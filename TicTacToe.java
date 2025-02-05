import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;

    public TicTacToe() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setText(xTurn ? "X" : "O");
        button.setEnabled(false);
        xTurn = !xTurn;

        checkForWinner();
    }

    public void checkForWinner() {
        int[][] winPatterns = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // Rows
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // Columns
                { 0, 4, 8 }, { 2, 4, 6 } // Diagonals
        };

        for (int[] pattern : winPatterns) {
            String b1 = buttons[pattern[0]].getText();
            String b2 = buttons[pattern[1]].getText();
            String b3 = buttons[pattern[2]].getText();

            if (!b1.isEmpty() && b1.equals(b2) && b2.equals(b3)) {
                showWinner(b1);
                return;
            }
        }

        if (isBoardFull()) {
            JOptionPane.showMessageDialog(frame, "It's a Tie!");
            resetGame();
        }
    }

    private void showWinner(String winner) {
        JOptionPane.showMessageDialog(frame, winner + " wins!");
        resetGame();
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
