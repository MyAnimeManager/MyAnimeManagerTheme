package main.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;


public class MAMSeparatorUI extends BasicSeparatorUI
{
	
	public static ComponentUI createUI(JComponent c) {
		return new MAMSeparatorUI();
	}
	@Override
	public void paint( Graphics g, JComponent c )
	{
		Dimension s = c.getSize();
		Graphics2D g2 = (Graphics2D) g.create();
		
		if ( ((JSeparator)c).getOrientation() == JSeparator.VERTICAL )
		{
			RadialGradientPaint gradient = new RadialGradientPaint(s.width/2f, s.height/2f, s.height/2f, new float[]{0.3f,0.9f}, new Color[]{c.getBackground(),c.getForeground()} );
			BasicStroke stroke = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			g2.setStroke(stroke);
			g2.setPaint(gradient);
			g2.drawLine( 0, 0, 0, s.height );
		}
		else  // HORIZONTAL
		{
			Point2D point = new Point(s.width/2, s.height/2);
			RadialGradientPaint gradient = new RadialGradientPaint(s.width/2f, s.height/2f, s.width/2f, new float[]{0.3f,0.9f}, new Color[]{c.getBackground(),c.getForeground()} );
			BasicStroke stroke = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			g2.setStroke(stroke);
			g2.setPaint(gradient);
			g2.drawLine( 0, 0, s.width, 0 );
			
		}
	}
	@Override
	public Dimension getPreferredSize( JComponent c )
	{
		if ( ((JSeparator)c).getOrientation() == JSeparator.VERTICAL )
			return new Dimension( 2, 0 );
		else
			return new Dimension( 0, 2 );
	}
}
