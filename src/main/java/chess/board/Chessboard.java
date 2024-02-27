package chess.board;

public class Chessboard {

  /*** The perspective of the chessboard, indicating the color of the bottom-left* cell. Should be 0 for White Perspective (Default) or 9 for Black* Perspective.*/
  public static final int PERSPECTIVE = 0;
  public static String[][] boardState = {
    {"wR", "wN", "wB", "wQ", "wK", "wB", "wN", "wR"},
    {"wp", "wp", "wp", "wp", "wp", "wp", "wp", "wp"},
    {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
    {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
    {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
    {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
    {"bp", "bp", "bp", "bp", "bp", "bp", "bp", "bp"},
    {"bR", "bN", "bB", "bQ", "bK", "bB", "bN", "bR"}};

  public static void printBoard() {
    System.out.print(
      "'  ' - Indicates White Space\n'  ' - Indicates Black Space\n");
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
            if (boardState[(Math.abs(PERSPECTIVE - (9 - row))) - 1][
              (Math.abs(PERSPECTIVE - column)) - 1].equals("  ")) {
              System.out.printf("%3s", (row + column) % 2 == 0 ? "" : "##");
            } else {
              System.out.printf("%3s",
                boardState[(Math.abs(PERSPECTIVE - (9 - row))) - 1][
                  (Math.abs(PERSPECTIVE - column)) - 1]);
            }
          }
        }
      }
      System.out.print("\n");
    }
    System.out.print(
      "'  ' - Indicates White Space\n'##' - Indicates Black Space\n");
  }

  public enum Column {
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