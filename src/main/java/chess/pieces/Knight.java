package chess.pieces;


public class Knight extends Piece{
  public Knight(Color color, Position position) {
    super(color, position, getColorPrefix(color) + "N");
  }

  private static String getColorPrefix(Color color) {
    return (color == Color.WHITE) ? "w" : "b";
  }
}
