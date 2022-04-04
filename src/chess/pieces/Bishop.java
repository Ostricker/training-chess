package chess.pieces;
//This is just to drive example provided, this will be modified considerably

import chess.ChessPiece;
import chess.Player;

import java.util.List;

public class Bishop extends ChessPiece
{
	public Bishop(Player player, char file, int rank) {
		super(player, PieceType.BISHOP, file, rank);
	}

	@Override
	public boolean isValidMove(char file, int rank, List<ChessPiece> listOfPieces)
	{
		return false;
	}
}