package edu.txstate.git.rrj29.go_git_ters.chess.pieces.playable;

import edu.txstate.git.rrj29.go_git_ters.chess.board.Board;
import edu.txstate.git.rrj29.go_git_ters.chess.pieces.Piece;
import edu.txstate.git.rrj29.go_git_ters.utils.Color;
import edu.txstate.git.rrj29.go_git_ters.utils.Position;
import java.util.ArrayList;

/**
 * Represents a king piece in chess.
 */
public class King extends Piece {

  /**
   * Constructs a king with the specified color, position, and board.
   *
   * @param color    The color of the king
   * @param position The position of the king
   * @param board    The chessboard
   */
  public King(String path, Color color, Position position, Board board) {
    super(path,color, position, color.getPrefix() + "K", board);
    updateMoves();
  }

  /**
   * Updates the possible moves for the king.
   */
  @Override
  public void updateMoves() {
    ArrayList<Position> newMoveList = new ArrayList<>();
    int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
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
