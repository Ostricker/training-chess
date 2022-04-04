package chess.pieces;
//This is just to drive example provided, this will be modified considerably

import chess.ChessPiece;
import chess.Player;

import java.util.List;

public class Pawn extends ChessPiece
{
	public Pawn(Player player, char file, int rank) {
		super(player, PieceType.PAWN, file, rank);
	}

	@Override
	public boolean isValidMove(char file, int rank, List<ChessPiece> listOfPieces)
	{
		// If file is not the same return false
		if (file != getBoardFile())
		{
			return false;
		}

		// calculate max possible move
		int maxMove = 1;
		if (getMyPlayer().getCurrentTurn() == 1)
		{
			maxMove = 2;
		}

		// It can move top + 1 only
		if (isWhite())
		{
			if (getBoardRank() + maxMove == rank)
			{
				return true;
			}
		}
		else
		{
			if (getBoardRank() - maxMove == rank)
			{
				return true;
			}
		}

		return false;
	}
}