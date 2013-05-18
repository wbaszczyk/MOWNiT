package clientsPackage;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MyTextPane extends JTextPane {
	
	private static final long serialVersionUID = 1L;

	MyTextPane(boolean editable) {
		super();
		StyledDocument doc = this.getStyledDocument();
		MutableAttributeSet standard = new SimpleAttributeSet();

		StyleConstants.setAlignment(standard, StyleConstants.ALIGN_CENTER);
		StyleConstants.setLeftIndent(standard, 40);
		StyleConstants.setRightIndent(standard, 40);

		Color szary = new Color(224, 226, 235);
		doc.setParagraphAttributes(0, 0, standard, true);
		this.setBackground(szary);
		this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		this.setEditable(editable);
		
	}
}
