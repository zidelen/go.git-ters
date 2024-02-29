package chess.pieces;


public class King extends Piece{
  public King(Color color, Position position) {
    super(color, position, getColorPrefix(color) + "K");
  }

  private static String getColorPrefix(Color color) {
    return (color == Color.WHITE) ? "w" : "b";
  }
}
