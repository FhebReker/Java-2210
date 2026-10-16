import java.util.ArrayList;

public interface BSTOrderedDictionaryADT {
    /* Ordered Dictionary ADT */

	public BSTNode getRoot();
   
	public int getNumInternalNodes();
	
	public ArrayList<MultimediaItem> get (BSTNode r, String k);
	
	public void put (BSTNode r, String name, String content, int type);
	
	public void remove (BSTNode r, String k) throws DictionaryException;
	
	public void remove(BSTNode r, String k, int type) throws DictionaryException;
	
	public Data successor(BSTNode r, String k);
	
	public Data predecessor(BSTNode r, String k);
	
	public Data smallest (BSTNode r);
	
	public Data largest (BSTNode r);
}
		


