package hebrooks.Mandelbrot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class App  extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 1024;

	public App() {
		setPreferredSize(new Dimension(WIDTH * 2, HEIGHT * 2));
	}

	@Override
	public void paintComponent(Graphics g) {
		final BufferedImage image;
		int[] iArray = { 0, 0, 0, 255 };

		image = (BufferedImage) createImage(WIDTH, HEIGHT);
		WritableRaster raster = image.getRaster();

		for(int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				int v = mandelbrot(x,y);
				iArray[0] = v;
				iArray[1] = v;
				iArray[2] = v;
				raster.setPixel(x, y, iArray);
			}
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	private static int mandelbrot(int x, int y) {
		double x1 = (x - (WIDTH * 0.75)) / (WIDTH / 2.0); 
		double y1 = (y - (HEIGHT / 2.0)) / (HEIGHT / 2.0);
		return mandelbrot(x1, y1);
	}
	private static int mandelbrot(double cX, double cY) {
		double zx = 0;
		double zy = 0;
        int iter = 0;
        while (zx * zx + zy * zy < 4 && iter < 2048) {
            double tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iter++;
        }
        return iter;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new JFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setDefaultLookAndFeelDecorated(true);
				f.setResizable(false);
				App rt = new App();
				f.add(rt, BorderLayout.CENTER);
				f.pack();
				f.setVisible(true);
			}
		});
	}
}