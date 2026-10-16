import java.io.*;
import java.util.ArrayList;

public class TestDict {

  public static void main(String[] args) {
	final int TEXT = 1;
	final int AUDIO = 2;
	final int IMAGE = 3;
	final int HTML = 4;
	BSTOrderedDictionary dictionary = new BSTOrderedDictionary();
	BSTNode root = dictionary.getRoot();
	ArrayList<MultimediaItem> list;
	Data data;

	String keys[] = {"homework", "course", "class", "computer", "four"};
	String content[] = {"Very enjoyable work that students need to complete outside the classroom.",
				"A series of talks or lessons. For example, CS210.",
				"A set of students taught together,",
				"An electronic machine frequently used by Computer Science students.",
				"One more than three",
				"homework.html",
				"Electronic device used to play video games",
				"computer.gif",
				"computer.wav"};
	int type[] = {TEXT, TEXT, TEXT, TEXT, TEXT, HTML, TEXT, IMAGE, AUDIO};
	
	boolean alltests = true;
	boolean passed = true;
	int test = -1;
	if (args.length > 0) {
		System.err.println("Running test ");
		test = Integer.parseInt(args[0]);
		alltests = false;
	}

	// Insert one key and then find it
	if (alltests || test == 1 || test == 3 || test == 6 || test == 7 || test == 8 || test == 9)
	try {
	    dictionary.put(root,keys[0],content[0],type[0]);
	    root = dictionary.getRoot();
	    list = dictionary.get(root,keys[0]);
		if (!(list.get(0).getContent()).equals(content[0])) passed = false;
	    BSTNode r = dictionary.getRoot();
		if (!r.getData().getName().equals(keys[0])) passed = false;

	    list = dictionary.get(root,keys[1]);
		if (list != null) passed = false;
		if (dictionary.getNumInternalNodes() != 1) passed = false;
	    print(passed,1);
	}
	catch(Exception e) {
	    print(false,1);
	    System.out.println(e.getMessage());
	}

	// Insert the same key again
	if (alltests || test == 3)
	try {
	    passed = true;
	    dictionary.put(root,keys[0],content[5],type[5]);
	    root = dictionary.getRoot();
	    list = dictionary.get(root,keys[0]);
	    if (list.size() != 2) passed = false;
	    if (dictionary.getNumInternalNodes() != 1) passed = false;
	    print(passed,2);
	}
	catch (Exception e) {
	    print(false,2);
	    System.out.println(e.getMessage());	  
	}

	// Insert and remove a key
	if (alltests || test == 4)
	try {
		passed = true;
	    dictionary.put(root,keys[1],content[1],type[1]);
	    root = dictionary.getRoot();
	    dictionary.remove(root,keys[1]);
	    root = dictionary.getRoot();
	    list = dictionary.get(root,keys[1]);
	    if (list != null) passed = false;
		
		dictionary.put(root,keys[1],content[1],type[1]);
		dictionary.put(root,keys[2],content[2],type[2]);
		dictionary.put(root,keys[3],content[3],type[3]);
		dictionary.put(root,keys[3],content[7],type[7]);
		dictionary.put(root,keys[3],content[6],type[6]);
		dictionary.remove(root,keys[3],type[3]);
		
		list = dictionary.get(root,keys[3]);
		if (list.size() != 1) passed = false;
		if (list.get(0).getType() != IMAGE) passed = false;
		
		print(passed,3);
	}
	catch(DictionaryException e) {
	    print(false,3);
	}

	// Remove a key not in the dictionary
	if (alltests || test == 5)
	try {
		passed = true;
		dictionary = new BSTOrderedDictionary();
		root = dictionary.getRoot();
	    dictionary.put(root,keys[1],content[1],type[1]);
	    root = dictionary.getRoot();	    	    
	    dictionary.put(root,keys[0],content[0],type[0]);
	    	    
	    for (int i = 2; i < 5; ++i) dictionary.put(root,keys[i],content[i],type[i]);	

	    dictionary.remove(root,keys[2]);
	    if (!root.getLeftChild().getData().getName().equals("computer")) passed = false;
	    dictionary.remove(root,keys[1]);
	    String k = root.getData().getName();
	    if (!k.equals("computer") && !k.equals("four")) passed = false;
	    dictionary.remove(root,keys[2]);
	    print(false,4);
	}
	catch(DictionaryException e) {
	    print(passed,4);
	}
	catch (Exception e) {
	    print(false,4);
	    System.out.println(e.getMessage());	    
	}

	// Insert 5 words in the dictionary and test the successor method
	if (alltests || test == 6 || test == 7 || test == 8 || test == 9)
	try {
	    passed = true;
	    dictionary = new BSTOrderedDictionary();
	    root = dictionary.getRoot();
	    dictionary.put(root,keys[1],content[1],type[1]);
	    root = dictionary.getRoot();	    	    
	    dictionary.put(root,keys[0],content[0],type[0]);
	    	    
	    for (int i = 2; i < 5; ++i) 
			dictionary.put(root,keys[i],content[i],type[i]);

	    root = dictionary.getRoot();
	    
	    data = dictionary.successor(root,keys[3]);
	    if ((data.getName()).compareTo(keys[1]) == 0)
	    if (dictionary.getNumInternalNodes() != 5) passed = false;
	    
	    print(passed,5);
	}
	catch (Exception e) {
	    print(false,5);
	    System.out.println(e.getMessage());
	}

	// Test the successor and smallest methods
	if (alltests || test == 7)
	try {
	    passed = true;
	    data = dictionary.successor(root,keys[2]);
	    if ((data.getName()).compareTo(keys[3]) != 0) passed = false;
	    
	    data = dictionary.smallest(root);
	    if ((data.getName()).compareTo(keys[2]) != 0) passed = false;
	    print(passed,6);
	}
	catch (Exception e) {
	    print(false,6);
	}

	//Test the predecessor and largst methods
	if (alltests || test == 8)
	try {
	    passed = true;
	    data = dictionary.predecessor(root,keys[0]);
	    if ((data.getName()).compareTo(keys[4]) != 0) passed = false;
	    data = dictionary.largest(root);
	    if ((data.getName()).compareTo(keys[0]) != 0) passed = false;
	    
	    print(passed,7);
	}
	catch (Exception e) {
	    print(false,7);
	}

	// Test the predecessor method
	if (alltests || test == 9)
	try {
	    data = dictionary.predecessor(root,keys[4]);
	    if ((data.getName()).compareTo(keys[1]) == 0)
			print(true,8);
	    else print(false,8);
	}
	catch (Exception e) {
	    print(false,8);
	}

        // Create a new empty dictionary

	dictionary = new BSTOrderedDictionary();

	try {


	    // Insert a large number of words in the dictionary
	    BufferedReader in = new BufferedReader(new FileReader("large.txt"));
	    String word = in.readLine();
	    String definition;
	    boolean test10 = true;

		if (alltests || test >= 10)
			try {
				while (word != null) {
					try {
						definition = in.readLine();
						dictionary.put(root,word,definition,TEXT); 
						word = in.readLine();
					}
					catch (Exception e) {
						test10 = false;
					}
				}

				if (test10) {	
					// Now, try to find the word "practic"
					list = dictionary.get(root,"practic");
					if ((list.get(0).getContent()).indexOf("Artful; deceitful; skillful.") == -1)
						print(false,9);
					else print(true,9);
				}
				else print(false,9);
			}
			catch (Exception e) {
				print(false,9);
			}

		if (alltests || test == 11)
			// Try to find a word that is not in the dictionary and test smallest
			try {
			        passed = true;
				list = dictionary.get(root,"schnell");
				if (list != null) passed = false;
				data = dictionary.smallest(root);
				if (data.getName().compareTo("aaronical") != 0) passed = false;
				
				print(passed,10);
			}
			catch (Exception e) {
				print(false,10);
			}

		if (alltests || test == 12)
			// Test the remove method
			try {
				dictionary.remove(root,"practic");
				list = dictionary.get(root,"practic");
				if (list == null) print(true,11);
				else print(false,11);
			}
			catch (Exception e) {
				print(false,11);
			}
	    
	    if (alltests || test == 13)
			// Try to insert a word that is already in the dictionary and test largest
			try {
			        passed = true;
				data = dictionary.largest(root);
				if (data.getName().compareTo("zythum") != 0) passed = false;			
				dictionary.put(root,"pool","Bpool.gif",3);
				print(true,12);
			}
			catch (Exception e) {
				print(false,12);
			}

		if (alltests || test == 14)
			// Test the predecessor method
			try {
				System.out.println("\t\tcheck 00000000000000000000000"); // debug predecessor
				data = dictionary.predecessor(root,"pony");
				System.out.println("check 1: <" + data.getName() + ">\texpected: <ponvolant>");
				if ((data.getName()).compareTo("ponvolant") == 0) 
					print(true,13);
				else print(false,13);
			}
			catch (Exception e) {
				print(false,13);
				/*Debug */
				e.printStackTrace();
				System.err.println("^^^ part 1: exception above ^^^");
				/*Debug */
				// looks like we find ponvolant early, but dont go back up the tree properly
			}

		if (alltests || test == 15)
			// Test the successor method
			try {
				System.out.println("\t\tcheck 11111111111111111111"); // debug successor
				data = dictionary.successor(root,"reel");
				System.out.println("check 1: <" + data.getName() + ">\texpected: <reem>");
				if ((data.getName()).compareTo("reem") == 0)
					print(true,14);
				else print(false,14);
			}
			catch (Exception e) {
				print(false,14);
				/*Debug */
				e.printStackTrace();
				System.err.println("^^^ part 2: exception above ^^^");
				/*Debug */
			}

		if (alltests || test == 16)
			// Test removing a word and then using successor
			try {
				System.out.println("\t\tcheck 22222222222222222"); // debug successor after using remove
				dictionary.remove(root,"langate");
				data = dictionary.successor(root,"land");
				System.out.println("check 1: <" + data.getName() + ">\texpected: <laniary>");
				if ((data.getName()).compareTo("laniary") == 0)
					print(true,15);
				else print(false,15);
			}
			catch (Exception e) {
				print(false,15);
				/*Debug */
				e.printStackTrace();
				System.err.println("^^^ part 3: exception above ^^^");
				/*Debug */
			}
	}
	catch (IOException e) {
	    System.out.println("Cannot open file: large.txt");
	}
  }
  
  private static void print(boolean testPassed, int test) {
	if (testPassed) System.out.println("Test "+test+" passed");
	else System.out.println("Test "+test+" failed");
  }

}
