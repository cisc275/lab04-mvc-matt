import java.awt.Color;

import javax.swing.JFrame;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model {
	Direction d;
	int yCoord = 0;
	int xCoord = 0;
	
	int width;
	int height;
	int IMwidth;
	int IMheight;
	
    int xIncr = 8;
    int yIncr = 2;
    
    JFrame frame;
	//Constructor, Initilizes model frame
	public Model(int w, int h, int IMw, int IMh){
		width = w;
		height = h;
		IMheight = IMh;
		IMwidth = IMw;
		frame = new JFrame();
		frame.getContentPane().add(new View());
		frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(View.frameWidth, View.frameHeight);
    	frame.setVisible(true);
	}
	//Updates the direction
	public void updateDirection() {
    	if (xIncr == 0 & yIncr == 0) {
    		System.out.println("We're not ready for this yet");
    	}
    	else if (xIncr == 0 & yIncr < 0) {
    		d = Direction.NORTH;
    	}
    	else if (xIncr == 0 & yIncr > 0) {
    		d = Direction.SOUTH;
    	}
    	else if (xIncr > 0 & yIncr == 0) {
    		d = Direction.EAST;
    	}
    	else if (xIncr < 0 & yIncr == 0) {
    		d = Direction.WEST;
    	}
    	else if (xIncr > 0 & yIncr > 0) {
    		d = Direction.SOUTHEAST;
    	}
    	else if (xIncr < 0 & yIncr > 0) {
    		d = Direction.SOUTHWEST;
    	}
    	else if (xIncr > 0 & yIncr < 0) {
    		d = Direction.NORTHEAST;
    	}
    	else if (xIncr < 0 & yIncr < 0) {
    		d = Direction.NORTHWEST;
    	}
    }
	//Updates the location and checks for collisions
	public void updateLocation() {
		xCoord += xIncr;
		yCoord += yIncr;
		if (xCoord > View.frameWidth-View.imgWidth) {
    		xIncr = xIncr * -1;
    	}
    	if (xCoord < 0-(40)) {
    		xIncr = xIncr * -1;
    	}
    	if (yCoord > View.frameHeight-View.imgHeight) {
    		yIncr = yIncr * -1;
    	}
    	if (yCoord < 0-(40)) {
    		yIncr = yIncr * -1;
    	}
	}
	//Updates the frame, location, and direction
	public void updateLocationAndDirection() {
		updateDirection();
		frame.repaint();
		updateLocation();
	}
	//returns X coord
	public int getX() {
		return xCoord;
	}
	//returns Y coord
	public int getY() {
		return yCoord;
	}
	//returns the direction
	public Direction getDirect() {
		return d;
	}
	
}