package go_git_ters.chess.pieces.playable;

import go_git_ters.chess.board.Board;
import go_git_ters.chess.pieces.Piece;
import go_git_ters.utils.Color;
import go_git_ters.utils.Position;
import java.util.ArrayList;

/**
 * Represents a queen piece in chess.
 */
public class Queen extends Piece {

  /**
   * Constructs a queen with the specified color, position, and board.
   *
   * @param color    The color of the queen
   * @param position The position of the queen
   * @param board    The chessboard
   */
  public Queen(String path, Color color, Position position, Board board) {
    super(path, color, position, color.getPrefix() + "Q", board);
    updateMoves();
  }

  /**
   * Updates the possible moves for the queen.
   */
  @Override
  public void updateMoves() {
    ArrayList<Position> newMoveList = new ArrayList<>();
    for (int dirRow = -1; dirRow <= 1; dirRow++) {
      for (int dirCol = -1; dirCol <= 1; dirCol++) {
        if (dirRow != 0 || dirCol != 0) {
          addMovesInDirection(dirRow, dirCol, newMoveList);
        }
      }
    }
    for (int dirRow = -1; dirRow <= 1; dirRow += 2) {
      for (int dirCol = -1; dirCol <= 1; dirCol += 2) {
        addMovesInDirection(dirRow, dirCol, newMoveList);
      }
    }
    this.moves = newMoveList.toArray(new Position[0]);
  }
}
