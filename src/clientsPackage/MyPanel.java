package clientsPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 100;
	public static final int WIDTH = 300;
	private JButton button;
	private JTextPane logPane, id;
	private JRadioButton addButton, readButton, writeButton, deleteButton;
	private JLabel label;
	private ActionListener btnGrpListener = new BtnGrpListener();

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
		JScrollPane jsp = new JScrollPane(logPane);
		
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(addButton);
		addButton.addActionListener(btnGrpListener);
		bgroup.add(readButton);
		readButton.addActionListener(btnGrpListener);
		bgroup.add(writeButton);
		writeButton.addActionListener(btnGrpListener);
		bgroup.add(deleteButton);
		deleteButton.addActionListener(btnGrpListener);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(4, 1));
		radioPanel.add(addButton);
		radioPanel.add(readButton);
		radioPanel.add(writeButton);
		radioPanel.add(deleteButton);

		radioPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Type"));
		
		JPanel butNamLab=new JPanel();
		butNamLab.setLayout(new GridLayout(3, 1));
		butNamLab.add(button);
		butNamLab.add(label);
		butNamLab.add(id);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		add(radioPanel,BorderLayout.LINE_START);
		add(butNamLab,BorderLayout.BEFORE_FIRST_LINE);
		add(jsp,BorderLayout.CENTER);
		
	}

	public JLabel getLabel() {
		return label;
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

	private class BtnGrpListener implements ActionListener {

	    public void actionPerformed(ActionEvent ae) {
	    	if(ae.getActionCommand()==addButton.toString())
	    		label.setText("Put name file");
	    	else label.setText("Put id file");
	    }
	 }
}

