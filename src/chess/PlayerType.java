package chess;

public enum PlayerType
{
  TOP("TOP"),
  BOTTOM("BOTTOM");

  private final String text;

  PlayerType(String text)
  {
    this.text = text;
  }

  public String getText()
  {
    return text;
  }
}
