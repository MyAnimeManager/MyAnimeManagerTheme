package main.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;

import main.util.MAMLookAndFeelUtil;
import sun.swing.SwingUtilities2;

public class MAMButtonUI extends BasicButtonUI
{
	private static Rectangle viewRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();
    private static Color backgroundColor;
    private static Color backgroundGradientColor;
    private static Color backgroundRolloverColor;
    private static Color backgroundGradientRolloverColor;
    private static Color textColor;
    private static Color textRolloverColor;
    private static Color focusRingColor;
    private static Color focusRingRolloverColor;
    private static int focusLineDistance;
	private static Insets margin;
	
	//Fade Animation
	private float transparency = 0;
	private boolean animating = false;
    private static Color backgroundRolloverActualColor;
    private static Color backgroundGradientRolloverActualColor;
	private Timer fadeInTimer;
	private Timer fadeOutTimer;
	private static final int TIMER_INTERVAL = 20;
    
	 public static ComponentUI createUI(JComponent c) {
	        setupColor();
	        return new MAMButtonUI();
	    }
	 
	@Override
    public void update(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton)c;
        if ((c.getBackground() instanceof UIResource) && button.isContentAreaFilled()) 
        {
            if (!(c.getParent() instanceof JToolBar)) 
            {
                paint(g, c);
            }
        }
        else
        	super.update(g, c);

    }
	
	@Override
	public void paint(Graphics g, JComponent c)
    {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
    	Graphics2D g2 = (Graphics2D) g.create();
    	
        String text = layout(b, SwingUtilities2.getFontMetrics(b, g), b.getWidth(), b.getHeight());

        clearTextShiftOffset();
        
        GradientPaint paint = new GradientPaint(0, 0, backgroundColor, 0, c.getHeight()/2, backgroundGradientColor, true);
        g2.setPaint(paint);
        g2.fillRect(0, 0, c.getWidth(), c.getHeight());
        
        if (animating && !model.isArmed())
        {
        	GradientPaint gradient = new GradientPaint(0, 0, backgroundRolloverActualColor, 0, c.getHeight()/2, backgroundGradientRolloverActualColor, true);
        	g2.setPaint(gradient);
        	g2.fillRect(0, 0, c.getWidth(),c.getHeight());

        }
        // perform UI specific press action, e.g. Windows L&F shifts text
        if (model.isArmed() && model.isPressed()) {
            paintButtonPressed(g,b);
        }

        // Paint the Icon
        if(b.getIcon() != null) {
            paintIcon(g,c,iconRect);
        }

        if (text != null && !text.equals("")){
            View v = (View) c.getClientProperty(BasicHTML.propertyKey);
            if (v != null) {
                v.paint(g, textRect);
            } else {
                paintText(g, c, textRect, text);
            }
        }

        if (b.isFocusPainted() && b.hasFocus() && focusLineDistance > 0) {
            // paint UI specific focus
            paintFocus(g2, b, viewRect, textRect, iconRect);
        }
        
        if (!model.isEnabled())
        {
        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
        	g2.setColor(Color.WHITE);
        	g2.fillRect(0, 0, b.getWidth(), b.getHeight());
        }
        
        g2.dispose();
    }
	
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
        if ( b.isContentAreaFilled() ) {
            Dimension size = b.getSize();
            GradientPaint gradient = new GradientPaint(0, 0, backgroundColor.darker(), 0, (int)size.getHeight()/2, backgroundGradientColor.darker(), true);
        	Graphics2D g2 = (Graphics2D) g.create();
        	g2.setPaint(gradient);
            g2.fillRect(0, 0,(int) size.getWidth(),(int)size.getHeight());
            g2.dispose();
        }
    }
	
	@Override
	 protected void paintFocus(Graphics g, AbstractButton b,
             Rectangle viewRect, Rectangle textRect, Rectangle iconRect)        
	{
		int focusLineDistances = focusLineDistance;
		if (focusLineDistances > textRect.x || focusLineDistances > textRect.y)
		{
			if (textRect.x > textRect.y)
				focusLineDistances = textRect.y + 2;
			else
				focusLineDistances = textRect.x + 2;
		}
		ButtonModel model = b.getModel();
		float dash1[] = {2.0f};
		BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, dash1, 0.0f);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setStroke(dashed);
		if (model.isRollover())
			g2.setColor(focusRingRolloverColor);
		else
			g2.setColor(focusRingColor);	
		g2.drawRect(focusLineDistances,focusLineDistances, b.getWidth() - (2 * focusLineDistances) , b.getHeight() - (2 * focusLineDistances) );
		g2.dispose();
	}
	
	@Override
	protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) 
	{
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(c.getFont());

        /* Draw the Text */
        	if (!model.isRollover())
        	{
        		if (model.isEnabled())
        			g2.setColor(textColor);
        		else
        			g2.setColor(textColor.brighter());
        	}
        	else
        		g.setColor(textRolloverColor);
        g2.drawString(text, textRect.x, textRect.y +fm.getAscent());
        g2.dispose();
	 }

	 private String layout(AbstractButton b, FontMetrics fm,
             int width, int height) 
	 {
			Insets i = b.getInsets();
			viewRect.x = i.left;
			viewRect.y = i.top;
			viewRect.width = width - (i.right + viewRect.x);
			viewRect.height = height - (i.bottom + viewRect.y);
			
			textRect.x = textRect.y = textRect.width = textRect.height = 0;
			iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;
			// layout the text and icon
			return SwingUtilities.layoutCompoundLabel(
			b, fm, b.getText(), b.getIcon(),
			b.getVerticalAlignment(), b.getHorizontalAlignment(),
			b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
			viewRect, iconRect, textRect,
			b.getText() == null ? 0 : b.getIconTextGap());
		}
	 
	 public Dimension getPreferredSize(JComponent b)
     {
		 AbstractButton c = (AbstractButton) b;
		 if(c.getComponentCount() > 0) {
	            return null;
	        }

	        Icon icon = c.getIcon();
	        String text = c.getText();

	        Font font = c.getFont();
	        FontMetrics fm = c.getFontMetrics(font);

	        Rectangle iconR = new Rectangle();
	        Rectangle textR = new Rectangle();
	        Rectangle viewR = new Rectangle(Short.MAX_VALUE, Short.MAX_VALUE);

	        SwingUtilities.layoutCompoundLabel(
	            c, fm, text, icon,
	            c.getVerticalAlignment(), c.getHorizontalAlignment(),
	            c.getVerticalTextPosition(), c.getHorizontalTextPosition(),
	            viewR, iconR, textR, (text == null ? 0 : c.getIconTextGap())
	        );

	        /* The preferred size of the button is the size of
	         * the text and icon rectangles plus the buttons insets.
	         */

	        Rectangle r = iconR.union(textR);

	        Insets insets = c.getInsets();
	        r.width += insets.left + insets.right + margin.left + margin.right;
	        r.height += insets.top + insets.bottom + margin.top + margin.bottom;

	        return r.getSize();
     }
	 
	 
	 @Override
	 public void installUI(JComponent c)
	 {
		 super.installUI(c);
			 fadeInTimer = new Timer(TIMER_INTERVAL, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					transparency += 0.075f;
					if (transparency > 1f)
					{
						transparency = 1f;
						fadeInTimer.stop();
					}
					backgroundRolloverActualColor = new Color(MAMLookAndFeelUtil.getRedFloat(backgroundRolloverColor), MAMLookAndFeelUtil.getGreenFloat(backgroundRolloverColor), MAMLookAndFeelUtil.getBlueFloat(backgroundRolloverColor), transparency);
					backgroundGradientRolloverActualColor = new Color(MAMLookAndFeelUtil.getRedFloat(backgroundGradientRolloverColor), MAMLookAndFeelUtil.getGreenFloat(backgroundGradientRolloverColor), MAMLookAndFeelUtil.getBlueFloat(backgroundGradientRolloverColor), transparency);
					c.repaint();
				}
			});
			 
			 fadeOutTimer = new Timer(TIMER_INTERVAL , new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						transparency -= 0.075f;
						if (transparency < 0f)
						{
							transparency = 0f;
							fadeOutTimer.stop();
						}
						backgroundRolloverActualColor = new Color(MAMLookAndFeelUtil.getRedFloat(backgroundRolloverActualColor), MAMLookAndFeelUtil.getGreenFloat(backgroundRolloverActualColor), MAMLookAndFeelUtil.getBlueFloat(backgroundRolloverActualColor), transparency);
						backgroundGradientRolloverActualColor = new Color(MAMLookAndFeelUtil.getRedFloat(backgroundGradientRolloverActualColor), MAMLookAndFeelUtil.getGreenFloat(backgroundGradientRolloverActualColor), MAMLookAndFeelUtil.getBlueFloat(backgroundGradientRolloverActualColor), transparency);
						c.repaint();
					}
				});
			 
			 c.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e)
				{
					Point mouse = e.getPoint();
					Rectangle compRect = c.getBounds();
					if (compRect.contains(mouse))
					{
						fadeOutTimer.stop();
						fadeInTimer.start();
					}
					else
					{
						fadeInTimer.stop();
					}
					c.repaint();
				}
				
				@Override
				public void mousePressed(MouseEvent e)
				{
					if (fadeInTimer.isRunning())
						fadeInTimer.stop();
				}
				
				@Override
				public void mouseExited(MouseEvent e)
				{
					fadeInTimer.stop();
					animating = false;
					((AbstractButton) c).getModel().setRollover(false);
					if (fadeOutTimer != null && !fadeOutTimer.isRunning())
					{
						animating = true;
						fadeOutTimer.start();
					}	
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					fadeOutTimer.stop();
					animating = false;
					((AbstractButton) c).getModel().setRollover(true);
					if (fadeInTimer != null && !fadeInTimer.isRunning())
					{
						animating = true;
						fadeInTimer.start();
					}

					
				}
				
				@Override
				public void mouseClicked(MouseEvent e)
				{
					c.repaint();
				}
				
				@Override
	            public void mouseDragged (MouseEvent e)
	            {
					if (c.isEnabled ())
	                {
	                    if (fadeInTimer == null || fadeInTimer.isRunning ())
	                    {
	                        c.repaint();
	                    }
	                    if (fadeOutTimer == null || fadeOutTimer.isRunning ())
	                    {
	                        c.repaint();
	                    }
	                }
	            }
			});
	 }

	 private static void setupColor()
	 {
	   backgroundColor = backgroundRolloverActualColor = UIManager.getColor("Button.background");
	   backgroundGradientColor = backgroundGradientRolloverActualColor = UIManager.getColor("Button.backgroundGradient");
	   backgroundRolloverColor = UIManager.getColor("Button.backgroundRolloverColor");
	   backgroundGradientRolloverColor = UIManager.getColor("Button.backgroundGradientRolloverColor");  
	   textColor = UIManager.getColor("Button.textColor");
	   textRolloverColor = UIManager.getColor("Button.textRolloverColor");
	   focusRingColor = UIManager.getColor("Button.focusRingColor");
	   focusRingRolloverColor = UIManager.getColor("Button.focusRingRolloverColor");
	   focusLineDistance = UIManager.getInt("Button.focusLineDistance");
	   margin = UIManager.getInsets("Button.margin");
	 }
	 
}
