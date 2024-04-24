package edu.txstate.git.rrj29.go_git_ters.chess.board;

import edu.txstate.git.rrj29.go_git_ters.chess.pieces.Piece;
import edu.txstate.git.rrj29.go_git_ters.utils.Color;

/**
 * Represents a square on the chessboard.
 */
public class Square {

  private final Color color; // The color of the square
  private Piece piece; // The piece currently occupying the square, null if empty

  /**
   * Constructs an empty square with the specified color.
   *
   * @param color The color of the square
   */
  public Square(Color color) {
    this.color = color;
    this.piece = null;
  }

  /**
   * Returns the piece currently occupying the square.
   *
   * @return The piece occupying the square, or null if empty
   */
  public Piece piece() {
    return piece;
  }

  /**
   * Sets the piece occupying the square.
   *
   * @param piece The piece to be placed on the square
   */
  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  /**
   * Checks if the square is occupied by a piece.
   *
   * @return True if the square is occupied, otherwise false
   */
  public boolean occupied() {
    return piece != null;
  }

  /**
   * Returns the color of the square.
   *
   * @return The color of the square
   */
  public Color color() {
    return color;
  }
}
