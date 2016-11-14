package main.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

import main.util.MAMComboBoxButton;
import sun.swing.DefaultLookup;


public class MAMComboBoxUI extends BasicComboBoxUI
{
	
	private static Color backgroundColor;
	private static Color arrowColor;
	private static Color disabledArrowColor;
	private static int direction = SwingConstants.SOUTH;
	private MAMComboBoxButton arrowButton;

	public static ComponentUI createUI(JComponent c) {
	        setupColor();
	        return new MAMComboBoxUI();
	    }

	protected JButton createArrowButton() {
        arrowButton = new MAMComboBoxButton(direction, backgroundColor, arrowColor, disabledArrowColor);
        arrowButton.setMargin( new Insets( 0, 1, 1, 3 ) );
        return arrowButton;
    }
	
	 @Override
	    public void paint( Graphics g, JComponent c ) {
	        hasFocus = comboBox.hasFocus();
	        if ( !comboBox.isEditable() ) {
	        	
	            Rectangle r = rectangleForCurrentValue();
	            paintCurrentValueBackground(g,r,hasFocus);
	            paintCurrentValue(g,r,hasFocus);
	        }
	    }
	    
	@Override
	public void installUI( JComponent c )
	{
		super.installUI(c);
		JComboBox combo = (JComboBox) c;
		combo.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e)
			{
				arrowButton.setDirection(SwingConstants.NORTH);
				combo.repaint();
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
			{
				arrowButton.setDirection(SwingConstants.SOUTH);
				combo.repaint();
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e)
			{

			}
		});
	}
	
	private static void setupColor()
	{
		backgroundColor = UIManager.getColor("ComboBox.background");
		arrowColor = UIManager.getColor("ComboBox.arrowButtonColor");
		disabledArrowColor = UIManager.getColor("ComboBox.arrowDisabledButtonColor");
	}
	
}
