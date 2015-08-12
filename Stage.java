import java.awt.*;
import javax.swing.*;

class Stage extends JPanel {
    public Ball player;
    String status ="Play";
    int
            score = 0,
            delay = 5,
            TOP_BORDER = 49,
            BOTTOM_BORDER = 331,
            LEFT_BORDER = 49,
            RIGHT_BORDER = 331,
            TOP_WALL = 81,
            BOTTOM_WALL = 300,
            LEFT_WALL = 81,
            RIGHT_WALL = 300;
    
    Stage() {
        player = new Ball(75,75);
        setPreferredSize(new Dimension(400, 400));
        setBackground(new Color(255, 255, 255));
        addMouseListener((Press) (e) -> {
            if (e.getButton() == 1) player.turn();
        });
    }
    
    @Override
    public void paintComponent(Graphics g){
        switch (status) {
            case "Play":
                // DRAW THE FIELD
                super.paintComponent(g);
                g.setColor(new Color(220, 123, 111));
                g.drawRect(50, 50, 300, 300);
                g.fillRect(100, 100, 200, 200);
                g.setColor(new Color(0, 255, 0));
                // DRAW THE SCORE AND SPEED
                g.drawString("Speed: " + Integer.toString(player.speed), 35, 40);
                g.drawString("Score: " + Integer.toString(score), 35, 20);
                // BALL MOVEMENT
                moveBall.start();
                player.draw(g);
                this.CheckCollide();
                evaluate.start();
                randomMove.start();
                // REPAINT
                repaint(player.X, player.Y, 20, 20);//Repaint Ball
                repaint(35, 0, 150, 200);// SCOREBOARD
                break;
            case "Game Over":  // THIS IS A METHOD TO CLEAR THE WHOLE SCREEN
                super.paintComponent(g);
                repaint();
                status = "Result";
                break;
            case "Result": // Show the player result
                super.paintComponent(g);
                g.drawString("Speed: " + Integer.toString(player.speed), 120, 140); // Speed
                g.drawString("Score: " + Integer.toString(score), 120, 160); // Score
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.setColor(new Color(255, 0, 10));
                g.drawString("GAME OVER", 140, 200);
                repaint();
                status = "End";
                break;
        }
    }   
    
    private IntRange TBB = new IntRange(TOP_BORDER, BOTTOM_BORDER),
                     LRB = new IntRange(LEFT_BORDER,RIGHT_BORDER),
                     LRW = new IntRange(LEFT_WALL,RIGHT_WALL),
                     TBW = new IntRange(TOP_WALL,BOTTOM_WALL);
    
    private void CheckCollide(){
        if (!TBB.contain(player.Y) || !LRB.contain(player.X) || (LRW.contain(player.Y) && TBW.contain(player.X))){
            GameOver(); // CHECK COLLIDE WITH WALL AND BORDER
        }
    }

    public void GameOver(){
        status = "Game Over"; // Change to game over status
    }

    Timer moveBall = new Timer(delay, e->{player.move();score+=player.speed+100-delay;});
    Timer evaluate = new Timer(13000,e->player.speedUp());
    Timer randomMove = new Timer (1000,e->{
        if((int) Math.ceil(Math.random() * 10) == 2) player.changeClockwise();
    });
}