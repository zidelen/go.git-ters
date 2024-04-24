package edu.txstate.git.rrj29.go_git_ters.chess.board;

import edu.txstate.git.rrj29.go_git_ters.utils.Position;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ChessGUI {

  private static final Color lightColor = new Color(218, 183, 152);
  private static final Color darkColor = new Color(126, 94, 69);
  private static Board board; // The chessboard
  private static JLabel selectedPiece;
  private static JPanel selectedSpace;
  private static edu.txstate.git.rrj29.go_git_ters.utils.Color turn = edu.txstate.git.rrj29.go_git_ters.utils.Color.WHITE;

  // Create a method to resize ImageIcon

  public static void start(Board b) {

    // Open Window
    JFrame window = new JFrame("go.git-ters");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLayout(new BorderLayout());

    // Create Board Panel
    JPanel boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(8, 8));
    boardPanel.setPreferredSize(new Dimension(400, 400));

    for (int row = 7; row >=0; row--) {
      for (int col = 7; col >=0; col--) {

        JLabel square;
        if (b.board[row][col].occupied()) {

          square = new JLabel(b.board[row][col].piece().getimg(),
            SwingConstants.CENTER);
        } else {
          square = new JLabel();
        }

        JPanel panelSquare = new JPanel(new BorderLayout());
        panelSquare.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelSquare.setBackground(
          (row + col) % 2 == 0 ? lightColor : darkColor);

        panelSquare.add(square);
        boardPanel.add(panelSquare);

        // Add a mouse listener to each square
        panelSquare.addMouseListener(new MouseAdapter() {

          static String fromPOS;
          static String toPOS;
          @Override
          public void mouseClicked(MouseEvent e) {
            int currentRow = -1;
            int currentCol = -1;
            // Determine the row and column indices of the clicked square
            for (int i = 0; i < 8; i++) {
              for (int j = 0; j < 8; j++) {
                if (boardPanel.getComponent(i * 8 + j) == panelSquare) {
                  currentRow = Math.abs(i-8);
                  currentCol = Math.abs(j-8);
                  break;
                }
              }
            }

            Position selected = new Position(currentRow,currentCol);
            if (selectedSpace == null) { // No piece has been selected yet
              if (b.validFromPosition(selected.toString(),turn)){
                selectedPiece = square;
                selectedSpace = panelSquare;
                panelSquare.setBorder(BorderFactory.createLineBorder(Color.RED));
                this.fromPOS = selected.toString();
                System.out.println(fromPOS);
              }
            } else {
              this.toPOS = selected.toString();
              System.out.println(this.fromPOS+this.toPOS);
              boolean legalMove = b.movePiece(this.fromPOS+this.toPOS);

              if(legalMove){
                System.out.println("Moved");
                square.setIcon(selectedPiece.getIcon()); // Move piece
                square.setHorizontalAlignment(
                  SwingConstants.CENTER); // Center the piece horizontally
                square.setVerticalAlignment(
                  SwingConstants.CENTER); // Center the piece vertically
                selectedPiece.setIcon(null);
                selectedSpace.setBorder(
                  BorderFactory.createLineBorder(Color.GRAY));
                nextTurn();
              }
              // Reset selection
              selectedPiece = null;
              selectedSpace = null;
              fromPOS = null;
              toPOS = null;
            }
          }
        });
      }
    }

    window.add(boardPanel, BorderLayout.CENTER);

    // Set fixed window size
    window.setSize(800, 800); // Set your desired fixed size here
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setVisible(true);
  }
  private static void nextTurn() {
    turn = (turn == edu.txstate.git.rrj29.go_git_ters.utils.Color.WHITE) ? edu.txstate.git.rrj29.go_git_ters.utils.Color.BLACK : edu.txstate.git.rrj29.go_git_ters.utils.Color.WHITE;
  }
}

