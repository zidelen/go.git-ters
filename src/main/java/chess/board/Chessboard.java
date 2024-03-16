package chess.board;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class Chessboard {

  /*** The perspective of the chessboard, indicating the color of the bottom-left* cell. Should be 0 for White Perspective (Default) or 9 for Black** Perspective.**/
  public static final int PERSPECTIVE = 0;

  public Piece[][] boardState;


  public Chessboard() {
    boardState = new Piece[8][8];
    initializeStartingBoard();
  }

  public void display() {
    System.out.print(
      "'  ' - Indicates White Space\n'##' - Indicates Black Space\n");
    for (int row = 0; row < 10; row++) {
      if (row == 0 || row == 9) {
        for (int column = 0; column < 10; column++) {
          if (column == 0 || column == 9) {
            System.out.printf("%3s", "");
          } else {
            System.out.printf("%3s",
              Column.getLetterForColumn(Math.abs(PERSPECTIVE - column)));
          }
        }
      } else {
        for (int column = 0; column < 10; column++) {
          if (column == 0 || column == 9) {
            System.out.printf("%3s", (Math.abs(PERSPECTIVE - (9 - row))));
          } else {
            if (boardState[(Math.abs(PERSPECTIVE - (row))) - 1][
              (Math.abs(PERSPECTIVE - column)) - 1] == (null)) {
              System.out.printf("%3s", (row + column) % 2 == 0 ? "" : "##");
            } else {
              System.out.printf("%3s",
                boardState[(Math.abs(PERSPECTIVE - (row))) - 1][
                  (Math.abs(PERSPECTIVE - column)) - 1].getName());
            }
          }
        }
      }
      System.out.print("\n");
    }
    System.out.print(
      "'  ' - Indicates White Space\n'##' - Indicates Black Space\n");
  }

  // Modify initializeStartingBoard method in Chessboard.java
  public void initializeStartingBoard() {
    // Add white pawns in the second row
    for (int i = 0; i < 8; i++) {
      boardState[1][i] = new Pawn(Color.BLACK,
        new Piece.Position(Column.getLetterForColumn(i + 1), 2));
    }

    // Add black pawns in the seventh row
    for (int i = 0; i < 8; i++) {
      boardState[6][i] = new Pawn(Color.WHITE,
        new Piece.Position(Column.getLetterForColumn(i + 1), 7));
    }

    // Add white pieces in the first row
    boardState[0][0] = new Rook(Piece.Color.BLACK, new Piece.Position('a', 1));
    boardState[0][1] = new Knight(Piece.Color.BLACK,
      new Piece.Position('b', 1));
    boardState[0][2] = new Bishop(Piece.Color.BLACK,
      new Piece.Position('c', 1));
    boardState[0][3] = new Queen(Piece.Color.BLACK, new Piece.Position('d', 1));
    boardState[0][4] = new King(Piece.Color.BLACK, new Piece.Position('e', 1));
    boardState[0][5] = new Bishop(Piece.Color.BLACK,
      new Piece.Position('f', 1));
    boardState[0][6] = new Knight(Piece.Color.BLACK,
      new Piece.Position('g', 1));
    boardState[0][7] = new Rook(Piece.Color.BLACK, new Piece.Position('h', 1));

    // Add black pieces in the eighth row
    boardState[7][0] = new Rook(Piece.Color.WHITE, new Piece.Position('a', 8));
    boardState[7][1] = new Knight(Piece.Color.WHITE,
      new Piece.Position('b', 8));
    boardState[7][2] = new Bishop(Piece.Color.WHITE,
      new Piece.Position('c', 8));
    boardState[7][3] = new Queen(Piece.Color.WHITE, new Piece.Position('d', 8));
    boardState[7][4] = new King(Piece.Color.WHITE, new Piece.Position('e', 8));
    boardState[7][5] = new Bishop(Piece.Color.WHITE,
      new Piece.Position('f', 8));
    boardState[7][6] = new Knight(Piece.Color.WHITE,
      new Piece.Position('g', 8));
    boardState[7][7] = new Rook(Piece.Color.WHITE, new Piece.Position('h', 8));
  }


  public static enum Column {
    a, b, c, d, e, f, g, h;

    public static char getLetterForColumn(int columnNumber) {
      if (columnNumber >= 1 && columnNumber <= values().length) {
        return values()[columnNumber - 1].name().charAt(0);
      } else {
        throw new IllegalArgumentException("Invalid Column Number");
      }
    }


  }
}