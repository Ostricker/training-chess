package chess;

public class Player
{
  private final PlayerType playerType;
  private final boolean isWhite;

  private int currentTurn = 1;

  public Player(PlayerType playerType, boolean isWhite)
  {
    this.playerType = playerType;
    this.isWhite    = isWhite;
  }

  public PlayerType getPlayerType()
  {
    return playerType;
  }

  public int getCurrentTurn()
  {
    return currentTurn;
  }

  public void setCurrentTurn(int currentTurn)
  {
    this.currentTurn = currentTurn;
  }

  public boolean isWhite()
  {
    return isWhite;
  }
}
