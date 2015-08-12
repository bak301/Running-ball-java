import java.awt.*;
class Ball {
    int X,Y;
    int speed = 1;
    boolean MoveClockwise;
    String currentDirection;

    Ball(int x,int y){
        this.X = x;
        this.Y = y;
        MoveClockwise = true;
        currentDirection = "right";
    }
    
    public void draw(Graphics stage){
        stage.fillOval(X,Y,20,20); // DRAW THE BALL WITH VARIABLE POSITION
    }
    
    public void turn(){
        if (MoveClockwise){ // IF THE BALL MOVE CLOCKWISE 
            switch (currentDirection){
                case "left":
                    currentDirection = "up";break;
                case "right":
                    currentDirection = "down";break;
                case "up":
                    currentDirection = "right";break;
                case "down":
                    currentDirection= "left";break;
                }
        } else { // IF THE BALL MOVE COUNTER-CLOCKWISE
            switch (currentDirection){
                case "left":
                    currentDirection = "down";break;
                case "right":
                    currentDirection = "up";break;
                case "up":
                    currentDirection = "left";break;
                case "down":
                    currentDirection= "right";break;
            }
        }
    }
    
    public void switchDirection(){ // MAKE THE BALL TURN 180 DEGREE
        switch(currentDirection){
            case "left":
                currentDirection = "right";break;
            case "right":
                currentDirection = "left";break;
            case "up":
                currentDirection = "down";break;
            case "down":
                currentDirection = "up";break;
        }
    }
    
    public void changeClockwise(){ // CLOCK OR COUNTER-CLOCK ?
        MoveClockwise = !MoveClockwise;
        switchDirection();
    }
    
    public void move(){
        switch (currentDirection){
            case "left": 
                moveLeft();break;
            case "right": 
                moveRight();break;
            case "up": 
                moveUp();break;
            case "down": 
                moveDown();break;
        }
    }
    
    public void speedUp(){
        speed++;
    }
    
    private void moveLeft(){
        X-=speed;
    }
    
    private void moveRight(){
        X+=speed;
    }
    
    private void moveUp(){
        Y-=speed;
    }
    
    private void moveDown(){
        Y+=speed;
    }
}