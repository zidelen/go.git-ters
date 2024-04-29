package go_git_ters.utils;


/**
 * Represents a position on a chessboard.
 */
public class Position {

  private final int row; // The row of the position
  private final int column; // The column of the position

  /**
   * Constructs a position with the specified row and column.
   *
   * @param row    The row of the position
   * @param column The column of the position
   */
  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /**
   * Checks if two positions are equal.
   *
   * @param pos1 The first position
   * @param pos2 The second position
   * @return True if the positions are equal, otherwise false
   */
  public static boolean posEquals(Position pos1, Position pos2) {
    return (pos1.column == pos2.column && pos1.row == pos2.row);
  }

  /**
   * Checks if a string represents a valid position.
   *
   * @param string The string representing the position (e.g., "a1")
   * @return True if the string represents a valid position, otherwise false
   */
  public static boolean validPos(String string) {
    if (string.length() != 2) {
      return false;
    }
    int columnNum = 0;
    try {
      columnNum = Column.charToInt(string.charAt(0));
    } catch (IllegalArgumentException e) {
      return false;
    }
    int rowNum = Character.getNumericValue(string.charAt(1));

    return columnNum >= 1 && columnNum <= 8 && rowNum >= 1 && rowNum <= 8;
  }

  /**
   * Converts a string representation of a position to a Position object.
   *
   * @param string The string representation of the position (e.g., "a1")
   * @return The Position object representing the position
   * @throws IllegalArgumentException if the string does not represent a valid
   *                                  position
   */
  public static Position strToPos(String string) {
    if (validPos(string)) {
      int row = Character.getNumericValue(string.charAt(1));
      int column = Column.charToInt(string.charAt(0));
      return new Position(row, column);
    } else {
      throw new IllegalArgumentException("Invalid position string");
    }
  }

  /**
   * Checks if this position is equal to another position.
   *
   * @param pos The position to compare with
   * @return True if the positions are equal, otherwise false
   */
  public boolean equals(Position pos) {
    return Position.posEquals(this, pos);
  }

  /**
   * Returns the row of the position.
   *
   * @return The row of the position
   */
  public int row() {
    return row;
  }

  /**
   * Returns the column of the position.
   *
   * @return The column of the position
   */
  public int column() {
    return column;
  }

  /**
   * Creates a new position by modifying the row of this position.
   *
   * @param change The amount to change the row by
   * @return The new position with the modified row
   */
  public Position modifyRow(int change) {
    return new Position(this.row() + change, this.column());
  }

  /**
   * Creates a new position by modifying the column of this position.
   *
   * @param change The amount to change the column by
   * @return The new position with the modified column
   */
  public Position modifyColumn(int change) {
    return new Position(this.row(), this.column() + change);
  }

  /**
   * Creates a new position by modifying both the row and column of this
   * position.
   *
   * @param rowChange    The amount to change the row by
   * @param columnChange The amount to change the column by
   * @return The new position with the modified row and column
   */
  public Position modifyPos(int rowChange, int columnChange) {
    return new Position(this.row() + rowChange, this.column() + columnChange);
  }

  /**
   * Returns a string representation of the position.
   *
   * @return The string representation of the position (e.g., "a1")
   */
  @Override
  public String toString() {
    return Column.intToChar(this.column) + Integer.toString(this.row);
  }

  /**
   * Returns the column of the position.
   *
   * @return The column of the position
   */
  public int getCol() {
    return column;
  }

  /**
   * Returns the row of the position.
   *
   * @return The row of the position
   */
  public int getRow() {
    return row;
  }
}
