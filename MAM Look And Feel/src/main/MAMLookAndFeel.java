package main;

import java.awt.Color;

import javax.swing.UIDefaults;
import javax.swing.border.LineBorder;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;

import main.util.MAMIconFactory;

public class MAMLookAndFeel extends BasicLookAndFeel
{
	
	@Override
	public String getName()
	{
		return "MAMSkin";
	}
	
	@Override
	public String getID()
	{
		return null;
	}
	
	@Override
	public String getDescription()
	{
		return null;
	}
	
	@Override
	public boolean isNativeLookAndFeel()
	{
		return false;
	}
	
	@Override
	public boolean isSupportedLookAndFeel()
	{
		return true;
	}
	
	protected void initClassDefaults(UIDefaults table)
	{
		super.initClassDefaults(table);
		final String MAMPackageName = "main.ui.";
		
		Object[] uiDefaults = {
				"ButtonUI", MAMPackageName + "MAMButtonUI",
				"PanelUI", MAMPackageName + "MAMPanelUI",
				"LabelUI", MAMPackageName + "MAMLabelUI",
				"CheckBoxUI", MAMPackageName + "MAMCheckBoxUI",
				"RadioButtonUI", MAMPackageName + "MAMRadioButtonUI",
				"ToggleButtonUI", MAMPackageName + "MAMToggleButtonUI",
				"ProgressBarUI", MAMPackageName + "MAMProgressBarUI",
                "ComboBoxUI", MAMPackageName + "MAMComboBoxUI",
                "SeparatorUI", MAMPackageName + "MAMSeparatorUI",
              "FileChooserUI", MAMPackageName + "MAMFileChooserUI",
//              "DesktopIconUI", MAMPackageName + "MAMDesktopIconUI",
//            "InternalFrameUI", MAMPackageName + "MAMInternalFrameUI",
//       "PopupMenuSeparatorUI", MAMPackageName + "MAMPopupMenuSeparatorUI",
//                "ScrollBarUI", MAMPackageName + "MAMScrollBarUI",
//               "ScrollPaneUI", MAMPackageName + "MAMScrollPaneUI",
//                   "SliderUI", MAMPackageName + "MAMSliderUI",
//                "SplitPaneUI", MAMPackageName + "MAMSplitPaneUI",
//               "TabbedPaneUI", MAMPackageName + "MAMTabbedPaneUI",
//                  "ToolBarUI", MAMPackageName + "MAMToolBarUI",
//                     "TreeUI", MAMPackageName + "MAMTreeUI",
		};
		table.putDefaults(uiDefaults);
	}
	
	protected void initSystemColorDefaults(UIDefaults table)
	{
		super.initSystemColorDefaults(table);
	}
	
	@Override
	protected void initComponentDefaults(UIDefaults table) {
		super.initComponentDefaults(table);
		
		//BUTTON
		Color buttonBackgroundColor = Color.decode("#4A4A4A");
		Color buttonBackgroundGradientColor = Color.decode("#313131");
		Color buttonBackgroundRolloverColor = Color.decode("#FFFAF0");
		Color buttonBackgroundGradientRolloverColor = Color.decode("#B2AFA8");
		Color buttonTextColor = Color.decode("#C8C8C8");
		Color buttonTextRolloverColor = Color.decode("#696969");
		Color buttonFocusRingColor = Color.decode("#A9A9A9");
		Color buttonFocusRingRolloverColor = Color.decode("#4A4A4A");
		Color buttonBorderColor = Color.GRAY;
		InsetsUIResource buttonInsets = new InsetsUIResource(2, 14, 2, 14);
		
		//PANEL
		Color panelExternalColor = Color.decode("#333333");
		Color panelInternalColor = Color.decode("#111111");
		boolean panelGradient = false;
		
		//LABEL
		Color labelTextColor = Color.decode("#C8C8C8");
		
		//RADIOBUTTON
		Color radioButtonTextColor = Color.decode("#C8C8C8");
		Color radioDotBackgroundColor = Color.decode("#999999");
		Color radioDotCircleColor = Color.decode("#181818");
		Color radioDot = Color.decode("#555555");
		
		//CHECKBOX
		Color checkBoxTextColor = Color.decode("#C8C8C8");
		Color checkBackgroundColor = Color.decode("#999999");
		Color checkBorderColor = Color.decode("#181818");
		Color checkColor = Color.decode("#555555");
		
		//TOGGLEBUTTON
		Color toggleButtonBackgroundColor = Color.decode("#4A4A4A");
		Color toggleButtonBackgroundGradientColor = Color.decode("#313131");
		Color toggleButtonBackgroundRolloverColor = Color.decode("#FFFAF0");
		Color toggleButtonBackgroundGradientRolloverColor = Color.decode("#B2AFA8");
		Color toggleButtonTextColor = Color.decode("#C8C8C8");
		Color toggleButtonTextRolloverColor = Color.decode("#696969");
		Color toggleButtonFocusRingColor = Color.decode("#A9A9A9");
		Color toggleButtonFocusRingRolloverColor = Color.decode("#4A4A4A");
		Color toggleButtonBorderColor = Color.GRAY;
		Color selectedColor = Color.decode("#696969");
		Color selectedGradientColor = Color.decode("#464646");
		
		//PROGRESSBAR
		Color progressBarBackground = Color.decode("#555555");
		Color progressBarForeground = Color.decode("#999999");
		Color progressBarTextBackground = Color.decode("#999999");
		Color progressBarTextForeground = Color.decode("#555555");
		Color progressBarBackgroundGradientColor = Color.decode("#232323");
		Color progressBarForegroundGradientColor = Color.decode("#797979");
		Color progressBarBorderColor = Color.GRAY;
		
		//TOOLTIP
		Color foregroundToolTipColor = Color.decode("#C8C8C8");
		Color backgroundToolTipColor = Color.decode("#4A4A4A");
		Color tooltipBorderColor = Color.GRAY;
		
		//TEXTFIELD
		Color textfieldBackground = Color.decode("#4A4A4A");
		Color textfieldCaretColor = Color.decode("#C8C8C8");
		Color textfieldTextColor = Color.decode("#C8C8C8");
		Color textfieldInactiveBackground = Color.decode("#333333");
		Color textfieldInactiveForeground = Color.decode("#8C8C8C");
		Color textfieldSelectionBackground = Color.decode("#696969");
		Color textfieldSelectionForeground = Color.decode("#FFFFFF");
		
		//COMBOBOX
		
		Color comboBoxBackground = Color.decode("#4A4A4A");
		Color comboBoxTextColor = Color.decode("#C8C8C8");
		Color comboBoxInactiveBackground = comboBoxBackground.darker();
		Color comboBoxInactiveForeground = comboBoxTextColor.brighter();
		Color comboBoxBorderColor = Color.GRAY;
		Color comboBoxSelectionBackground = Color.decode("#696969");
		Color comboBoxSelectionForeground = Color.decode("#FFFFFF");
		Color comboBoxArrowButtonColor = Color.GRAY;
		Color comboBoxArrowDisabledButtonColor = Color.BLACK;
		//SEPARATOR
		Color separatorForeground = new Color(Color.DARK_GRAY.getRed(), Color.DARK_GRAY.getRed(), Color.DARK_GRAY.getRed(), 0);
		Color separatorBackground = Color.GRAY;
		
		//DEFAULTS
		Object[] defaults = {
				//BUTTON
				"Button.border", new LineBorder(buttonBorderColor, 1),
				"Button.background", buttonBackgroundColor,
				"Button.backgroundGradient", buttonBackgroundGradientColor,
				"Button.backgroundRolloverColor", buttonBackgroundRolloverColor,
				"Button.backgroundGradientRolloverColor", buttonBackgroundGradientRolloverColor,
				"Button.textColor", buttonTextColor,
				"Button.textRolloverColor", buttonTextRolloverColor,
				"Button.focusRingColor", buttonFocusRingColor,
				"Button.focusRingRolloverColor", buttonFocusRingRolloverColor,
				"Button.focusLineDistance", 3,
				"Button.margin", buttonInsets,
				
				//PANEL
				"Panel.background", panelExternalColor,
				"Panel.backgroundGradient", panelInternalColor,
				"Panel.gradientActive", panelGradient,
				
				//LABEL
				"Label.textColor", labelTextColor,
				
				//RADIOBUTTON
				"RadioButton.textColor",radioButtonTextColor,
				"RadioButton.icon", MAMIconFactory.getRadioButtonIcon(),
				"RadioButton.dotBackgroundColor", radioDotBackgroundColor,
				"RadioButton.dotCircleColor", radioDotCircleColor,
				"RadioButton.radioDot", radioDot,
				
				//CHECKBOX
				"CheckBox.textColor", checkBoxTextColor,
				"CheckBox.icon", MAMIconFactory.getCheckBoxIcon(),
				"CheckBox.checkBackgroundColor", checkBackgroundColor,
				"CheckBox.checkBorderColor", checkBorderColor,
				"CheckBox.checkColor", checkColor,
				
				//TOGGLEBUTTON
				"ToggleButton.border", new LineBorder(toggleButtonBorderColor, 1),
				"ToggleButton.background", toggleButtonBackgroundColor,
				"ToggleButton.backgroundGradient", toggleButtonBackgroundGradientColor,
				"ToggleButton.backgroundRolloverColor", toggleButtonBackgroundRolloverColor,
				"ToggleButton.backgroundGradientRolloverColor", toggleButtonBackgroundGradientRolloverColor,
				"ToggleButton.textColor", toggleButtonTextColor,
				"ToggleButton.textRolloverColor", toggleButtonTextRolloverColor,
				"ToggleButton.focusRingColor", toggleButtonFocusRingColor,
				"ToggleButton.focusRingRolloverColor", toggleButtonFocusRingRolloverColor,
				"ToggleButton.selectedColor", selectedColor,
				"ToggleButton.selectedGradientColor", selectedGradientColor,
				"ToggleButton.focusLineDistance", 3,
				
				//PROGRESSBAR
				"ProgressBar.background", progressBarBackground,
				"ProgressBar.foreground", progressBarForeground,
				"ProgressBar.selectionBackground", progressBarTextBackground,
				"ProgressBar.selectionForeground", progressBarTextForeground,
				"ProgressBar.repaintInterval", 20,
				"ProgressBar.backgroundGradient", true,
				"ProgressBar.backgroundGradientColor", progressBarBackgroundGradientColor,
				"ProgressBar.foregroundGradient", true,
				"ProgressBar.foregroundGradientColor", progressBarForegroundGradientColor,
				"ProgressBar.border", new LineBorder(progressBarBorderColor, 1),
				
				//TOOLTIP
				"ToolTip.foreground", foregroundToolTipColor,
				"ToolTip.background", backgroundToolTipColor,
				"ToolTip.border", new LineBorder(tooltipBorderColor, 1),
				
				//TEXTFIELD
				"TextField.background", textfieldBackground,
				"TextField.caretForeground", textfieldCaretColor,
				"TextField.foreground", textfieldTextColor,
				"TextField.border", new LineBorder(toggleButtonBorderColor, 1),
				"TextField.inactiveBackground", textfieldInactiveBackground,
				"TextField.inactiveForeground", textfieldInactiveForeground,
				"TextField.selectionBackground", textfieldSelectionBackground,
				"TextField.selectionForeground", textfieldSelectionForeground,
				
				//COMBOBOX
				"ComboBox.background", comboBoxBackground,
				"ComboBox.disabledBackground", comboBoxInactiveBackground,
				"ComboBox.foreground", comboBoxTextColor,
				"ComboBox.disabledForeground", comboBoxInactiveForeground,
				"ComboBox.selectionBackground", comboBoxSelectionBackground,
				"ComboBox.selectionForeground", comboBoxSelectionForeground,
				"ComboBox.border", new LineBorder(comboBoxBorderColor, 1),
				"ComboBox.arrowButtonColor", comboBoxArrowButtonColor,
				"ComboBox.arrowDisabledButtonColor", comboBoxArrowDisabledButtonColor,
				
				//SEPARATOR
				"Separator.background", separatorBackground,
				"Separator.foreground", separatorForeground
				
		};
		table.putDefaults(defaults);
	}
	
}
