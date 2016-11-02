package main.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.metal.MetalIconFactory;

public class MAMIconFactory extends MetalIconFactory
{
	private static Icon checkBoxIcon;
	private static Icon radioButtonIcon;
	
	public static Icon getCheckBoxIcon() {
        if (checkBoxIcon == null) {
            checkBoxIcon = new CheckBoxIcon();
        }
        return checkBoxIcon;
    }
	
	public static Icon getRadioButtonIcon() {
        if (radioButtonIcon == null) {
            radioButtonIcon = new RadioButtonIcon();
        }
        return radioButtonIcon;
    }
	
	private static class CheckBoxIcon implements Icon, UIResource, Serializable {

        protected int getControlSize() 
        { 
        	return 13; 
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
        	Graphics2D g2 = (Graphics2D) g.create();
        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
     				RenderingHints.VALUE_ANTIALIAS_ON);
            ButtonModel model = ((JCheckBox)c).getModel();
            int controlSize = getControlSize();
            if ( model.isEnabled() ) {
                if (model.isPressed() && model.isArmed()) {
                    g2.setColor(UIManager.getColor("CheckBox.checkBackgroundColor").darker());
                    g2.fillRoundRect( x + 1, y + 1, controlSize - 1, controlSize - 1, 5 , 5);
                    g2.setColor(UIManager.getColor("CheckBox.checkBorderColor").darker());
                    g2.drawRoundRect(x, y, controlSize, controlSize, 5, 5);
                    g2.setColor(UIManager.getColor("CheckBox.checkBorderColor"));
                    g2.drawRect(x+1, y+1, controlSize-2, controlSize-2);
                } else {
                	g2.setColor(UIManager.getColor("CheckBox.checkBackgroundColor"));
                    g2.fillRoundRect( x + 1, y + 1, controlSize - 1, controlSize - 1, 5 , 5);
                    g2.setColor(UIManager.getColor("CheckBox.checkBorderColor"));
                    g2.drawRoundRect(x, y, controlSize, controlSize, 5, 5);
                    g2.setColor(UIManager.getColor("CheckBox.checkBorderColor").brighter());
                    g2.drawRect(x+1, y+1, controlSize-2, controlSize-2);
                }
                g2.setColor(c.getForeground());
            } else {
                g2.setColor(UIManager.getColor("CheckBox.checkBackgroundColor").brighter());
                g2.drawRect( x, y, controlSize, controlSize);
            }

            if (model.isSelected()) {
                drawCheck(g2);
            }
            g2.dispose();
        }

        protected void drawCheck(Graphics g) {
         // get graphics and set hints
            int dimension = 19;
            BufferedImage checkMark = MAMLookAndFeelUtil.getBlankImage(dimension,
            		 				dimension);
             		Graphics2D graphics = (Graphics2D) checkMark.getGraphics();
             		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
             				RenderingHints.VALUE_ANTIALIAS_ON);
             
             		// create curved checkbox path
             		GeneralPath path = new GeneralPath();
             
             		path.moveTo(0.25f * dimension, 0.5f * dimension);
             		path.quadTo(0.37f * dimension, 0.6f * dimension, 0.47f * dimension,
             				0.8f * dimension);
             		path.quadTo(0.55f * dimension, 0.5f * dimension, 0.85f * dimension, 0f);
             
             		// compute the x-based clip for the visibility
             		float xClipStart = 0.15f * dimension;
             		float xClipEnd = 0.95f * dimension;
             
             		float xClipRealEnd = xClipStart + (xClipEnd - xClipStart)
             				* 1f;
             
             		graphics.setClip(0, 0, (int) Math.ceil(xClipRealEnd), dimension);
             
             		Stroke stroke = new BasicStroke((float) 0.15 * dimension,
             				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
             		graphics.setStroke(stroke);
             		graphics.setColor(UIManager.getColor("CheckBox.checkColor"));
             		graphics.draw(path);
             		
             		g.drawImage(checkMark, - 1, - 3, null);
        }

        public int getIconWidth() {
            return getControlSize();
        }

        public int getIconHeight() {
            return getControlSize();
        }
        
    }
	
	// se si usano i radioButton dare un occhiata a questa per l'icona
	private static class RadioButtonIcon implements Icon, UIResource, Serializable {
      
        public void paintIcon(Component c, Graphics g, int x, int y) {
            JRadioButton rb = (JRadioButton)c;
            ButtonModel model = rb.getModel();
            boolean drawDot = model.isSelected();

            Graphics2D g2 = (Graphics2D) g.create();
            
            Color dotColor = UIManager.getColor("RadioButton.radioDot");
            Color dotCircle = UIManager.getColor("RadioButton.dotCircleColor");
            Color interiorColor = UIManager.getColor("RadioButton.dotBackgroundColor");

            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
           g2.setRenderingHints(rh);
            // draw Dark Circle
           
           if (model.isPressed())
           {
               g2.setColor(interiorColor.darker());
               g2.fillOval(x, y, 10, 10);
               dotColor = dotColor.darker();
           }
           else
           {
               g2.setColor(interiorColor);
               g2.fillOval(x, y, 10, 10);
           }
           
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(2));
            g2.setColor(dotCircle);
            g2.drawOval(x, y, 10, 10);
            g2.setStroke(oldStroke);

            // selected dot
            if ( drawDot ) {
                g2.setColor(dotColor);
              
               Ellipse2D.Double dot = new Ellipse2D.Double(x + 3, y + 3, 5, 5);
               g2.fill(dot);
            }
            g2.dispose();
        }

        public int getIconWidth() {
            return 10;
        }

        public int getIconHeight() {
            return 10;
        }
    }
}
