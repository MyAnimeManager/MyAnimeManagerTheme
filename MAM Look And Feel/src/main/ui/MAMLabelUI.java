package main.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

import sun.awt.AppContext;
import sun.swing.SwingUtilities2;


public class MAMLabelUI extends BasicLabelUI
{
	private static final Object MAM_LABEL_UI_KEY = new Object();
	private static Color textColor;
	
	public static ComponentUI createUI(JComponent c) {
		AppContext appContext = AppContext.getAppContext();
		MAMLabelUI metalButtonUI =
                (MAMLabelUI) appContext.get(MAM_LABEL_UI_KEY);
        if (metalButtonUI == null) {
            metalButtonUI = new MAMLabelUI();
            appContext.put(MAM_LABEL_UI_KEY, metalButtonUI);
        }
        setupColor();
        return metalButtonUI;
    }

	protected void paintEnabledText(JLabel l, Graphics g, String s, int textX, int textY)
    {
        int mnemIndex = l.getDisplayedMnemonicIndex();
        g.setColor(textColor);
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemIndex,
                                                     textX, textY);
    }
	
	protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY)
    {
        int accChar = l.getDisplayedMnemonicIndex();
        g.setColor(textColor.darker());
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, accChar,
                                                   textX, textY);
    }
	
	
	private static void setupColor()
	{
		textColor = UIManager.getColor("Label.textColor");
	}
}
