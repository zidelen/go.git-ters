package chess.pieces;

public class Pawn extends Piece {

  public Pawn(Color color, Position position) {
    super(color, position, getColorPrefix(color) + "P");
  }

  private static String getColorPrefix(Color color) {
    return (color == Color.WHITE) ? "w" : "b";
  }
}
