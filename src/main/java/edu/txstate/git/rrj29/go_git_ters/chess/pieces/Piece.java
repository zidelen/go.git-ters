package edu.txstate.git.rrj29.go_git_ters.chess.Pieces;

import edu.txstate.git.rrj29.go_git_ters.chess.board.Board;
import edu.txstate.git.rrj29.go_git_ters.utils.Color;
import edu.txstate.git.rrj29.go_git_ters.utils.Position;
import java.util.ArrayList;

/**
 * Abstract class representing a chess piece.
 */
public abstract class Piece {

  protected final String name; // The name of the piece
  protected final Color color; // The color of the piece
  protected Board board; // The chessboard
  protected Position position; // The position of the piece
  protected Position[] moves; // Possible moves for the piece

  /**
   * Constructs a piece with the specified color, position, name, and board.
   *
   * @param color    The color of the piece
   * @param position The position of the piece
   * @param name     The name of the piece
   * @param board    The chessboard
   */
  public Piece(Color color, Position position, String name, Board board) {
    this.color = color;
    this.position = position;
    this.name = name;
    this.board = board;
    this.moves = new Position[0];
  }

  /**
   * Returns the name of the piece.
   *
   * @return The name of the piece
   */
  public String name() {
    return name;
  }

  /**
   * Returns the position of the piece.
   *
   * @return The position of the piece
   */
  public Position position() {
    return position;
  }

  /**
   * Returns the color of the piece.
   *
   * @return The color of the piece
   */
  public Color color() {
    return color;
  }

  /**
   * Returns an array of possible moves for the piece.
   *
   * @return An array of possible moves for the piece
   */
  public Position[] possibleMoves() {
    return moves;
  }

  /**
   * Sets the piece as captured.
   */
  public void captured() {
    this.position = null;
    this.moves = null;
  }

  /**
   * Updates the possible moves for the piece.
   */
  public void updateMoves() {
    this.moves = null;
  }

  /**
   * Moves the piece to the specified position.
   *
   * @param newPos The new position to move the piece to
   * @return True if the move is valid, otherwise false
   */
  public boolean move(Position newPos) {
    for (Position move : possibleMoves()) {
      if (Position.posEquals(move, newPos)) {
        this.position = newPos;
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the row of the piece's position.
   *
   * @return The row of the piece's position
   */
  protected int row() {
    return this.position.row();
  }

  /**
   * Returns the column of the piece's position.
   *
   * @return The column of the piece's position
   */
  protected int column() {
    return this.position.column();
  }

  /**
   * Checks if the piece has possible moves.
   *
   * @return True if the piece has possible moves, otherwise false
   */
  public boolean hasMoves() {
    try {
      return moves.length > 0;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Adds possible moves in a specified direction to the move list.
   *
   * @param dirRow    The row direction
   * @param dirCol    The column direction
   * @param moveList  The list of possible moves
   */
  protected void addMovesInDirection(int dirRow, int dirCol, ArrayList<Position> moveList) {
    int newRow = position.getRow() + dirRow;
    int newCol = position.getCol() + dirCol;
    while (board.isValidPosition(newRow, newCol)) {
      Position newPos = new Position(newRow, newCol);
      Piece pieceAtPos = board.getPiece(newPos);
      if (pieceAtPos == null) {
        moveList.add(newPos);
      } else if (pieceAtPos.getColor() != this.color) {
        moveList.add(newPos);
        break;
      } else {
        break;
      }
      newRow += dirRow;
      newCol += dirCol;
    }
  }

  /**
   * Returns the color of the piece.
   *
   * @return The color of the piece
   */
  public Color getColor() {
    return color;
  }
}
