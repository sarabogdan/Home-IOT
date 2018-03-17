import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PriceDisplay extends JFrame{
	private JLabel label;
	
	private JPanel panel;
	public PriceDisplay() {
		this.setTitle("Ripple price");
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		label = new JLabel("welcome", JLabel.CENTER);
	    label.setVerticalAlignment(JLabel.CENTER);

		Font labelFont = label.getFont();
		String labelText = label.getText();

		int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = label.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = label.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		label.setFont(new Font(labelFont.getName(), Font.PLAIN, 150));
		label.setForeground(Color.RED);
		label.setVerticalTextPosition(panel.getHeight() / 2);
		
	        panel.setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();

	        panel.add(label, gbc);

	        this.add(panel);
	        this.setVisible(true);
		
		
		
		this.show();
	}
	
	
	public void setPrice(String price) {
		this.label.setText(price);
	}
}
