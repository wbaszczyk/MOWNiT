package clientsPackage;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 100;
	public static final int WIDTH = 300;
	private JButton button;
	private JTextPane textPane;
	private JRadioButton addButton, readButton, writeButton, deleteButton;

	public MyPanel() {

		button = new ButtonAction(this);
		textPane = new MyTextPane();
		addButton = new MyRadioButton("Add");
		readButton = new MyRadioButton("Read");
		writeButton = new MyRadioButton("Write");
		deleteButton= new MyRadioButton("Delete");
		
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(addButton);
		bgroup.add(readButton);
		bgroup.add(writeButton);
		bgroup.add(deleteButton);
		addButton.setSelected(true);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(3, 1));
		radioPanel.add(addButton);
		radioPanel.add(readButton);
		radioPanel.add(writeButton);
		radioPanel.add(deleteButton);

		radioPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Type"));

		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		add(radioPanel);
		add(button);
		add(textPane);
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public String getSelectedRadio() {

		if (addButton.isSelected())
			return addButton.toString();
		else if (readButton.isSelected())
			return readButton.toString();
		else if (writeButton.isSelected())
			return writeButton.toString();
		else return deleteButton.toString();

	}
}
