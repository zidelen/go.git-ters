package chess.pieces;

import chess.pieces.Piece.Color;
import chess.pieces.Piece.Position;

public class Rook extends Piece{
  public Rook(Color color, Position position) {
    super(color, position, getColorPrefix(color) + "R");
  }

  private static String getColorPrefix(Color color) {
    return (color == Color.WHITE) ? "w" : "b";
  }
}
