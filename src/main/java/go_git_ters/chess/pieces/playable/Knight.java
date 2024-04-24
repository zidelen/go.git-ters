package go_git_ters.chess.pieces.playable;

import go_git_ters.chess.board.Board;
import go_git_ters.chess.pieces.Piece;
import go_git_ters.utils.Color;
import go_git_ters.utils.Position;
import java.util.ArrayList;

/**
 * Represents a knight piece in chess.
 */
public class Knight extends Piece {

  /**
   * Constructs a knight with the specified color, position, and board.
   *
   * @param color    The color of the knight
   * @param position The position of the knight
   * @param board    The chessboard
   */
  public Knight(String path, Color color, Position position, Board board) {
    super(path, color, position, color.getPrefix() + "N", board);
    updateMoves();
  }

  /**
   * Updates the possible moves for the knight.
   */
  @Override
  public void updateMoves() {
    ArrayList<Position> newMoveList = new ArrayList<>();
    int[] dRow = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] dCol = {1, 2, 2, 1, -1, -2, -2, -1};
    for (int i = 0; i < 8; i++) {
      int newRow = position.getRow() + dRow[i];
      int newCol = position.getCol() + dCol[i];
      if (board.isValidPosition(newRow, newCol)) {
        Position newPos = new Position(newRow, newCol);
        Piece pieceAtPos = board.getPiece(newPos);
        if (pieceAtPos == null || pieceAtPos.getColor() != this.color) {
          newMoveList.add(newPos);
        }
      }
    }
    this.moves = newMoveList.toArray(new Position[0]);
  }
}
