package clientsPackage;

import javax.swing.*;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MyFrame() {
		super("Client");
		JPanel panel = new MyPanel();
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(800,400);
		setVisible(true);
	}
}
