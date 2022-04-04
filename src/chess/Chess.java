package chess;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.PieceType;
import chess.pieces.Queen;
import chess.pieces.Rook;
import graphicAPI.DrawingPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Chess {

	//static panel and graphics, so they can be used be all methods
	static DrawingPanel panel = new DrawingPanel(600, 600);
	static Graphics g = panel.getGraphics();
	
	//Variables used for sizing 
	static int MAX_CONSOLE = 60;
	static int GRID_SIZE = 50;

	// List of all chess pieces
	static List<ChessPiece> listChessPieces = new ArrayList<>();

	// List of players
	static List<Player> listPlayers = new ArrayList<>();
	static boolean isWhiteTurn = true;

	// Memory for last selected
	static ChessPiece lastSelectedPiece = null;

	// variable to simply change colors
	static boolean isWhite = true;

	public static void main(String[] args) {
		//Call the method to initialize the board
		initializeBoard();
		
		//Setup graphicAPI.DrawingPanel to handle clicks
		panel.onClick((x, y) -> handleClickEvent(x, y));
		
		/*
		// Very Useful Code
		chess.ChessPiece[] pieces = new chess.ChessPiece[32];
		chess.pieces.King king = new chess.pieces.King('E', 1);
		king.type = "chess.pieces.King";
		pieces[0] = king;
		if(pieces[0].type.equals("chess.pieces.King")) {
			System.out.println(((chess.pieces.King) pieces[0]).kingExample);
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
			System.out.println("Invalid Area Clicked");
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

			// Get selected piece
			ChessPiece selectedPiece = null;
			for (ChessPiece piece : listChessPieces)
			{
				if (rank == piece.getBoardRank() && file == piece.getBoardFile())
				{
					selectedPiece = piece;
					break;
				}
			}

			// If I have clicked on piece I will write out its type
			if (selectedPiece != null)
			{
				// Check if I can move this piece at this point
				if ((isWhiteTurn && selectedPiece.isWhite()) || (!isWhiteTurn && !selectedPiece.isWhite()))
				{
					lastSelectedPiece = selectedPiece;
					System.out.println(selectedPiece.getType().getText() + " | " + (selectedPiece.isWhite() ? "white" : "black"));
				}
			}
			else
			{
				if (lastSelectedPiece != null)
				{
					// Check for move validity
					if (!lastSelectedPiece.isValidMove(file, rank, listChessPieces))
					{
						System.out.println("This is not valid move");
						return;
					}

					lastSelectedPiece.setBoardFile(file);
					lastSelectedPiece.setBoardRank(rank);
					System.out.println(lastSelectedPiece.getType().getText() + " | "
									   + (lastSelectedPiece.isWhite() ? "white" : "black")
									   + " | New position: " + file + rank);

					// Add +1 to players turn
					for (Player player : listPlayers)
					{
						if (isWhiteTurn == player.isWhite())
						{
							player.setCurrentTurn(player.getCurrentTurn() + 1);
							System.out.println("Player " + player.getPlayerType().getText() + " turn " + player.getCurrentTurn());
						}
					}

					// Change turn
					isWhiteTurn = !isWhiteTurn;
					System.out.println("Next turn: " + (isWhiteTurn ? "white" : "black"));

					// Delete last piece from memory after successful turn
					lastSelectedPiece = null;
				}
			}
		}

		// After change, we need to redraw current state of the board
		redrawBoard();
	}

	/**
	 * Redraw complete board
	 */
	public static void redrawBoard() {
		//Draw the board
		drawBoard();
		//Draw Console Area
		dpPrint("");

		// Redraw list of pieces
		for (ChessPiece piece : listChessPieces)
		{
			//	if (piece instanceof King)
			//	{
			//		System.out.println(((King) piece).kingExample);
			//	}
			drawPiece(piece.isWhite(), piece.getType(), piece.getxCoord(), piece.getyCoord());
		}
	}

	/**
	 * TODO initializeBoard just draws pieces but does not manage the game
	 * 1) chess.pieces.PieceType should be Enumerator
	 * 2) We need to create game management -> then we can put pieces on the board
	 * 3) Then create chess.ChessPiece and extend it to child chess.pieces.King and other pieces -> They contain rules of their own movement
	 * 4) Then move the pieces on board
	 */
	public static void initializeBoard() {

		// Top player with black pieces
		Player topPlayer = new Player(PlayerType.TOP, !isWhite);
		listPlayers.add(topPlayer);

		// Black pieces
		listChessPieces.add(new King(topPlayer, 'E', 8));
		listChessPieces.add(new Queen(topPlayer, 'D', 8));

		listChessPieces.add(new Bishop(topPlayer, 'C', 8));
		listChessPieces.add(new Bishop(topPlayer, 'F', 8));

		listChessPieces.add(new Rook(topPlayer, 'A', 8));
		listChessPieces.add(new Rook(topPlayer, 'H', 8));

		listChessPieces.add(new Knight(topPlayer, 'B', 8));
		listChessPieces.add(new Knight(topPlayer, 'G', 8));

		listChessPieces.add(new Pawn(topPlayer, 'A', 7));
		listChessPieces.add(new Pawn(topPlayer, 'B', 7));
		listChessPieces.add(new Pawn(topPlayer, 'C', 7));
		listChessPieces.add(new Pawn(topPlayer, 'D', 7));
		listChessPieces.add(new Pawn(topPlayer, 'E', 7));
		listChessPieces.add(new Pawn(topPlayer, 'F', 7));
		listChessPieces.add(new Pawn(topPlayer, 'G', 7));
		listChessPieces.add(new Pawn(topPlayer, 'H', 7));

		// Bottom player with white pieces
		Player bottomPlayer = new Player(PlayerType.BOTTOM, isWhite);
		listPlayers.add(bottomPlayer);

		// White pieces
		listChessPieces.add(new King(bottomPlayer, 'E', 1));
		listChessPieces.add(new Queen(bottomPlayer, 'D', 1));

		listChessPieces.add(new Bishop(bottomPlayer, 'C', 1));
		listChessPieces.add(new Bishop(bottomPlayer, 'F', 1));

		listChessPieces.add(new Rook(bottomPlayer, 'A', 1));
		listChessPieces.add(new Rook(bottomPlayer, 'H', 1));

		listChessPieces.add(new Knight(bottomPlayer, 'B', 1));
		listChessPieces.add(new Knight(bottomPlayer, 'G', 1));

		listChessPieces.add(new Pawn(bottomPlayer, 'A', 2));
		listChessPieces.add(new Pawn(bottomPlayer, 'B', 2));
		listChessPieces.add(new Pawn(bottomPlayer, 'C', 2));
		listChessPieces.add(new Pawn(bottomPlayer, 'D', 2));
		listChessPieces.add(new Pawn(bottomPlayer, 'E', 2));
		listChessPieces.add(new Pawn(bottomPlayer, 'F', 2));
		listChessPieces.add(new Pawn(bottomPlayer, 'G', 2));
		listChessPieces.add(new Pawn(bottomPlayer, 'H', 2));

		redrawBoard();
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
	 * Draw single piece
	 * @param isWhite
	 * @param pieceType
	 * @param x
	 * @param y
	 */
	public static void drawPiece(boolean isWhite, PieceType pieceType, int x, int y) {
		if(isWhite) {
			g.setColor(Color.WHITE);
		}
		else {
			g.setColor(Color.BLACK);
		}

		switch (pieceType) {
			case KING:
				g.fillPolygon(new int[] {17+x, 25+x, 33+x}, new int[] {43+y, 6+y, 43+y}, 3); //Body
				g.fillOval(12 + x, 41 + y, 26, 6); //Base
				g.fillOval(18 + x, 17 + y, 14, 4); //Shoulder 01
				g.fillRect(23 + x, 5 + y, 4, 25); //Cross
				g.fillRect(19 + x, 9 + y, 12, 4); //Cross
				break;
			case QUEEN:
				g.fillPolygon(new int[] {17+x, 25+x, 33+x}, new int[] {43+y, 6+y, 43+y}, 3); //Body
				g.fillOval(12 + x, 41 + y, 26, 6); //Base
				g.fillOval(17 + x, 17 + y, 16, 4); //Shoulder 01
				g.fillOval(17 + x, 25 + y, 16, 4); //Shoulder 02
				g.fillOval(20 + x, 5 + y, 10, 10); //Head
				break;
			case ROOK:
				g.fillRect(15 + x, 17 + y, 20, 30); //Body
				g.fillRect(10 + x, 42 + y, 30, 5); //Bottom Black
				g.fillRect(10 + x, 12 + y, 30, 12); //Top Block
				g.fillRect(10 + x, 7 + y, 5, 5); //Pillar One
				g.fillRect(18 + x, 7 + y, 5, 5); //Pillar Two
				g.fillRect(27 + x, 7 + y, 5, 5); //Pillar Three
				g.fillRect(35 + x, 7 + y, 5, 5); //Pillar Four
				break;
			case BISHOP:
				g.fillPolygon(new int[] {17+x, 25+x, 33+x}, new int[] {43+y, 16+y, 43+y}, 3); //Body
				g.fillOval(12 + x, 41 + y, 26, 6); //Base
				g.fillPolygon(new int[] {18+x, 25+x, 32+x, 25+x}, new int[] {17+y, 27+y, 17+y, 8+y}, 4); //Head
				break;
			case KNIGHT:
				g.fillOval(8 + x, 39 + y, 35, 8); //Base
				g.fillPolygon(new int[] {15+x, 15+x, 25+x, 35+x}, new int[] {42+y, 17+y, 12+y, 40+y}, 4); //Body
				g.fillOval(12 + x, 7 + y, 16, 16); //Head
				g.fillPolygon(new int[] {25+x, 20+x, 40+x, 35+x}, new int[] {21+y, 7+y, 16+y, 23+y}, 4); //Face
				break;
			case PAWN:
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


