package edu.txstate.git.rrj29.go_git_ters.chess;

import edu.txstate.git.rrj29.go_git_ters.chess.board.Board;
import edu.txstate.git.rrj29.go_git_ters.chess.Pieces.Piece;
import edu.txstate.git.rrj29.go_git_ters.utils.Color;
import edu.txstate.git.rrj29.go_git_ters.utils.Position;
import java.util.ArrayList;

/**
 * Represents a player in a chess game.
 */
public class Player {

  private Color color; // The color of the player (BLACK or WHITE)
  ArrayList<Piece> pieces; // The pieces owned by the player

  /**
   * Constructs a player with the specified color and initializes their pieces on the board.
   *
   * @param color The color of the player
   * @param board The chessboard
   */
  public Player(Color color, Board board) {
    this.color = color;
    this.pieces = new ArrayList<>();
    initStartingPieces(board);
  }

  /**
   * Initializes the player's pieces on the board.
   *
   * @param board The chessboard
   */
  private void initStartingPieces(Board board) {
    for (int row = 1; row <= 8; row++) {
      for (int col = 1; col <= 8; col++) {
        Position currentPos = new Position(row, col);
        Piece currentPiece = board.getPiece(currentPos);
        if (currentPiece != null && currentPiece.color() == this.color) {
          this.pieces.add(currentPiece);
        }
      }
    }
  }
}
