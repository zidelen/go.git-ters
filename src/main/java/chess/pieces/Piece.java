package chess.pieces;

public class Piece {

  private String name;
  private Color color;
  private Position position;

  public Piece(Color color, Position position, String name) {
    this.color = color;
    this.position = position;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Color getColor() {
    return color;
  }

  public Position getPosition() {
    return position;
  }

  public enum Color {
    WHITE, BLACK
  }

  public static class Position {

    private int row;
    private char column;

    public Position(char column, int row) {
      this.row = row;
      this.column = column;
    }

    public int getRow() {
      return row;
    }

    public char getColumn() {
      return column;
    }
  }
}
