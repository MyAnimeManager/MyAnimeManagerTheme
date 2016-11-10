package main.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicPanelUI;


public class MAMPanelUI extends BasicPanelUI
{
	private static Color backgroundColor;
    private static Color backgroundGradientColor;
    private static boolean gradientActive;


	public static ComponentUI createUI(JComponent c) {
        setupColor();
        return new MAMPanelUI();
    }
	
	@Override
	public void update(Graphics g, JComponent c) {
    	Graphics2D g2 = (Graphics2D) g.create();
		if ((c.getBackground() instanceof UIResource))
		{	
	        if (c.isOpaque()) 
	        {
	        	if (gradientActive)
	        	{
			    	GradientPaint gradient = new GradientPaint(0, 0, backgroundColor, 0, c.getHeight()/2, backgroundGradientColor, true);
			    	g2.setPaint(gradient);
			        g2.fillRect(0, 0, c.getWidth(),c.getHeight());
	        	}
	        	else
	        	{
			    	g2.setColor(backgroundColor);
			        g2.fillRect(0, 0, c.getWidth(),c.getHeight());
	        	}
	        }
		}
		else
		{
	    	g2.setColor(c.getBackground());
	        g2.fillRect(0, 0, c.getWidth(),c.getHeight());
		}
		g2.dispose();
    }
	
	 public static void setupColor()
	 {
	   backgroundColor = UIManager.getColor("Panel.background");
	   backgroundGradientColor = UIManager.getColor("Panel.backgroundGradient");
	   gradientActive = UIManager.getBoolean("Panel.gradientActive");
	 }
	
}
