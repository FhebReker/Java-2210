public interface BoardADT {
// Read description of these methods in the assignment

	public HashDictionary makeDictionary();

	public void saveTile(int i, int j, char symbol);

	public boolean isDraw(char player, int minEmpty);

	public boolean winner(char player);

	public int evaluate(char player, int minEmpty);

	public int repeatedLayout(HashDictionary table); 

	public void storeLayout(HashDictionary table, int score); 

	public boolean positionIsEmpty(int row, int column);

	public boolean isComputerTile(int row, int column);

	public boolean isHumanTile(int row, int column);

}
