import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Game extends JFrame {
    public Game() {
        this.setTitle("Run for your life !!"); // Set a fkin title
        startNewGame();
    }

    private JButton initButton(){ // Create a reset button that startNewGame
        JButton Reset = new JButton("Reset");
        Reset.setPreferredSize(new Dimension(200,50));
        Reset.setBackground(new Color(255, 120, 0));
        Reset.setVisible(true);
        Reset.addMouseListener((Press) (e) -> this.startNewGame());
        return Reset;
    }

    private Stage stage;
    private void startNewGame() {
        stage = new Stage(); // Create a new stage
        stage.setLayout(new BorderLayout());
        stage.add(initButton(),BorderLayout.SOUTH); // Place the reset button on bottom of the layout
        this.setContentPane(stage);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String args[]) {
       SwingUtilities.invokeLater(() -> new Game().setVisible(true)); // Create game as a new task and run it
    }
}