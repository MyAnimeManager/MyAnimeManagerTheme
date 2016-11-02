package main;

import java.awt.Color;

import javax.swing.UIDefaults;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;

import main.util.MAMIconFactory;

//TODO cambiare nomi costanti
public class MAMLookAndFeel extends MetalLookAndFeel
{
	
	@Override
	public String getName()
	{
		return null;
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
//                 "ComboBoxUI", MAMPackageName + "MAMComboBoxUI",
//              "DesktopIconUI", MAMPackageName + "MAMDesktopIconUI",
//              "FileChooserUI", MAMPackageName + "MAMFileChooserUI",
//            "InternalFrameUI", MAMPackageName + "MAMInternalFrameUI",
//       "PopupMenuSeparatorUI", MAMPackageName + "MAMPopupMenuSeparatorUI",
//                "ScrollBarUI", MAMPackageName + "MAMScrollBarUI",
//               "ScrollPaneUI", MAMPackageName + "MAMScrollPaneUI",
//                "SeparatorUI", MAMPackageName + "MAMSeparatorUI",
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
		Color buttonExternalColor = Color.decode("#4A4A4A");
		Color buttonInternalColor = Color.decode("#313131");
		Color buttonExternalRolloverColor = Color.decode("#FFFAF0");
		Color buttonInternalRolloverColor = Color.decode("#B2AFA8");
		Color buttonTextColor = Color.decode("#C8C8C8");
		Color buttonTextRolloverColor = Color.decode("#696969");
		Color buttonFocusRingColor = Color.decode("#A9A9A9");
		Color buttonFocusRingRolloverColor = Color.decode("#4A4A4A");
		Color buttonBorderColor = Color.GRAY;
		
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
		Color toggleButtonExternalColor = Color.decode("#4A4A4A");
		Color toggleButtonInternalColor = Color.decode("#313131");
		Color toggleButtonExternalRolloverColor = Color.decode("#FFFAF0");
		Color toggleButtonInternalRolloverColor = Color.decode("#B2AFA8");
		Color toggleButtonTextColor = Color.decode("#C8C8C8");
		Color toggleButtonTextRolloverColor = Color.decode("#696969");
		Color toggleButtonFocusRingColor = Color.decode("#A9A9A9");
		Color toggleButtonFocusRingRolloverColor = Color.decode("#4A4A4A");
		Color toggleButtonBorderColor = Color.GRAY;
		Color selectedExternalColor = Color.decode("#696969");
		Color selectedInternalColor = Color.decode("#464646");
		
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
		
		//DEFAULTS
		Object[] defaults = {
				//BUTTON
				"Button.border", new LineBorder(buttonBorderColor, 1),
				"Button.externalColor", buttonExternalColor,
				"Button.internalColor", buttonInternalColor,
				"Button.externalRolloverColor", buttonExternalRolloverColor,
				"Button.internalRolloverColor", buttonInternalRolloverColor,
				"Button.textColor", buttonTextColor,
				"Button.textRolloverColor", buttonTextRolloverColor,
				"Button.focusRingColor", buttonFocusRingColor,
				"Button.focusRingRolloverColor", buttonFocusRingRolloverColor,
				"Button.focusLineDistance", 3,
				
				//PANEL
				"Panel.externalColor", panelExternalColor,
				"Panel.internalColor", panelInternalColor,
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
				"ToggleButton.externalColor", toggleButtonExternalColor,
				"ToggleButton.internalColor", toggleButtonInternalColor,
				"ToggleButton.externalRolloverColor", toggleButtonExternalRolloverColor,
				"ToggleButton.internalRolloverColor", toggleButtonInternalRolloverColor,
				"ToggleButton.textColor", toggleButtonTextColor,
				"ToggleButton.textRolloverColor", toggleButtonTextRolloverColor,
				"ToggleButton.focusRingColor", toggleButtonFocusRingColor,
				"ToggleButton.focusRingRolloverColor", toggleButtonFocusRingRolloverColor,
				"ToggleButton.selectedExternalColor", selectedExternalColor,
				"ToggleButton.selectedInternalColor", selectedInternalColor,
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
				"TextField.selectionForeground", textfieldSelectionForeground
		};
		table.putDefaults(defaults);
	}
	
}
