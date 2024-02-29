package chess.pieces;

import chess.pieces.Piece.Color;
import chess.pieces.Piece.Position;

public class Queen extends Piece{
  public Queen(Color color, Position position) {
    super(color, position, getColorPrefix(color) + "Q");
  }

  private static String getColorPrefix(Color color) {
    return (color == Color.WHITE) ? "w" : "b";
  }
}
