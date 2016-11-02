package main.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

import main.util.MAMLookAndFeelUtil;
import sun.swing.SwingUtilities2;


public class MAMProgressBarUI extends BasicProgressBarUI
{

	private int stripeOffset;
	private static boolean backgroundGradient;
	private static Color backgroundGradientColor;
	private static boolean foregroundGradient;
	private static Color foregroundGradientColor;
	
	public static ComponentUI createUI(JComponent x) {
		setupColor();
        return new MAMProgressBarUI();
    }
	
	@Override
	protected void paintDeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) {
            return;
        }

        Insets b = progressBar.getInsets(); // area for border
        int barRectWidth = progressBar.getWidth() - (b.right + b.left);
        int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

        if (barRectWidth <= 0 || barRectHeight <= 0) {
            return;
        }

        int cellLength = getCellLength();
        int cellSpacing = getCellSpacing();
        // amount of progress to draw
        int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

        Graphics2D g2 = (Graphics2D)g;
        if (backgroundGradient)
        {
        	LinearGradientPaint gradient = new LinearGradientPaint(0, 0, 0, c.getHeight(), new float[]{0.3f, 0.7f}, new Color[]{c.getBackground(),backgroundGradientColor}, CycleMethod.NO_CYCLE);
        	g2.setPaint(gradient);

        }
        else
        	g2.setColor(c.getBackground());
      
    	g2.fillRect(0, 0, progressBar.getWidth(), progressBar.getHeight());
        
        if (foregroundGradient)
        {
        	GradientPaint gradient = new GradientPaint(0, 0, foregroundGradientColor , 0, c.getHeight()/2, c.getForeground(), true);
        	g2.setPaint(gradient);
        }
        else
        	g2.setColor(c.getForeground());
        
        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) 
        {
            if (c.getComponentOrientation().isLeftToRight()) 
            {
            	g2.fillRect(b.left, b.top, amountFull + b.left, barRectHeight + b.top);

            } 
            else 
            {
            	g2.fillRect(amountFull + b.left, b.top,
            			barRectWidth + b.left - amountFull,
            			barRectHeight);
            }

        } 
        else { // VERTICAL
            // draw the cells
            if (cellSpacing == 0 && amountFull > 0) {
                // draw one big Rect because there is no space between cells
                g2.setStroke(new BasicStroke(barRectWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
            } else {
                // draw each individual cell
                g2.setStroke(new BasicStroke(barRectWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                        0f, new float[] { cellLength, cellSpacing }, 0f));
            }

            g2.drawLine(barRectWidth/2 + b.left,
                    b.top + barRectHeight,
                    barRectWidth/2 + b.left,
                    b.top + barRectHeight - amountFull);
        }

        // Deal with possible text painting
        if (progressBar.isStringPainted()) {
            paintString(g, b.left, b.top,
                        barRectWidth, barRectHeight,
                        amountFull, b);
        }
    }
	
	@Override
	protected void paintIndeterminate(Graphics g, JComponent c) 
	{
		if (!(g instanceof Graphics2D)) {
            return;
        }
        Insets b = progressBar.getInsets(); // area for border
        int barRectWidth = progressBar.getWidth() - (b.right + b.left);
        int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

        if (barRectWidth <= 0 || barRectHeight <= 0) {
            return;
        }

        Graphics2D g2 = (Graphics2D)g.create();

        if (backgroundGradient)
        {
        	LinearGradientPaint gradient = new LinearGradientPaint(0, 0, 0, c.getHeight(), new float[]{0.3f, 0.7f}, new Color[]{c.getBackground(),backgroundGradientColor}, CycleMethod.NO_CYCLE);
        	g2.setPaint(gradient);

        }
        else
        	g2.setColor(c.getBackground());
      
    	g2.fillRect(0, 0, progressBar.getWidth(), progressBar.getHeight());
    	
        int startX = c.getBorder().getBorderInsets(c).left;
        int startY = c.getBorder().getBorderInsets(c).top;

        

        boolean isVertical = false;
        if (progressBar.getOrientation() == SwingConstants.VERTICAL) 
        {
        	isVertical = true;
        }
        if (foregroundGradient)
        	paintRectangularStripedBackground(g2, startX, startY, barRectWidth, barRectHeight, MAMLookAndFeelUtil.getStripe(barRectHeight, foregroundGradientColor, c.getForeground() ), stripeOffset, isVertical);
        else
        	paintRectangularStripedBackground(g2, startX, startY, barRectWidth, barRectHeight, MAMLookAndFeelUtil.getStripe(barRectHeight, c.getForeground()), stripeOffset, isVertical);
        stripeOffset++;

        // Deal with possible text painting
        if (progressBar.isStringPainted()) {
            if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
                paintString(g2, b.left, b.top,
                            barRectWidth, barRectHeight,
                            boxRect.x, boxRect.width);
            }
            else {
                paintString(g2, b.left, b.top,
                            barRectWidth, barRectHeight,
                            boxRect.y, boxRect.height);
            }
        }
	}
	
	private void paintString(Graphics g, int x, int y, int width, int height,
            int fillStart, int amountFull)
	{
		if (!(g instanceof Graphics2D)) {
            return;
        }

        Graphics2D g2 = (Graphics2D)g;
        String progressString = progressBar.getString();
        g2.setFont(progressBar.getFont());
        Point renderLocation = getStringPlacement(g2, progressString,
                                                  x, y, width, height);
        Rectangle oldClip = g2.getClipBounds();

        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            g2.setColor(getSelectionBackground());
            SwingUtilities2.drawString(progressBar, g2, progressString,
                                       renderLocation.x, renderLocation.y);
            g2.setColor(getSelectionForeground());
            g2.clipRect(fillStart, y, amountFull, height);
            SwingUtilities2.drawString(progressBar, g2, progressString,
                                       renderLocation.x, renderLocation.y);
        } else { // VERTICAL
            g2.setColor(getSelectionBackground());
            AffineTransform rotate =
                    AffineTransform.getRotateInstance(Math.PI/2);
            g2.setFont(progressBar.getFont().deriveFont(rotate));
            renderLocation = getStringPlacement(g2, progressString,
                                                  x, y, width, height);
            SwingUtilities2.drawString(progressBar, g2, progressString,
                                       renderLocation.x, renderLocation.y);
            g2.setColor(getSelectionForeground());
            g2.clipRect(x, fillStart, width, amountFull);
            SwingUtilities2.drawString(progressBar, g2, progressString,
                                       renderLocation.x, renderLocation.y);
        }
        g2.setClip(oldClip);
	}
	
	private static void paintRectangularStripedBackground(Graphics g, 
			int startX, int startY, int width, int height,
			BufferedImage stripeImage, int stripeOffset,  
			boolean isVertical) {
		Graphics2D graphics = (Graphics2D) g.create(startX, startY, width,
				height);
		if (!isVertical) {

			if (stripeImage != null) {
				int stripeSize = stripeImage.getHeight();
				int stripeCount = width / stripeSize;
				stripeOffset = stripeOffset % (2 * stripeSize);
				for (int stripe = -2; stripe <= stripeCount; stripe += 2) {
					int stripePos = stripe * stripeSize + stripeOffset;

					graphics.drawImage(stripeImage, stripePos, 0, null);
				}
			}
		} else {
			
			if (stripeImage != null) {
				int stripeSize = stripeImage.getWidth();
				int stripeCount = height / stripeSize;
				stripeOffset = stripeOffset % (2 * stripeSize);
				for (int stripe = -2; stripe <= stripeCount; stripe += 2) {
					int stripePos = stripe * stripeSize + stripeOffset;

					graphics.drawImage(stripeImage, 0, stripePos, null);
				}
			}
		}

		graphics.dispose();
	}

	
	@Override
	protected Rectangle getBox(Rectangle r) {
		return new Rectangle(0, 0, progressBar.getWidth(), progressBar.getHeight());
    }

	private static void setupColor()
	{
		backgroundGradient = UIManager.getBoolean("ProgressBar.backgroundGradient");
		foregroundGradient = UIManager.getBoolean("ProgressBar.foregroundGradient");
		foregroundGradientColor = UIManager.getColor("ProgressBar.foregroundGradientColor");
		backgroundGradientColor = UIManager.getColor("ProgressBar.backgroundGradientColor");
	}
	
}
