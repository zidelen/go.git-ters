package edu.txstate.git.rrj29.go_git_ters.utils;

/**
 * Enum representing colors in chess: BLACK and WHITE.
 */
public enum Color {
  BLACK, WHITE;

  /**
   * Returns a string representation of the color.
   *
   * @return "Black" for BLACK and "White" for WHITE
   */
  @Override
  public String toString() {
    return switch (this) {
      case BLACK -> "Black";
      case WHITE -> "White";
    };
  }

  /**
   * Returns the prefix associated with the specified color.
   *
   * @param color The color for which the prefix is required
   * @return "w" for WHITE and "b" for BLACK
   */
  public static String getPrefix(Color color) {
    return (color == WHITE) ? "w" : "b";
  }

  /**
   * Returns the prefix associated with this color.
   *
   * @return "w" for WHITE and "b" for BLACK
   */
  public String getPrefix() {
    return (this == WHITE) ? "w" : "b";
  }
}
