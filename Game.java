import javax.swing.*;

class Game extends JFrame {
    public Game() {
        this.setTitle("Run for your life !!"); // Set a fkin title
        startNewGame();
    }

    private void startNewGame() {
        Stage stage = new Stage(); // Create a new stage
        this.setContentPane(stage);// Add it to window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// Set close operation
        this.pack();// auto-sizing
    }

    public static void main(String args[]) {
       SwingUtilities.invokeLater(() -> new Game().setVisible(true)); // Create game as a new task and run it
    }
}