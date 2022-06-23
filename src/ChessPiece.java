
public abstract class ChessPiece {

	private int boardRank; //1 to 8
	private char boardFile; //A-H
	private int boardFileNum; //1 to 8, representing A-H
	
	private int xCoord; // Calculated from boardFileNum
	private int yCoord; // Calculated from boardRank
	
	public boolean isAlive = true; //Tracks if piece is alive
	public boolean isWhite;
	
	public String type = "n/a";
	
	//Constructor
	public ChessPiece(char file, int rank) {
		setBoardRank(rank);
		setBoardFile(file);
	}
	
	//Converts from file (a char) to the int representation
	public int convertFileToInt(char file) {
		switch (file) {
			case 'A':
				return  1;
			case 'B':
				return 2;
			case 'C':
				return 3;
			case 'D':
				return 4;
			case 'E':
				return 5;
			case 'F':
				return 6;
			case 'G':
				return 7;
			case 'H':
				return 8;
			default:
				//Incorrect file entered
				return 0;
		}
	}
	
	//Returns if move is valid, must be Overridden, will most likely use convertFileToInt()
	public abstract boolean isValidMove(char file, int rank, ChessPiece[] pieces);

	//Setter for boardRank that also sets yCoord
	public void setBoardRank(int boardRank) {
		this.boardRank = boardRank;
		yCoord = 100 + 50*(8 - boardRank); //Calculate coordinate from rank
	}

	//Setter for boardFile that also sets boardFileNum and xCoord
	public void setBoardFile(char boardFile) {
		this.boardFile = boardFile;
		boardFileNum = convertFileToInt(boardFile);
		xCoord = 100 + 50*(boardFileNum - 1); //Calculate coordinate from file
	}

	public char getBoardFile() {
		return boardFile;
	}

	public int getBoardRank() {
		return boardRank;
	}

	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}
	
}
