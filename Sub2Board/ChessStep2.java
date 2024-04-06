import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessStep2 {

    private static final String[] UNICODE_PIECES = {
            "\u2654", "\u2655", "\u2656", "\u2657", "\u2658", "\u2659",
            "\u265A", "\u265B", "\u265C", "\u265D", "\u265E", "\u265F"
    };

    private static JLabel selectedLabel = null; // Tracks the selected piece
    private static JPanel selectedPanel = null; // Tracks the panel of the selected piece
    private static Color lightColor = new Color(240, 217, 181);
    private static Color darkColor = new Color(181, 136, 99);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setPreferredSize(new Dimension(400, 400));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JLabel square = new JLabel(getPieceUnicode(row, col), SwingConstants.CENTER);
                square.setFont(new Font("Serif", Font.BOLD, 32));

                JPanel panelSquare = new JPanel(new BorderLayout());
                panelSquare.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                panelSquare.setBackground((row + col) % 2 == 0 ? lightColor : darkColor);

                panelSquare.add(square);
                boardPanel.add(panelSquare);

                // Add a mouse listener to each square
                panelSquare.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedPanel == null) { // No piece has been selected yet
                            selectedLabel = square;
                            selectedPanel = panelSquare;
                            panelSquare.setBorder(BorderFactory.createLineBorder(Color.RED));
                        } else {
                            // Move the piece if it's a different square
                            if (selectedPanel != panelSquare) {
                                square.setText(selectedLabel.getText()); // Move piece
                                selectedLabel.setText("");
                                selectedPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                            }
                            // Reset selection
                            selectedLabel = null;
                            selectedPanel = null;
                        }
                    }
                });
            }
        }
//NOTE THIS CURRENTLY HAS NO GAME LOGIC, ONLY PIECE MOVEMENT AND SOME TAKES
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String getPieceUnicode(int row, int col) {
        if (row == 0 || row == 7) {
            int offset = (row == 0) ? 0 : 6; // Determines white or black pieces
            switch (col) {
                case 0:
                case 7:
                    return UNICODE_PIECES[2 + offset]; // Rook
                case 1:
                case 6:
                    return UNICODE_PIECES[4 + offset]; // Knight
                case 2:
                case 5:
                    return UNICODE_PIECES[3 + offset]; // Bishop
                case 3:
                    return (row == 0) ? UNICODE_PIECES[1] : UNICODE_PIECES[7]; // Queen
                case 4:
                    return (row == 0) ? UNICODE_PIECES[0] : UNICODE_PIECES[6]; // King
            }
        } else if (row == 1 || row == 6) {
            return (row == 1) ? UNICODE_PIECES[5] : UNICODE_PIECES[11]; // Pawns
        }
        return ""; // Empty space for non-piece areas
    }
}