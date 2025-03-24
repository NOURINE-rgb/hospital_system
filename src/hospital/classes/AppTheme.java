package hospital.classes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class AppTheme {
	// Colours
	public static final Color primaryColor = new Color(41, 128, 185);  // Blue
    public static final Color backgroundColor = new Color(236, 240, 241); // Light Gray
    public static final Color buttonColor = new Color(52, 152, 219); // Light Blue
    public static final Color buttonTextColor = Color.WHITE;
    public static final Color errorColor = new Color(231, 76, 60); // Red
    public static final Color successColor = new Color(46, 204, 113);
	// fonts
	    public static final Font titleFont = new Font("Arial", Font.BOLD, 20);
	    public static final Font buttonFont = new Font("Arial", Font.BOLD, 14);
	    public static final Font tableFont = new Font("Arial", Font.PLAIN, 14);
	    public static final Font tableHeader = new Font("Arial", Font.BOLD, 14);
	// sizes 
	    public static final int buttonWidth = 200;
	    public static final int buttonHeight = 40;
	// button style
	    public static void styleButton(JButton button, Color bgColor, Color textColor) {
	        button.setBackground(bgColor);
	        button.setForeground(textColor);
	        button.setFont(AppTheme.buttonFont);
	        button.setFocusPainted(false);
	        button.setBorderPainted(false);
	        button.setOpaque(true);
	    }
}
