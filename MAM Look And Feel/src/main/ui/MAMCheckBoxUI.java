package main.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import sun.awt.AppContext;
import sun.swing.SwingUtilities2;


public class MAMCheckBoxUI extends MAMRadioButtonUI
{
	
	private static final Object MAM_CHECKBOX_UI_KEY = new Object();
	private boolean defaults_initialized = false;
	private static Icon icon;
	private static Color textColor;
	
	public static ComponentUI createUI(JComponent c) {
		AppContext appContext = AppContext.getAppContext();
		MAMCheckBoxUI metalButtonUI =
                (MAMCheckBoxUI) appContext.get(MAM_CHECKBOX_UI_KEY);
        if (metalButtonUI == null) {
            metalButtonUI = new MAMCheckBoxUI();
            appContext.put(MAM_CHECKBOX_UI_KEY, metalButtonUI);
        }
        setupColor();     
        return metalButtonUI;
    }
	@Override
	protected void installDefaults(AbstractButton b) 
	{
		super.installDefaults(b);
		if(!defaults_initialized) {
			icon = UIManager.getIcon("CheckBox.icon");
            defaults_initialized = true;
		}
	}
	
	@Override
	public Icon getDefaultIcon() {
        return icon;
    }
	
	 
	@Override
	 protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
	        AbstractButton b = (AbstractButton) c;
	        ButtonModel model = b.getModel();
	        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setFont(c.getFont());

	        /* Draw the Text */
    		if (model.isEnabled())
    			g2.setColor(textColor);
    		else
    			g2.setColor(textColor.brighter());
	        g2.drawString(text, textRect.x, textRect.y +fm.getAscent());
	        g2.dispose();
	    }

	private static void setupColor()
	{
		textColor = UIManager.getColor("CheckBox.textColor");		
	}
	
}
