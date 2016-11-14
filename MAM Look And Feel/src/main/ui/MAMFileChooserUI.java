package main.ui;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFileChooserUI;


public class MAMFileChooserUI extends BasicFileChooserUI
{
	
	public MAMFileChooserUI(JFileChooser b)
	{
		super(b);
	}

	public static ComponentUI createUI(JComponent c) {
		 return new MAMFileChooserUI((JFileChooser) c);
    }
	
}
