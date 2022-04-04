package chess.pieces;

public enum PieceType
{
  KING("King"),
  QUEEN("Queen"),
  BISHOP("Bishop"),
  ROOK("Rook"),
  KNIGHT("Knight"),
  PAWN("Pawn");

  private final String text;

  PieceType(String text)
  {
    this.text = text;
  }

  public String getText()
  {
    return text;
  }
}
