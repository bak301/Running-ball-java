import java.awt.*;
import javax.swing.*;

class Stage extends JPanel {
    public Ball player;
    String status;
    int score, delay = 5,
            TOP_BORDER = 49,
            BOTTOM_BORDER = 331,
            LEFT_BORDER = 49,
            RIGHT_BORDER = 331,
            TOP_WALL = 81,
            BOTTOM_WALL = 300,
            LEFT_WALL = 81,
            RIGHT_WALL = 300;
    
    Stage() {
        initNewBall();
        setPreferredSize(new Dimension(400, 450));
        setBackground(new Color(255, 255, 255));
        addMouseListener((Press)(e)->{
            if(e.getButton()==1){
                player.turn();
            } else if (e.getButton()==3 && status.equals("End")){
                repaint();
                initNewBall();
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D f = (Graphics2D) g;
        switch (status) {
            case "Play":
                // DRAW THE FIELD
                super.paintComponent(f);
                f.setColor(Color.red);
                f.draw(new Rectangle(50, 50, 300, 300));// BORDER
                f.fill(new Rectangle(100, 100, 200, 200));// INTERNAL WALL
                // DRAW THE SCORE AND SPEED
                f.setColor(new Color(18, 17, 132));
                f.drawString("Speed: " + Integer.toString(player.speed), 35, 40);
                f.drawString("Score: " + Integer.toString(score), 35, 20);
                // BALL MOVEMENT
                moveBall.start();
                f.setColor(new Color(0, 255, 1));
                f.fill(player);
                CheckCollide(f);
                evaluate.start();
                randomMove.start();
                // REPAINT
                repaint(player.X, player.Y, 20, 20);//Repaint Ball
                repaint(35, 0, 150, 200);// SCOREBOARD
                break;
            case "Game Over": // Show the player result
                super.paintComponent(f);
                f.drawString("Speed: " + Integer.toString(player.speed), 120, 140); // Speed
                f.drawString("Score: " + Integer.toString(score), 120, 160); // Score
                f.setFont(new Font("TimesRoman", Font.ITALIC, 20));
                f.setColor(new Color(255, 0, 10));
                f.drawString("GAME OVER", 140, 200);
                f.drawString("Right click to restart the game! ", 50, 230);
                repaint();
                status = "End";
                break;
        }
    }

    private void initNewBall(){
        // Reset game
        player = new Ball(60,60);
        score = 0;
        player.speed =1;
        status = "Play";

        // Reset all Timer
        moveBall.stop();
        randomMove.stop();
        evaluate.stop();
    }
    
    private IntRange TBB = new IntRange(TOP_BORDER, BOTTOM_BORDER),
                     LRB = new IntRange(LEFT_BORDER,RIGHT_BORDER),
                     LRW = new IntRange(LEFT_WALL,RIGHT_WALL),
                     TBW = new IntRange(TOP_WALL,BOTTOM_WALL);
    
    private void CheckCollide(Graphics2D f){
        if (!TBB.contain(player.Y) || !LRB.contain(player.X) || (LRW.contain(player.Y) && TBW.contain(player.X))){
            super.paintComponent(f);
            repaint();
            status = "Game Over"; // CHECK COLLIDE WITH WALL AND BORDER
        }
    }

    Timer moveBall = new Timer(delay, e->{player.move();score+=player.speed+100-delay;});
    Timer evaluate = new Timer(11000,e->player.speedUp());
    Timer randomMove = new Timer (1600,e->{
        if((int) Math.ceil(Math.random() * 10) == 1) player.changeClockwise();
    });
}