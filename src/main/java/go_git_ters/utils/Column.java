package go_git_ters.utils;

/**
 * Enum representing columns on a chessboard.
 */
public enum Column {
  a(1), b(2), c(3), d(4), e(5), f(6), g(7), h(8);

  private final int columnNumber; // The numeric value of the column

  /**
   * Constructs a column with the specified numeric value.
   *
   * @param columnNumber The numeric value of the column
   */
  Column(int columnNumber) {
    this.columnNumber = columnNumber;
  }

  /**
   * Converts a numeric column value to its corresponding character
   * representation.
   *
   * @param columnNumber The numeric value of the column
   * @return The character representation of the column
   * @throws IllegalArgumentException if the column number is invalid
   */
  public static char intToChar(int columnNumber) {
    if (columnNumber >= 1 && columnNumber <= values().length) {
      return values()[columnNumber - 1].name().charAt(0);
    } else {
      throw new IllegalArgumentException("Invalid Column Number");
    }
  }

  /**
   * Converts a character column representation to its corresponding numeric
   * value.
   *
   * @param columnChar The character representation of the column
   * @return The numeric value of the column
   * @throws IllegalArgumentException if the column character is invalid
   */
  public static int charToInt(char columnChar) {
    for (Column column : values()) {
      if (column.name().charAt(0) == columnChar) {
        return column.column();
      }
    }
    throw new IllegalArgumentException("Invalid Column Character");
  }

  /**
   * Returns the numeric value of the column.
   *
   * @return The numeric value of the column
   */
  public int column() {
    return this.columnNumber;
  }
}
