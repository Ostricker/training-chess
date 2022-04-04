package chess.pieces;
//This is just to drive example provided, this will be modified considerably

import chess.ChessPiece;
import chess.Player;

import java.util.List;

public class King extends ChessPiece
{
	public String kingExample = "chess.pieces.King child field. This only exists in chess.pieces.King, not chess.ChessPiece";
	
	public King(Player player, char file, int rank) {
		super(player, PieceType.KING, file, rank);
	}

	@Override
	public boolean isValidMove(char file, int rank, List<ChessPiece> listOfPieces)
	{
		return false;
	}
}