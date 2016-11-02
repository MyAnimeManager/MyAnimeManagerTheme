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

import sun.awt.AppContext;


public class MAMPanelUI extends BasicPanelUI
{
	private static final Object MAM_PANEL_UI_KEY = new Object();
	private static Color externalColor;
    private static Color internalColor;
    private static boolean gradientActive;


	public static ComponentUI createUI(JComponent c) {
		AppContext appContext = AppContext.getAppContext();
		MAMPanelUI metalButtonUI =
                (MAMPanelUI) appContext.get(MAM_PANEL_UI_KEY);
        if (metalButtonUI == null) {
            metalButtonUI = new MAMPanelUI();
            appContext.put(MAM_PANEL_UI_KEY, metalButtonUI);
        }
        setupColor();
        return metalButtonUI;
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
			    	GradientPaint gradient = new GradientPaint(0, 0, externalColor, 0, c.getHeight()/2, internalColor, true);
			    	g2.setPaint(gradient);
			        g2.fillRect(0, 0, c.getWidth(),c.getHeight());
	        	}
	        	else
	        	{
			    	g2.setColor(externalColor);
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
	   externalColor = UIManager.getColor("Panel.externalColor");
	   internalColor = UIManager.getColor("Panel.internalColor");
	   gradientActive = UIManager.getBoolean("Panel.gradientActive");
	 }
	
}