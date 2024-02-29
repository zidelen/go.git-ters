package chess.pieces;

public class Bishop extends Piece{
  public Bishop(Color color, Position position) {
    super(color, position, getColorPrefix(color) + "B");
  }

  private static String getColorPrefix(Color color) {
    return (color == Color.WHITE) ? "w" : "b";
  }
}
