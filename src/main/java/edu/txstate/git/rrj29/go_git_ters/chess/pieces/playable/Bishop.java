package edu.txstate.git.rrj29.go_git_ters.chess.pieces.playable;

import edu.txstate.git.rrj29.go_git_ters.chess.board.Board;
import edu.txstate.git.rrj29.go_git_ters.chess.pieces.Piece;
import edu.txstate.git.rrj29.go_git_ters.utils.Color;
import edu.txstate.git.rrj29.go_git_ters.utils.Position;
import java.util.ArrayList;

/**
 * Represents a bishop piece in chess.
 */
public class Bishop extends Piece {

  /**
   * Constructs a bishop with the specified color, position, and board.
   *
   * @param color    The color of the bishop
   * @param position The position of the bishop
   * @param board    The chessboard
   */
  public Bishop(String path, Color color, Position position, Board board) {



    super(path,color, position, color.getPrefix() + "B", board);
    updateMoves();
  }

  /**
   * Updates the possible moves for the bishop.
   */
  @Override
  public void updateMoves() {
    ArrayList<Position> newMoveList = new ArrayList<>();
    for (int dirRow = -1; dirRow <= 1; dirRow += 2) {
      for (int dirCol = -1; dirCol <= 1; dirCol += 2) {
        addMovesInDirection(dirRow, dirCol, newMoveList);
      }
    }
    this.moves = newMoveList.toArray(new Position[0]);
  }
}
