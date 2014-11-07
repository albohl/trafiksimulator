package graphics;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class screen extends JPanel{
	
	private int width;
	private int height;
	
	public JFrame frame;
	
	public int[] tiles;
	
	private BufferedImage image;
	public int[] pixels;
	
	public screen(int width, int height) {
		this.width = width * 16;
		this.height = height * 16;
		tiles = new int[this.width * this.height];
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(this.width, this.height);
		image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		for (int j = 0; j < tiles.length; j++){
			tiles[j] = 0x111111;
		}
		
		JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        frame.add(pane);
		
	}
	
	public void clear(){
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}
	
	public void render(){
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x % 16 == 0 || y % 16 == 0) {
					pixels[x + y * width] = 0x000000;
				}
				else {
					pixels[x + y * width] = tiles[(x / 16) + ((y * width) / 16)];	
				}
			}
		}
//	    screen.clear();
	    Graphics g = image.getGraphics(); // retrieves a graphics object from the next in line buffer in the bufferstrategy, this graphics object draws to that buffer
	    paintComponent(g);
	    g.drawImage(image, 0, 0, width, height, null); // draws the bufferedimage to the available buffer
	    g.dispose();
	}
	
}
