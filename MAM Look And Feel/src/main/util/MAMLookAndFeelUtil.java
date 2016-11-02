package main.util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class MAMLookAndFeelUtil
{
	
	public static BufferedImage getBlankImage(int width, int height) {

		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice d = e.getDefaultScreenDevice();
		GraphicsConfiguration c = d.getDefaultConfiguration();
		BufferedImage compatibleImage = c.createCompatibleImage(width, height,
				Transparency.TRANSLUCENT);
		return compatibleImage;
	}
	
	public static BufferedImage getStripe(int baseSize, Color color) {
		return getStripe( baseSize,  color, color);
	}
	public static BufferedImage getStripe(int baseSize, Color color1, Color color2) {
		int width = (int) (1.8 * baseSize);
		int height = baseSize;
		BufferedImage result = getBlankImage(width,
				height);
		Graphics2D graphics = (Graphics2D) result.getGraphics();

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Polygon polygon = new Polygon();
		polygon.addPoint(0, 0);
		polygon.addPoint(width - 1 - baseSize, 0);
		polygon.addPoint(width - 1, height - 1);
		polygon.addPoint(baseSize, height - 1);

		GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height/2, color2, true);
		graphics.setPaint(gradient);
		graphics.fillPolygon(polygon);
		graphics.drawPolygon(polygon);

		float[] BLUR = { 0.10f, 0.10f, 0.10f, 0.10f, 0.30f, 0.10f, 0.10f,
				0.10f, 0.10f };
		ConvolveOp vBlurOp = new ConvolveOp(new Kernel(3, 3, BLUR));
		BufferedImage blurred = vBlurOp.filter(result, null);

		return blurred;
	}
}
