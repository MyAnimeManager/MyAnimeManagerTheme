package main.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import javax.swing.text.View;

import sun.awt.AppContext;
import sun.swing.SwingUtilities2;


public class MAMRadioButtonUI extends BasicRadioButtonUI
{
	private static final Object MAM_RADIOBUTTON_UI_KEY = new Object();
	private boolean defaults_initialized = false;
	private static Dimension size = new Dimension();
	private  Rectangle viewRect = new Rectangle();
    private  Rectangle iconRect = new Rectangle();
    private  Rectangle textRect = new Rectangle();

    private static Icon icon;
	private static Color textColor;
	
	public static ComponentUI createUI(JComponent c) {
		AppContext appContext = AppContext.getAppContext();
		MAMRadioButtonUI metalButtonUI =
                (MAMRadioButtonUI) appContext.get(MAM_RADIOBUTTON_UI_KEY);
        if (metalButtonUI == null) {
            metalButtonUI = new MAMRadioButtonUI();
            appContext.put(MAM_RADIOBUTTON_UI_KEY, metalButtonUI);
        }

        setupColor();
        return metalButtonUI;
    }

	@Override
	protected void installDefaults(AbstractButton b) 
	{
//		super.installDefaults(b);
		if(!defaults_initialized) {
	        icon = UIManager.getIcon("RadioButton.icon");
            defaults_initialized = true;
		}
	}
	/**
     * Returns the default icon.
     *
     * @return the default icon
     */
	@Override
    public Icon getDefaultIcon() {
        return icon;
    }
	
	@Override
	public void update(Graphics g, JComponent c) {
        paint(g, c);
    }
	
	/**
     * paint the radio button
     */
    @Override
    public synchronized void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        Graphics2D g2 = (Graphics2D) g.create();
        
        Font f = c.getFont();
        g2.setFont(f);
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g2, f);

        Insets i = c.getInsets();
        size = b.getSize(size);
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = size.width - (i.right + viewRect.x);
        viewRect.height = size.height - (i.bottom + viewRect.y);
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;
        textRect.x = textRect.y = textRect.width = textRect.height = 0;

        Icon altIcon = b.getIcon();


        String text = SwingUtilities.layoutCompoundLabel(
            c, fm, b.getText(), altIcon != null ? altIcon : getDefaultIcon(),
            b.getVerticalAlignment(), b.getHorizontalAlignment(),
            b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
            viewRect, iconRect, textRect,
            b.getText() == null ? 0 : b.getIconTextGap());
        if(altIcon != null) {

            if(!model.isEnabled()) {
                if(model.isSelected()) {
                   altIcon = b.getDisabledSelectedIcon();
                } else {
                   altIcon = b.getDisabledIcon();
                }
            } else if(model.isPressed() && model.isArmed()) {
                altIcon = b.getPressedIcon();
                if(altIcon == null) {
                    // Use selected icon
                    altIcon = b.getSelectedIcon();
                }
            } else if(model.isSelected()) {
                if(b.isRolloverEnabled() && model.isRollover()) {
                        altIcon = b.getRolloverSelectedIcon();
                        if (altIcon == null) {
                                altIcon = b.getSelectedIcon();
                        }
                } else {
                        altIcon = b.getSelectedIcon();
                }
            } else if(b.isRolloverEnabled() && model.isRollover()) {
                altIcon = b.getRolloverIcon();
            }

            if(altIcon == null) {
                altIcon = b.getIcon();
            }

            altIcon.paintIcon(c, g2, iconRect.x, iconRect.y);

        } else {
            getDefaultIcon().paintIcon(c, g2, iconRect.x, iconRect.y);
        }


        // Draw the Text
        if(text != null) {
            View v = (View) c.getClientProperty(BasicHTML.propertyKey);
            if (v != null) {
                v.paint(g2, textRect);
            } else {
                paintText(g2, b, textRect, text);
            }
            if(b.hasFocus() && b.isFocusPainted() &&
               textRect.width > 0 && textRect.height > 0 ) {
                paintFocus(g2, textRect, size);
            }
        }
        g2.dispose();
    }
	
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
		textColor = UIManager.getColor("RadioButton.textColor");
		icon = UIManager.getIcon("RadioButton.icon");
	}
	
}
