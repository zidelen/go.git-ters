package edu.txstate.git.rrj29.go_git_ters.chess;

import edu.txstate.git.rrj29.go_git_ters.chess.board.Board;
import edu.txstate.git.rrj29.go_git_ters.chess.pieces.Piece;
import edu.txstate.git.rrj29.go_git_ters.chess.board.ChessGUI;
import edu.txstate.git.rrj29.go_git_ters.utils.Color;
import edu.txstate.git.rrj29.go_git_ters.utils.Position;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents a chess game.
 */
public class Game {

  private PrintStream OUTPUT = System.out; // Output stream for displaying messages
  private Scanner INPUT = new Scanner(System.in); // Input stream for user input
  private Board board; // The chessboard
  private Player White; // Player with white pieces
  private Player Black; // Player with black pieces
  private Color turn; // Current turn

  /**
   * Starts a new game with default input and output streams.
   */
  public void start() {
    start(System.out, System.in);
  }

  /**
   * Starts a new game with specified input and output streams.
   *
   * @param printStream The output stream for displaying messages
   * @param inputStream The input stream for user input
   */
  public void start(PrintStream printStream, InputStream inputStream) {
    this.turn = Color.WHITE;
    this.board = new Board();
    this.White = new Player(Color.WHITE, this.board);
    this.Black = new Player(Color.BLACK, this.board);
    this.OUTPUT = printStream;
    this.INPUT = new Scanner(inputStream);
  }

  /**
   * Starts playing the game.
   */
  public void playGUI() {
    if (board == null) {
      throw new GameNotStartedException("The game has not been started. Call the start() method first.");
    }
    ChessGUI.start(board);
  }
  /**
   * Starts playing the game.
   */
  public void playCLI() {
    if (board == null) {
      throw new GameNotStartedException("The game has not been started. Call the start() method first.");
    }
    while (true) {
      boolean gameEnd = board.isCheckmate(Color.BLACK) || board.isCheckmate(Color.WHITE);
      if (gameEnd) {
        break;
      }

      for (int i = 0; i < 20; i++) {
        OUTPUT.println();
      }

      printGameState();
      playerMove();
    }
  }



  /**
   * Executes a player move.
   */
  private void playerMove() {
    OUTPUT.print("Enter position to move from (ex. e5, d8, a1, ...): ");
    String fromPOS = INPUT.next();
    int mistakes = 0;
    while (!this.board.validFromPosition(fromPOS, this.turn)) {
      if (mistakes < 2) {
        OUTPUT.print("Invalid Input!! Enter position to move from: ");
        fromPOS = INPUT.next();
        mistakes++;
      } else {
        printValidFrom();
        OUTPUT.print("Enter position to move from: ");
        fromPOS = INPUT.next();
      }
    }

    OUTPUT.print("Enter position to move to (ex. e5, d8, a1, ...): ");
    String toPos = INPUT.next();
    mistakes = 0;
    while (true) {
      boolean legalMove = board.movePiece(fromPOS + toPos);
      if (legalMove) {
        break;
      }
      if (mistakes < 2) {
        OUTPUT.print("Invalid Input!! Enter position to move to: ");
        toPos = INPUT.next();
        mistakes++;
      } else {
        printValidTo(board.getPiece(Position.strToPos(fromPOS)));
        OUTPUT.print("Enter position to move to: ");
        toPos = INPUT.next();
      }
    }
    nextTurn();
  }

  /**
   * Prints the current game state.
   */
  private void printGameState() {
    OUTPUT.println("Current board");
    board.display(OUTPUT, turn);
    OUTPUT.println(" **" + turn.toString() + "'s Turn** ");
  }

  /**
   * Switches to the next turn.
   */
  private void nextTurn() {
    this.turn = (this.turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  /**
   * Prints valid moves for the piece to move to.
   *
   * @param from The piece to move
   */
  private void printValidTo(Piece from) {
    OUTPUT.println("Valid Options:");
    OUTPUT.print("{ ");
    Position[] moves = from.possibleMoves();
    for (Position position : moves) {
      OUTPUT.print(position.toString() + " ");
    }
    OUTPUT.println("}");
  }

  /**
   * Prints valid positions for the player to move from.
   */
  private void printValidFrom() {
    OUTPUT.println("Valid Options:");
    OUTPUT.print("{ ");
    for (Piece piece : (turn == Color.WHITE) ? White.pieces : Black.pieces) {
      if (piece.hasMoves()) {
        OUTPUT.print(piece.position().toString() + " ");
      }
    }
    OUTPUT.println("}");
  }

  /**
   * Exception thrown when the game is not started.
   */
  public static class GameNotStartedException extends RuntimeException {

    public GameNotStartedException(String message) {
      super(message);
    }
  }
}
