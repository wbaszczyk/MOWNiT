package clientsPackage;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 100;
	public static final int WIDTH = 300;
	private JButton button;
	private JTextPane logPane, id;
	private JRadioButton addButton, readButton, writeButton, deleteButton;
	private JLabel label;

	public MyPanel() {

		button = new ButtonAction(this);
		logPane = new MyTextPane(false);
		id = new MyTextPane(true);
		addButton = new MyRadioButton("Add");
		readButton = new MyRadioButton("Read");
		writeButton = new MyRadioButton("Write");
		deleteButton= new MyRadioButton("Delete");
		addButton.setSelected(true);
		label = new JLabel("Put file name");
		
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(addButton);
		bgroup.add(readButton);
		bgroup.add(writeButton);
		bgroup.add(deleteButton);
		
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
		add(label);
		add(id);
		add(logPane);
	}

	public JTextPane getLogPane() {
		return logPane;
	}
	public JTextPane getIdPane() {
		return id;
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
