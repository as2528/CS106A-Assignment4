// This program runs a game of breakout. The player has 3 turns, and must destroy all the bricks with a bouncing ball and paddle. 

import acm.graphics.*;     // GOval, GRect, etc.
import acm.program.*;      // GraphicsProgram
import acm.util.*;         // RandomGenerator
import java.awt.*;         // Color
import java.awt.event.*;   // MouseEvent

public class Breakout extends BreakoutProgram {
    private GRect paddle;
    private GOval ball;
    private int velocityX = 0;
    private int velocityY = 0; 
    private RandomGenerator rgen = RandomGenerator.getInstance();
	int turnsLeft=NTURNS;
    


   


    
	public void run() {
		// Set the window's title bar text
		setTitle("CS 106A Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		
		
		brickBuilder();
		buildPaddle();
		buildBall();
		bouncingBall();





	}
	
	
	
	//This method creates the bricks in the program. It will also set the color for the bricks. 
    //Input: The constants for brick columns,brick width,brick separation, brick y offset, brick height,brick columns and brick rows
    //Output: The brick display
    private void brickBuilder() {
        int i=0;//i will count columns
        int j=-1;//j will count rows, set to -1 or the color display will break as well as y-coordinate
        while(j<(NBRICK_ROWS-1)) {
            j++;
            i-=i;//i must reset or while loop will only execute once for columns
            double x_coordinate = (getWidth()-(NBRICK_COLUMNS*BRICK_WIDTH+BRICK_SEP*(NBRICK_COLUMNS-1)))/2;
            double y_coordinate = BRICK_Y_OFFSET;
            y_coordinate +=j*(BRICK_HEIGHT+BRICK_SEP);
            while(i<NBRICK_COLUMNS) {
                i++;
                GRect brickWall = new GRect(x_coordinate,y_coordinate,BRICK_WIDTH,BRICK_HEIGHT);
                x_coordinate +=(BRICK_WIDTH+BRICK_SEP);
                brickWall.setFilled(true);
                switch((j+1)%10) {//switch will set the color, uses remainder to decide color of brick
                case 1:
                    brickWall.setFillColor(Color.RED);
                    break;
                case 2:
                    brickWall.setFillColor(Color.RED);
                    break;
                case 3:
                    brickWall.setFillColor(Color.ORANGE);
                    break;
                case 4:
                    brickWall.setFillColor(Color.ORANGE);
                    break;
                case 5:
                    brickWall.setFillColor(Color.YELLOW);
                    break;
                case 6:
                    brickWall.setFillColor(Color.YELLOW);
                    break;
                case 7:
                    brickWall.setFillColor(Color.GREEN);
                    break;
                case 8:
                    brickWall.setFillColor(Color.GREEN);
                    break;
                case 9:
                    brickWall.setFillColor(Color.CYAN);
                    break;
                case 0:
                    brickWall.setFillColor(Color.CYAN);
                    break;
                }
                add(brickWall);
                }
        
}
        }
/*This method builds the static paddle. It is a simple rectangle, filled with the color black, that is at the width, and height specified, and
 * as far from the bottom of the screen as specified by the offset parameter. It initiates the mouse listeners used to move the paddle later.
 * Input: PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT constants. Not input from any method needed. 
 * Output: A static rectangle used as a paddle, as well as the mouselisteners*/
    private void buildPaddle() {
    paddle = new GRect(10, getHeight()-PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
    paddle.setFilled(true);
    add(paddle);
    addMouseListeners();
   
    }
    /* This method builds the ball. The ball has the radius that was provided in the constant BALL_RADIUS. The ball is in the center of the screen. 
     * The ball is filled with the color black.
     * Input:BALL_RADIUS, nothing else from any method.
     * Output: A static ball*/
    private void buildBall() {
    	ball = new GOval(getWidth()/2,getHeight()/2,BALL_RADIUS,BALL_RADIUS);
    	ball.setFilled(true);
    	add(ball);
    }
    /*This method is necessary for moving the paddle. It uses the mouse, and holds the paddle so that the center of the paddle is on the mouse. 
     * It also does not allow the paddle to leave the screen. It replaces the paddle repeatedly on the screen using the mouselistener. 
     * Input: PADDLE_WIDTH,PADDLE_WIDTH, PADDLE_WIDTH, PADDLE_Y_OFFSET constants, static paddle from method above
     * Output: A moving paddle */
    public void mouseMoved(MouseEvent e) {
       paddle.setLocation(e.getX()-PADDLE_WIDTH/2, getHeight()-PADDLE_Y_OFFSET);
       if(e.getX()-PADDLE_WIDTH/2<=0) {
    	   paddle.setLocation(0,getHeight()-PADDLE_Y_OFFSET);
       }
       if(e.getX()+PADDLE_WIDTH/2>=getWidth()) {
    	   paddle.setLocation(getWidth()-PADDLE_WIDTH,getHeight()-PADDLE_Y_OFFSET);
       }
    }
    /* This method creates the bouncing ball. The static ball will not move without this method. 
     * Input: VELOCITY_X_MIN,VELOCITY_X_MAX,VELOCITY_Y,DELAY,BALL_RADIUS, NBRICK_COLUMNS,SCREEN_FONT, NTURNS, NBRICK_ROWS, static ball
     * Output: Ball that bounces on the sides of the screen. Velocity in the X direction is randomly generated, and multiplied
     * by -1 raised to a random power between 1 and 4 inclusive, so it has an equal likelihood of being negative or positive.Also outputs the 
     * win/lose graphics, and the scores, score labels, turns, and turn labels, and keeps track of whether the player loses or wins.*/
    private void bouncingBall() {
    	double velocityX = Math.pow((-1), rgen.nextInt(1,4))*rgen.nextDouble(VELOCITY_X_MIN,VELOCITY_X_MAX);
    	double velocityY = VELOCITY_Y;
    	int turnsLeft=NTURNS;
    	int scoreKeeper = 0;
    	GObject collidedObject;
    	GLabel turns = new GLabel("");
    	turns.setFont(SCREEN_FONT);
		add(turns,0,turns.getHeight());
    	while(turnsLeft!=0&&scoreKeeper!=NBRICK_COLUMNS*NBRICK_ROWS) {
//while loop runs while player has turns left or has bricks left to destroy
    		ball.move(velocityX, velocityY);
    		pause(DELAY);//delay so that ball appears on screen
    		turns.setLabel("SCORE:"+scoreKeeper+", TURNS:"+turnsLeft);//creates score and turns graphic
    		//if labels below change direction of ball when a canvas side is hit
    		if(ball.getX()<=0) {
    			velocityX=-velocityX;
    		}
    		if(ball.getY()+2*BALL_RADIUS>getHeight()) {//this if statement removes a turn and resets ball to center with a random x-velocity
    			turnsLeft-=1;//removes a turn since the bottom was hit
    			ball.setLocation(getWidth()/2,getHeight()/2);
    			velocityX = Math.pow((-1), rgen.nextInt(1,4))*rgen.nextDouble(VELOCITY_X_MIN,VELOCITY_X_MAX);
    			

    			
    		}
    		if(ball.getY()<=0) {
    			velocityY=-velocityY;
    		}
    		if(ball.getX()+2*BALL_RADIUS>getWidth()) {
    			velocityX=-velocityX;
    		}
    		//these if statements remove a brick and change ball trajectory, or hit the paddle and change ball trajectory. Also, they change the score
        	if(getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS)!=null&&getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS)!=paddle&&getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS)!=turns) {
        		
    			collidedObject = (getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS));
    			remove(collidedObject);
    			scoreKeeper+=1;
    			velocityX=-velocityX;

    		
    	}
        	
if(getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS)!=null&&getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS)==paddle) {
        		
    			
    			velocityX=-velocityX;

    		
    	}
if(getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS)!=null&&getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS)!=paddle&&getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS)!=turns) {

		collidedObject = getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS);
		remove(collidedObject);
			scoreKeeper+=1;
		velocityY=-velocityY;

	
    		
    	}
if(getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS)!=null&&getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS)==paddle) {

	velocityY=-velocityY;


		
	}
if(getElementAt(ball.getX()+BALL_RADIUS, ball.getY())!=null&&getElementAt(ball.getX()+BALL_RADIUS, ball.getY())!=paddle&&getElementAt(ball.getX()+BALL_RADIUS, ball.getY())!=turns) {
		collidedObject = getElementAt(ball.getX()+BALL_RADIUS, ball.getY());
		remove(collidedObject);
			scoreKeeper+=1;
		velocityY=-velocityY;

	}
if(getElementAt(ball.getX()+BALL_RADIUS, ball.getY())!=null&&getElementAt(ball.getX()+BALL_RADIUS, ball.getY())==paddle) {

	velocityY=-velocityY;

}
	

if(getElementAt(ball.getX(), ball.getY()+BALL_RADIUS)!=null&&getElementAt(ball.getX(), ball.getY()+BALL_RADIUS)!=paddle&&getElementAt(ball.getX(), ball.getY()+BALL_RADIUS)!=turns) {
		collidedObject = getElementAt(ball.getX(), ball.getY()+BALL_RADIUS);
		remove(collidedObject);
			scoreKeeper+=1;
		velocityX=-velocityX;

		
	}
if(getElementAt(ball.getX(), ball.getY()+BALL_RADIUS)!=null&&getElementAt(ball.getX(), ball.getY()+BALL_RADIUS)==paddle) {

	velocityX=-velocityX;

	
}

    }
    	//these if statements run the game over or game won labels and remove the paddles and balls
    if(turnsLeft==0) {
    	remove(paddle);
    	remove(ball);
    	GLabel loser = new GLabel("GAME OVER");
    	loser.setFont(SCREEN_FONT);
    	add(loser,getWidth()/2,getHeight()/2);
    	turns.setLabel("SCORE:"+scoreKeeper+", TURNS:"+turnsLeft);
    }
    if(scoreKeeper == NBRICK_COLUMNS*NBRICK_ROWS) {
    	remove(paddle);
    	remove(ball);
    	GLabel winner = new GLabel("YOU WIN!");
    	winner.setFont(SCREEN_FONT);
    	add(winner,getWidth()/2,getHeight()/2);
    }
    }
}
  /*  private void getCollidingObject() {
    	GObject collidedObject;
    	GLabel name = new GLabel("Artistry by Ajit Singh");
		add(name,100,100);
    	if(getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS)!=null&&getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS)!=paddle) {
    		
    			collidedObject = (getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS));
    			remove(collidedObject);
    			velocityX=-velocityX;
    			//return collidedObject;
    		
    	}
if(getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS)!=null&&getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS)!=paddle) {

		collidedObject = getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS);
		remove(collidedObject);
		velocityY=-velocityY;
		//return collidedObject;
	
    		
    	}
if(getElementAt(ball.getX()+BALL_RADIUS, ball.getY())!=null&&getElementAt(ball.getX()+BALL_RADIUS, ball.getY())!=paddle) {
		collidedObject = getElementAt(ball.getX()+BALL_RADIUS, ball.getY());
		remove(collidedObject);
		velocityY=-velocityY;
		//return collidedObject;
	}
	

if(getElementAt(ball.getX(), ball.getY()+BALL_RADIUS)!=null&&getElementAt(ball.getX(), ball.getY()+BALL_RADIUS)!=paddle) {
		collidedObject = getElementAt(ball.getX(), ball.getY()+BALL_RADIUS);
		remove(collidedObject);
		velocityX=-velocityX;
		//return collidedObject;
		
	}
	
}
    
    
    private void collidedObject(GObject hitObject) {
    	
    }
}
    
    */
