/**
 * Represents a chessboard with pieces and squares.
 */
package go_git_ters.chess.board;


import go_git_ters.chess.pieces.Piece;
import go_git_ters.chess.pieces.playable.Bishop;
import go_git_ters.chess.pieces.playable.King;
import go_git_ters.chess.pieces.playable.Knight;
import go_git_ters.chess.pieces.playable.Pawn;
import go_git_ters.chess.pieces.playable.Queen;
import go_git_ters.chess.pieces.playable.Rook;
import go_git_ters.utils.Color;
import go_git_ters.utils.Column;
import go_git_ters.utils.Position;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Represents a chessboard.
 */
public class Board {

  private static final int BOARD_DIMENSION = 8;

  final Square[][] board;
  final ArrayList<Piece> Captures;

  /**
   * Constructs a new chessboard and initializes it with pieces.
   */
  public Board() {
    this.Captures = new ArrayList<Piece>();
    this.board = new Square[BOARD_DIMENSION][BOARD_DIMENSION];
    initializePieces();
    updatePieceMoves();
  }

  // Methods for updating the board state

  /**
   * Transforms the perspective of the board depending on the color.
   */
  private static int viewPoint(int row, int PERSPECTIVE) {
    return Math.abs(row - PERSPECTIVE);
  }

  /**
   * Updates the possible moves for all pieces on the board.
   */
  public void updatePieceMoves() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board[i][j].occupied()) {
          board[i][j].piece().updateMoves();
        }
      }
    }
  }

  /**
   * Checks if the specified position is a valid starting position for a move of
   * the given color.
   */
  public boolean validFromPosition(String string, Color color) {
    if (!Position.validPos(string)) {
      return false;
    } else {
      Position fromPos = Position.strToPos(string);
      try {
        Piece piece = getPiece(fromPos);
        if (!piece.hasMoves()) {
          return false;
        }
        return piece.color() == color;
      } catch (NullPointerException e) {
        return false;
      }
    }
  }

  /**
   * Initializes the board with pieces.
   */
  private void initializePieces() {
    for (int row = 0; row < BOARD_DIMENSION; row++) {
      for (int column = 0; column < BOARD_DIMENSION; column++) {
        board[row][column] = new Square(
          ((row + column) % 2 == 0) ? Color.BLACK : Color.WHITE);
      }
    }

    for (int row = 0; row < BOARD_DIMENSION; row++) {
      for (int column = 0; column < BOARD_DIMENSION; column++) {
        board[row][column].setPiece(
          createExpectedPiece(new Position(row + 1, column + 1)));
      }
    }
  }

  private Piece createExpectedPiece(Position position) {
    if (position.row() <= 6 && position.row() >= 3) {
      return null;
    }
    Color color = (position.row() > 4.5) ? Color.BLACK : Color.WHITE;

    String path = "src/main/resources/";

    if (position.row() == 2 || position.row() == 7) {
      return new Pawn(path + color.getPrefix() + "-pawn.png", color, position,
        this);
    } else {
      return switch (position.column()) {
        case 1, 8 ->
          new Rook(path + color.getPrefix() + "-rook.png", color, position,
            this);
        case 2, 7 ->
          new Knight(path + color.getPrefix() + "-knight.png", color, position,
            this);
        case 3, 6 ->
          new Bishop(path + color.getPrefix() + "-bishop.png", color, position,
            this);
        case 4 ->
          new Queen(path + color.getPrefix() + "-queen.png", color, position,
            this);
        case 5 ->
          new King(path + color.getPrefix() + "-king.png", color, position,
            this);
        default -> null;
      };
    }
  }

  /**
   * Displays the current state of the board.
   */
  public void display(OutputStream output, Color color) {
    final int PERSPECTIVE = (color == Color.WHITE) ? 0 : 9;
    PrintStream out = new PrintStream(output);

    for (int row = 9; row > -1; row--) {
      for (int column = 0; column < 10; column++) {

        row = viewPoint(row, PERSPECTIVE);
        column = viewPoint(column, PERSPECTIVE);

        if (row == 0 || row == 9) {
          if (column != 0 && column != 9) {
            if (PERSPECTIVE == 0) {
              char columnLabel = Column.intToChar(column);
              if (row == 0) {
                out.print(" " + columnLabel + " ");
              } else {
                out.print("  " + columnLabel);
              }
            } else {
              char columnLabel = Column.intToChar(column);
              if (row == 9) {
                out.print(" " + columnLabel + " ");
              } else {
                out.print("  " + columnLabel);
              }
            }
          } else {
            out.print("  ");
          }
        } else {
          if (column == 0 || column == 9) {
            out.printf(" " + row);
          } else {
            Square currentSquare = this.board[row - 1][column - 1];
            if (!currentSquare.occupied()) {
              out.print((currentSquare.color() == Color.WHITE ? "   " : " ##"));
            } else {
              out.print(" " + currentSquare.piece().name());
            }
          }
        }

        row = viewPoint(row, PERSPECTIVE);
        column = viewPoint(column, PERSPECTIVE);
      }
      out.println();
    }

    out.flush();
  }

  /**
   * Retrieves the piece at the specified position.
   */
  public Piece getPiece(Position position) {
    if (!isValidPosition(position)) {
      return null;
    }
    return board[position.row() - 1][position.column() - 1].piece();
  }

  /**
   * Moves a piece from one position to another on the board.
   */
  public boolean movePiece(String string) {
    try {
      Position from = Position.strToPos(string.substring(0, 2));
      Position to = Position.strToPos(string.substring(2, 4));

      Piece pieceToMove = getPiece(from);

      if (legalMove(pieceToMove, to)) {
        pieceToMove.move(to);

        clearSpace(from);

        updateSpace(to, pieceToMove);

        updatePieceMoves();
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

  // Helper methods for managing board state

  /**
   * Checks if a move from a piece to a specified position is legal.
   */
  private boolean legalMove(Piece from, Position to) {
    for (Position position : from.possibleMoves()) {
      if (Position.posEquals(to, position)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Clears a space on the board by removing the piece occupying it.
   */
  private void clearSpace(Position position) {
    board[position.row() - 1][position.column() - 1].setPiece(null);
  }

  // Other helper methods

  /**
   * Updates a space on the board with a new piece.
   */
  private void updateSpace(Position position, Piece piece) {
    if (board[position.row() - 1][position.column() - 1].piece() != null) {
      board[position.row() - 1][position.column() - 1].piece().captured();
    }

    board[position.row() - 1][position.column() - 1].setPiece(piece);
  }

  /**
   * Checks if a given position is valid on the board.
   */
  public boolean isValidPosition(Position position) {
    int row = position.row();
    int col = position.column();
    return row > 0 && row <= BOARD_DIMENSION && col > 0
      && col <= BOARD_DIMENSION;
  }

  /**
   * Checks if a given position is occupied by an enemy piece.
   */
  public boolean isEnemyPiece(Color color, Position position) {
    try {
      Piece piece = getPiece(position);
      return piece != null && piece.color() != color;
    } catch (NullPointerException e) {
      return false;
    }
  }

  /**
   * Checks if a given position is empty.
   */
  public boolean isSquareEmpty(Position position) {
    try {
      Piece piece = getPiece(position);
      return piece == null;
    } catch (NullPointerException e) {
      return true;
    }
  }

  public boolean isValidPosition(int newRow, int newCol) {
    return isValidPosition(new Position(newRow, newCol));
  }

  public boolean isCheckmate(Color color) {
    return false;
  }

  /**
   * Represents a square on the chessboard.
   */
  public static class Square {

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
}
