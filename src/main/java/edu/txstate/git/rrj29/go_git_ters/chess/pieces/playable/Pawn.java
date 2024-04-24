package edu.txstate.git.rrj29.go_git_ters.chess.pieces.playable;

import edu.txstate.git.rrj29.go_git_ters.chess.board.Board;
import edu.txstate.git.rrj29.go_git_ters.chess.pieces.Piece;
import edu.txstate.git.rrj29.go_git_ters.utils.Color;
import edu.txstate.git.rrj29.go_git_ters.utils.Position;
import java.util.ArrayList;

/**
 * Represents a pawn piece in chess.
 */
public class Pawn extends Piece {

  private final int DIRECTION;

  /**
   * Constructs a pawn with the specified color, position, and board.
   *
   * @param color    The color of the pawn
   * @param position The position of the pawn
   * @param board    The chessboard
   */
  public Pawn(String path, Color color, Position position, Board board) {
    super(path,color, position, color.getPrefix() + "P", board);
    DIRECTION = (this.color == Color.WHITE) ? 1 : -1;
    updateMoves();
  }

  /**
   * Updates the possible moves for the pawn.
   */
  @Override
  public void updateMoves() {
    ArrayList<Position> newMoveList = new ArrayList<>();

    int currentRow = this.row();
    int currentCol = this.column();

    int direction = (this.color == Color.WHITE) ? 1 : -1;

    Position oneSquareForward = this.position.modifyRow(direction);
    if (board.isValidPosition(oneSquareForward) && board.getPiece(oneSquareForward) == null) {
      newMoveList.add(oneSquareForward);

      int startingRow = (this.color == Color.WHITE) ? 2 : 7;
      if (currentRow == startingRow) {
        Position twoSquaresForward = this.position.modifyRow(2 * direction);
        if (board.isValidPosition(twoSquaresForward) && board.getPiece(twoSquaresForward) == null) {
          newMoveList.add(twoSquaresForward);
        }
      }
    }

    Position leftDiagonal = this.position.modifyPos(direction, -1);
    if (board.isValidPosition(leftDiagonal)) {
      Piece pieceAtLeftDiagonal = board.getPiece(leftDiagonal);
      if (pieceAtLeftDiagonal != null && pieceAtLeftDiagonal.color() != this.color) {
        newMoveList.add(leftDiagonal);
      }
    }

    Position rightDiagonal = this.position.modifyPos(direction, 1);
    if (board.isValidPosition(rightDiagonal)) {
      Piece pieceAtRightDiagonal = board.getPiece(rightDiagonal);
      if (pieceAtRightDiagonal != null && pieceAtRightDiagonal.color() != this.color) {
        newMoveList.add(rightDiagonal);
      }
    }

    this.moves = newMoveList.toArray(new Position[0]);
  }
}
