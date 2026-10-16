/* 
CS 2210 – Assignment 4
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: November 4, 2025 
*/

/**
This class implements a text-based application that allows the user to access the information in the multimedia ordered dictionary. 

This class contains the main method, declared with the usual method header:
public static void main(String[] args)

The input to the program will be a file containing the information that is to be stored in the ordered dictionary. 
Therefore, to run the program you will type this command:
java Query inputFile
where inputFile is the name of the file containing the input for the program. 
*/
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;

public class Query {
   private BSTOrderedDictionary tree;
   public static void main(String[] args) {
      String result, nextCommand;
      StringReader keyboard = new StringReader();

      // Make sure we're working only with the file
      if (args.length != 1) {
         System.out.println("Usage: java Query fileName");
         System.exit(0);
      }

      // Initialize tree
      Query myProgram = new Query(args[0]);

      // Loop to process commands
      do {
         nextCommand = keyboard.read("Enter next command: ");
         result = myProgram.processCommand(nextCommand);
         System.out.println(result);
      } while (result != "");
   }

   public Query(String inputFile){
      // Initialize tree
      this.tree = new BSTOrderedDictionary();
      
      // Parse input file and add to tree
      try {
         BufferedReader br = new BufferedReader(new FileReader(inputFile));
         String keyLine = br.readLine(), contentLine = br.readLine();
         while (keyLine != null && contentLine != null) {
            // Initialize variables
            String extention;
            int type;
            keyLine = keyLine.toLowerCase();
            
            // Find extention and handle case of no extention
            if (contentLine.lastIndexOf(".") >= 0) {extention = contentLine.substring(contentLine.lastIndexOf("."));}
            else {extention = "";}

            // Find type based on extention
            switch (extention) {
               case ".wav":
               case ".mid":
                  type = 2;
                  break;
               case ".jpg":
               case ".gif":
                  type = 3;
                  break;
               case ".html":
                  type = 4;
                  break;
               default:
                  type = 1;
                  break;
            }

            // Add to tree
            this.tree.put(this.tree.getRoot(), keyLine, contentLine, type);

            // Move to next lines
            keyLine = br.readLine();
            contentLine = br.readLine();
         }
         br.close();
      } catch (Exception e) {
         System.err.println("\nError reading file: " + inputFile);
         e.printStackTrace();
         System.exit(1);
      }
   }

   public String processCommand(String command) {
      // 
      String result = "";
      BSTNode head = this.tree.getRoot();
      String userCommand = command.toLowerCase().trim();

      StringTokenizer st = new StringTokenizer(userCommand);
      String methodSelected = st.nextToken();

      switch (methodSelected) {
         case "get": // get key
            // Initialize variables
            String getKey = st.nextToken();
            ArrayList<MultimediaItem> items = this.tree.get(head, getKey);

            // Get predecessor and successor if not in dictionary
            if (items == null) {
               result += "The word  " + getKey + " is not in the ordered dictionary.\n";
               
               Data predecessor = this.tree.predecessor(head, getKey);
               result += "Preceding word: ";
               if (predecessor!= null) result +=  predecessor.getName();
               result += "\n";

               Data successor = this.tree.successor(head, getKey);
               result += "Following word: ";
               if (successor!= null) result +=  successor.getName();
               result += "\n";
            }

            // Otherwise cycle through all elements in the node and display according to type
            else {
               for (MultimediaItem mediaItem: items) {
                  switch (mediaItem.getType()) {
                     case 1:
                        result += mediaItem.getContent() + "\n";
                        break;
                     case 2:
                        try {
                           SoundPlayer player = new SoundPlayer();
                           player.play(mediaItem.getContent());
                           result += " ";
                        }
                        catch (MultimediaException e) { System.out.println(e.getMessage());}
                        break;
                     case 3:
                        try {
                           PictureViewer viewer = new PictureViewer();
                           viewer.show(mediaItem.getContent());
                           result += " ";
                        }
                        catch (MultimediaException e) { System.out.println(e.getMessage());}
                        break;
                     case 4:
                        ShowHTML htmlShower = new ShowHTML();
                        htmlShower.show(mediaItem.getContent());
                        result += " ";
                        break;
                  }
               }
            }
            break;
         
         case "remove": // remove key
            // Initialize key
            String removeKey = st.nextToken();

            // Try to remove node, or update result to show error if key is not in the tree
            try {
               this.tree.remove(head, removeKey);
               result += " ";
            } 
            catch (Exception e) {result += "No record in the ordered dictionary has the key " + removeKey + ".";}
            break;

         case "delete": // delete key type
            // Initialize variables
            String deleteKey = st.nextToken();
            int removeType = Integer.parseInt(st.nextToken());
            
            // Delete types from node in tree, update result to show error if key is not in the tree
            try {
               this.tree.remove(head, deleteKey, removeType);
               result += " ";
            }
            catch (Exception e) {result += "No record in the ordered dictionary has the key " + deleteKey + ".";}
            break;

         case "add": // add key content type
            // Use substring instead of string tokenizer due to how the spaces work and that we need content as written
            String restOfArgs = command.substring(command.indexOf(" ") + 1); // Skipping past 'add'
            restOfArgs = restOfArgs.substring(restOfArgs.indexOf(" ")+1); // Skipping past key
            int endOfContentIndex = restOfArgs.lastIndexOf(" ");

            // Initialize variables
            String addKey = st.nextToken(); // key
            String addContent = restOfArgs.substring(0, endOfContentIndex); // content
            int addType = Integer.parseInt(restOfArgs.substring(endOfContentIndex+1)); // type

            // Add to tree and update result to show successful completion
            this.tree.put(head, addKey, addContent, addType);
            result += " ";
            break;

         case "next": // next key successorCounts
            // Initialize variables
            String nextKey = st.nextToken();
            int numOfNextNodes = Integer.parseInt(st.nextToken());

            // Loop until all successors have been found
            while (numOfNextNodes > 0) {
               Data nextNode = this.tree.successor(head, nextKey);

               // If we found a successor, update result string and use successor key to check for further successors
               if (nextNode != null) {
                  result += nextNode.getName() + " ";
                  nextKey = nextNode.getName();
                  numOfNextNodes--;
               }

               // Otherwise, exit out of the loop (and update result to show error if no successor exists for the node)
               else {
                  if (result.isEmpty()) {result += "There are no keys larger or equal to " + nextKey;}
                  numOfNextNodes = 0;
               }
            }
            break;

         case "prev": // prev key predecessorCount
            // Initialize variables
            String prevKey = st.nextToken();
            int numOfPrevNodes = Integer.parseInt(st.nextToken());

            // Loop until all predecessors have been found
            while (numOfPrevNodes > 0) {
               Data prevNode = this.tree.predecessor(head, prevKey);

               // If we found a predecessor, update result string and use predecessor key to check for further predecessors
               if (prevNode != null) {
                  result += prevNode.getName() + " ";
                  prevKey = prevNode.getName();
                  numOfPrevNodes--;
               }

               // Otherwise, exit out of the loop (and update result to show error if no predecessor exists for the node)
               else {
                  if (result.isEmpty()) {result += "There are no keys smaller or equal to " + prevKey;}
                  numOfPrevNodes = 0;
               }
            }       
            break;

         case "first": // first
            if (this.tree.smallest(head) != null) {result += this.tree.smallest(head).getName();}
            else {result += "The ordered dictionary is empty.";}
            break;

         case "last": // last
            if (this.tree.largest(head) != null) {result += this.tree.largest(head).getName();}
            else {result += "The ordered dictionary is empty.";}
            break;

         case "size": // size
            result += "There are " + this.tree.getNumInternalNodes() + " keys in the ordered dictionary";
            break;

         case "end": // end
            result = "";
            break;
         
         default: // Any other input form
            result = "Invalid command";
            break;
      }

      // Return whatever result we get
      return result;
   }
}
