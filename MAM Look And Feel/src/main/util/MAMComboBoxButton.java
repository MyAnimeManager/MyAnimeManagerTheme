package main.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MAMComboBoxButton extends JButton
{
	protected int direction;

    private Color arrowColor;
	private Color disabledArrowColor;
	
	public MAMComboBoxButton(int direction, Color background, Color arrowColor, Color disabledArrowColor) 
	{
		super();
		setRequestFocusEnabled(false);
		setDirection(direction);
		setBackground(background);
		this.arrowColor = arrowColor;
		this.disabledArrowColor = disabledArrowColor;
		}
		
	public void setDirection(int direction) {
        this.direction = direction;
    }
	
	public int getDirection() {
        return direction;
    }
	
	public void paint(Graphics g) {
        Color origColor;
        boolean isEnabled;
        int width;
        int height; 
        int arrowSize;

        width = this.getSize().width;
        height = this.getSize().height;
        origColor = g.getColor();
        isEnabled = this.isEnabled();

        g.setColor(this.getBackground());
        g.fillRect(0, 0, width, height);


        // If there's no room to draw arrow, bail
        if(height < 5 || width < 5)      {
            g.setColor(origColor);
            return;
        }

        //TODO Draw the arrow
        arrowSize = Math.min((height - 7) , (width - 7));
        arrowSize = Math.max(arrowSize, 2);
//        paintArrow(g, (width - size) / 2, (height - size) / 2,
//                            size, direction, isEnabled);
        int distance = 2;
        paintArrow(g, distance, distance,
                arrowSize, direction, isEnabled, width, height);
        // Reset the Graphics back to it's original settings
        g.setColor(origColor);

    }
	
    public void paintArrow(Graphics g, int x, int y, int arrowSize, int direction, boolean isEnabled, int width, int height) 
    {
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
 				RenderingHints.VALUE_ANTIALIAS_ON);
    	BasicStroke stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    	g2.setStroke(stroke);
    	if (isEnabled)
    		g2.setColor(arrowColor);
    	else
    		g2.setColor(disabledArrowColor);
    	g2.translate(x, y);
    	GeneralPath arrow = getArrowPath(x, y, arrowSize, direction, width,  height);  
    	g2.draw(arrow);
    	g2.translate(-x, -y);
    }
        
	private GeneralPath getArrowPath(int x, int y, int size, int direction,int width, int height)
	{
		GeneralPath arrow = new GeneralPath();
		arrow.moveTo(x, y);
		arrow.lineTo(x + size/2, y + size);
		arrow.lineTo(x + size, y);
		AffineTransform at = null;		
		if (direction == SwingConstants.NORTH)
		{
			if ((x % 2) == 0)
				x++;
			at = AffineTransform.getTranslateInstance(width - (x), height - (y*2));
			at.quadrantRotate(2);
			arrow.transform(at);
		}
		if (direction == SwingConstants.EAST)
		{
			at = AffineTransform.getTranslateInstance(width - (x*2), 0);
			at.quadrantRotate(1);
			arrow.transform(at);
		}
		if (direction == SwingConstants.WEST)
		{
			at = AffineTransform.getTranslateInstance(0, height - (y*2));
			at.quadrantRotate(-1);
			arrow.transform(at);
		}
		return arrow;
	}
}
