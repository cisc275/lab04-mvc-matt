import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel{
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics = new BufferedImage[9][10];
    String[] fnames = {"orc_forward_southeast.png","orc_forward_southwest.png","orc_forward_northeast.png","orc_forward_northwest.png","orc_forward_north.png","orc_forward_south.png","orc_forward_east.png","orc_forward_west.png"};
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    
    static int animNum = 0;
    
    static int xloc;
    static int yloc;
    
    public void chooseAnimation(Direction dir) {	
    	if (dir.getName().equals("southeast")) {
			animNum = 0;
    	}
    	if (dir.getName().equals("southwest")) {
			animNum = 1;
    	}
    	if (dir.getName().equals("northeast")) {
			animNum = 2;
    	}
    	if (dir.getName().equals("northwest")) {
			animNum = 3;
    	}
    	if (dir.getName().equals("north")) {
			animNum = 4;
    	}
    	if (dir.getName().equals("south")) {
			animNum = 5;
    	}
    	if (dir.getName().equals("east")) {
			animNum = 6;
    	}
    	if (dir.getName().equals("west")) {
			animNum = 7;
    	}
    }
    
    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	g.drawImage(pics[animNum][picNum], View.xloc, View.yloc, Color.gray, this);
    }
    
    
	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageWidth() {
		return imgWidth;
	}
	public int getImageHeight() {
		return imgHeight;
	}
	
	public void update(int x, int y, Direction d) {
		chooseAnimation(d);
		xloc = x;
		yloc = y;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public View(){
    	for (int j = 0 ; j < fnames.length ; j++) {
    		BufferedImage img = createImage(fnames[j]);
           	pics[j] = new BufferedImage[10];
            for(int i = 0; i < frameCount; i++) {
           		pics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
           	}
    	}
    	
    }  
	
	private BufferedImage createImage(String filename){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(filename));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}