import graphicAPI.DrawingPanel;

import java.awt.*;

public class Chess {

	//static panel and graphics, so they can be used be all methods
	static DrawingPanel panel = new DrawingPanel(600, 600);
	static Graphics g = panel.getGraphics();   
	
	//Variables used for sizing 
	static int MAX_CONSOLE = 60;
	static int GRID_SIZE = 50;
	

	public static void main(String[] args) {
		//Call the method to initialize the board
		initializeBoard();
		
		//Setup graphicAPI.DrawingPanel to handle clicks
		panel.onClick( (x, y) -> handleClickEvent(x, y) );
		
		/*
		// Very Useful Code
		ChessPiece[] pieces = new ChessPiece[32];
		King king = new King('E', 1);
		king.type = "King";
		pieces[0] = king;
		if(pieces[0].type.equals("King")) {
			System.out.println(((King) pieces[0]).kingExample); 
		}
		*/
	}
	
	public static void dpPrint(String output) {
		//clear console output area
		g.setColor(Color.BLACK);   
		g.fillRect(15, 10, 570, 30); 
		g.setColor(Color.GREEN);
		g.setFont(new Font("Courier New", Font.PLAIN, 14)); 
		if(output.length() > MAX_CONSOLE) {
			output = output.substring(0, MAX_CONSOLE);
		}
		g.drawString("dpPrint> " + output, 20, 30);
	}

	/**
	 * Handles click (feel free to modify this if necessary)
	 * This is OK
	 * @param x
	 * @param y
	 */
	public static void handleClickEvent(int x, int y) {
		char file = ' ';
		int rank = 0;
		if(x < 100 || x > (100 + 8*GRID_SIZE) || y < 100 || y > (100 + 8*GRID_SIZE)) {
			dpPrint("Invalid Area Clicked");
		}
		else {
			//Set Rank
			if( y > 100 && y < 150) { rank = 8;}
			else if ( y > 150 && y < 200){ rank = 7;}
			else if ( y > 200 && y < 250){ rank = 6;}
			else if ( y > 250 && y < 300){ rank = 5;}
			else if ( y > 300 && y < 350){ rank = 4;}
			else if ( y > 350 && y < 400){ rank = 3;}
			else if ( y > 400 && y < 450){ rank = 2;}
			else if ( y > 450 && y < 500){ rank = 1;}
			//Set File
			if( x > 100 && x < 150) { file = 'A';}
			else if ( x > 150 && x < 200){ file = 'B';}
			else if ( x > 200 && x < 250){ file = 'C';}
			else if ( x > 250 && x < 300){ file = 'D';}
			else if ( x > 300 && x < 350){ file = 'E';}
			else if ( x > 350 && x < 400){ file = 'F';}
			else if ( x > 400 && x < 450){ file = 'G';}
			else if ( x > 450 && x < 500){ file = 'H';}
			dpPrint("Valid Click: " + file + rank);
		}
	}

	/**
	 * TODO initializeBoard just draws pieces but does not manage the game
	 * 1) PieceType should be Enumerator
	 * 2) We need to create game management -> then we can put pieces on the board
	 * 3)
	 */
	public static void initializeBoard() {
		//Draw the board
		drawBoard();
		//Draw Console Area
		dpPrint("");
		//Draw black pieces
		drawPiece(false, "King", 100 + 4*GRID_SIZE, 100 + 0*GRID_SIZE); //King
		drawPiece(false, "Queen", 100 + 3*GRID_SIZE, 100 + 0*GRID_SIZE); //Queen

		drawPiece(false, "Bishop", 100 + 2*GRID_SIZE, 100 + 0*GRID_SIZE); //Left Bishop
		drawPiece(false, "Bishop", 100 + 5*GRID_SIZE, 100 + 0*GRID_SIZE); //Right Bishop

		drawPiece(false, "Rook", 100 + 0*GRID_SIZE, 100 + 0*GRID_SIZE); //Left Rook
		drawPiece(false, "Rook", 100 + 7*GRID_SIZE, 100 + 0*GRID_SIZE); //Right Rook

		drawPiece(false, "Knight", 100 + 1*GRID_SIZE, 100 + 0*GRID_SIZE); //Left Knight
		drawPiece(false, "Knight", 100 + 6*GRID_SIZE, 100 + 0*GRID_SIZE); //Right Knight

		drawPiece(false, "Pawn", 100 + 0*GRID_SIZE, 100 + 1*GRID_SIZE); //Pawns
		drawPiece(false, "Pawn", 100 + 1*GRID_SIZE, 100 + 1*GRID_SIZE);
		drawPiece(false, "Pawn", 100 + 2*GRID_SIZE, 100 + 1*GRID_SIZE);
		drawPiece(false, "Pawn", 100 + 3*GRID_SIZE, 100 + 1*GRID_SIZE);
		drawPiece(false, "Pawn", 100 + 4*GRID_SIZE, 100 + 1*GRID_SIZE);
		drawPiece(false, "Pawn", 100 + 5*GRID_SIZE, 100 + 1*GRID_SIZE);
		drawPiece(false, "Pawn", 100 + 6*GRID_SIZE, 100 + 1*GRID_SIZE);
		drawPiece(false, "Pawn", 100 + 7*GRID_SIZE, 100 + 1*GRID_SIZE);

		//Draw white pieces
		drawPiece(true, "King", 100 + 4*GRID_SIZE, 100 + 7*GRID_SIZE); //King
		drawPiece(true, "Queen", 100 + 3*GRID_SIZE, 100 + 7*GRID_SIZE); //Queen

		drawPiece(true, "Bishop", 100 + 2*GRID_SIZE, 100 + 7*GRID_SIZE); //Left Bishop
		drawPiece(true, "Bishop", 100 + 5*GRID_SIZE, 100 + 7*GRID_SIZE); //Right Bishop

		drawPiece(true, "Rook", 100 + 0*GRID_SIZE, 100 + 7*GRID_SIZE); //Left Rook
		drawPiece(true, "Rook", 100 + 7*GRID_SIZE, 100 + 7*GRID_SIZE); //Right Rook

		drawPiece(true, "Knight", 100 + 1*GRID_SIZE, 100 + 7*GRID_SIZE); //Left Knight
		drawPiece(true, "Knight", 100 + 6*GRID_SIZE, 100 + 7*GRID_SIZE); //Right Knight

		drawPiece(true, "Pawn", 100 + 0*GRID_SIZE, 100 + 6*GRID_SIZE); //Pawns
		drawPiece(true, "Pawn", 100 + 1*GRID_SIZE, 100 + 6*GRID_SIZE);
		drawPiece(true, "Pawn", 100 + 2*GRID_SIZE, 100 + 6*GRID_SIZE);
		drawPiece(true, "Pawn", 100 + 3*GRID_SIZE, 100 + 6*GRID_SIZE);
		drawPiece(true, "Pawn", 100 + 4*GRID_SIZE, 100 + 6*GRID_SIZE);
		drawPiece(true, "Pawn", 100 + 5*GRID_SIZE, 100 + 6*GRID_SIZE);
		drawPiece(true, "Pawn", 100 + 6*GRID_SIZE, 100 + 6*GRID_SIZE);
		drawPiece(true, "Pawn", 100 + 7*GRID_SIZE, 100 + 6*GRID_SIZE);
	}

	/**
	 * This is OK
	 */
	public static void drawBoard() {
		//clear board
		g.setColor(Color.WHITE);   
		g.fillRect(1, 1, 600, 600); 
		// print "ranks" and "files" - i.e. what the letters and numbers are called
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier", Font.PLAIN, 42)); 
		g.drawString("A  B C  D  E  F  G  H", 112, 85);
		g.drawString("A  B C  D  E  F  G  H", 112, 540);
		g.drawString("8", 60, 135);
		g.drawString("7", 60, 185);
		g.drawString("6", 60, 235);
		g.drawString("5", 60, 285);
		g.drawString("4", 60, 335);
		g.drawString("3", 60, 385);
		g.drawString("2", 60, 435);
		g.drawString("1", 60, 485);
		g.drawString("8", 510, 135);
		g.drawString("7", 510, 185);
		g.drawString("6", 510, 235);
		g.drawString("5", 510, 285);
		g.drawString("4", 510, 335);
		g.drawString("3", 510, 385);
		g.drawString("2", 510, 435);
		g.drawString("1", 510, 485);
		//Colors for Board
		Color lightColor = new Color(255, 206, 158);
		Color darkColor = new Color(209, 139, 71);
		//Draws board squares
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j<8; j++) {
				if((i+j)%2==0) {
					g.setColor(lightColor);
				}
				else {
					g.setColor(darkColor);
				}
				g.fillRect(100 + GRID_SIZE*i, 100 + GRID_SIZE*j, GRID_SIZE, GRID_SIZE);
			}
		}
	}

	/**
	 * TODO PieceType as Enum
	 * @param isWhite
	 * @param pieceType
	 * @param x
	 * @param y
	 */
	public static void drawPiece(boolean isWhite, String pieceType, int x, int y) {
		if(isWhite) {
			g.setColor(Color.WHITE);
		}
		else {
			g.setColor(Color.BLACK);
		}
		switch (pieceType) {
			case "King":
				g.fillPolygon(new int[] {17+x, 25+x, 33+x}, new int[] {43+y, 6+y, 43+y}, 3); //Body
				g.fillOval(12 + x, 41 + y, 26, 6); //Base
				g.fillOval(18 + x, 17 + y, 14, 4); //Shoulder 01
				g.fillRect(23 + x, 5 + y, 4, 25); //Cross
				g.fillRect(19 + x, 9 + y, 12, 4); //Cross
				break;
			case "Queen":
				g.fillPolygon(new int[] {17+x, 25+x, 33+x}, new int[] {43+y, 6+y, 43+y}, 3); //Body
				g.fillOval(12 + x, 41 + y, 26, 6); //Base
				g.fillOval(17 + x, 17 + y, 16, 4); //Shoulder 01
				g.fillOval(17 + x, 25 + y, 16, 4); //Shoulder 02
				g.fillOval(20 + x, 5 + y, 10, 10); //Head
				break;
			case "Rook":
				g.fillRect(15 + x, 17 + y, 20, 30); //Body
				g.fillRect(10 + x, 42 + y, 30, 5); //Bottom Black
				g.fillRect(10 + x, 12 + y, 30, 12); //Top Block
				g.fillRect(10 + x, 7 + y, 5, 5); //Pillar One
				g.fillRect(18 + x, 7 + y, 5, 5); //Pillar Two
				g.fillRect(27 + x, 7 + y, 5, 5); //Pillar Three
				g.fillRect(35 + x, 7 + y, 5, 5); //Pillar Four
				break;
			case "Bishop":
				g.fillPolygon(new int[] {17+x, 25+x, 33+x}, new int[] {43+y, 16+y, 43+y}, 3); //Body
				g.fillOval(12 + x, 41 + y, 26, 6); //Base
				g.fillPolygon(new int[] {18+x, 25+x, 32+x, 25+x}, new int[] {17+y, 27+y, 17+y, 8+y}, 4); //Head
				break;
			case "Knight":
				g.fillOval(8 + x, 39 + y, 35, 8); //Base
				g.fillPolygon(new int[] {15+x, 15+x, 25+x, 35+x}, new int[] {42+y, 17+y, 12+y, 40+y}, 4); //Body
				g.fillOval(12 + x, 7 + y, 16, 16); //Head
				g.fillPolygon(new int[] {25+x, 20+x, 40+x, 35+x}, new int[] {21+y, 7+y, 16+y, 23+y}, 4); //Face
				break;
			case "Pawn":
				g.fillPolygon(new int[] {17+x, 27+x, 37+x}, new int[] {40+y, 10+y, 40+y}, 3); //Center Triangle
				g.fillOval(22 + x, 8 + y, 10, 10); //Top Hat
				g.fillOval(13 + x, 35 + y, 28, 8); //Base
				break;
			default:
				System.out.println("Piece not recognized.");
				break;
		}
	}
}


