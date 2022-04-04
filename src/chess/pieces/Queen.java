package chess.pieces;
//This is just to drive example provided, this will be modified considerably

import chess.ChessPiece;
import chess.Player;

import java.util.List;

public class Queen extends ChessPiece
{
	public Queen(Player player, char file, int rank) {
		super(player, PieceType.QUEEN, file, rank);
	}

	@Override
	public boolean isValidMove(char file, int rank, List<ChessPiece> listOfPieces)
	{
		return false;
	}
}