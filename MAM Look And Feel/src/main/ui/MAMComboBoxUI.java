package main.ui;

import java.awt.Color;
import java.awt.Insets;

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


public class MAMComboBoxUI extends BasicComboBoxUI
{
	
	private static Color backgroundColor;
	private static Color disabledBackgroundColor;
	private static Color foregroundColor;
	private static Color disabledForegroundColor;
	private static Color selectionBackgroundColor;
	private static Color selectionForegroundColor;
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
		disabledBackgroundColor = UIManager.getColor("ComboBox.disabledBackground");
		foregroundColor = UIManager.getColor("ComboBox.foreground");
		disabledForegroundColor = UIManager.getColor("ComboBox.disabledForeground");
		selectionBackgroundColor = UIManager.getColor("ComboBox.selectionBackground");
		selectionForegroundColor = UIManager.getColor("ComboBox.selectionForeground");
		arrowColor = UIManager.getColor("ComboBox.arrowButtonColor");
		disabledArrowColor = UIManager.getColor("ComboBox.arrowDisabledButtonColor");
	}
	
}
