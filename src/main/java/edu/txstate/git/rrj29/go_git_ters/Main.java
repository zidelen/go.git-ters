package edu.txstate.git.rrj29.go_git_ters;

import edu.txstate.git.rrj29.go_git_ters.chess.Game;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    Game chess = new Game();
    chess.start();
    chess.play();
  }
}
