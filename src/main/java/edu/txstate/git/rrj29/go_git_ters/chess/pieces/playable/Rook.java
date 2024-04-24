package edu.txstate.git.rrj29.go_git_ters.chess.pieces.playable;

import edu.txstate.git.rrj29.go_git_ters.chess.pieces.Piece;
import edu.txstate.git.rrj29.go_git_ters.chess.board.Board;
import edu.txstate.git.rrj29.go_git_ters.utils.Color;
import edu.txstate.git.rrj29.go_git_ters.utils.Position;
import java.util.ArrayList;

/**
 * Represents a rook piece in chess.
 */
public class Rook extends Piece {

  /**
   * Constructs a rook with the specified color, position, and board.
   *
   * @param color    The color of the rook
   * @param position The position of the rook
   * @param board    The chessboard
   */
  public Rook(String path, Color color, Position position, Board board) {
    super(path, color, position, color.getPrefix() + "R", board);
    updateMoves();
  }

  /**
   * Updates the possible moves for the rook.
   */
  @Override
  public void updateMoves() {
    ArrayList<Position> newMoveList = new ArrayList<>();
    for (int dirRow = -1; dirRow <= 1; dirRow += 2) {
      addMovesInDirection(dirRow, 0, newMoveList);
    }
    for (int dirCol = -1; dirCol <= 1; dirCol += 2) {
      addMovesInDirection(0, dirCol, newMoveList);
    }
    this.moves = newMoveList.toArray(new Position[0]);
  }
}
