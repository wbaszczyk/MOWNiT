package clientsPackage;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.net.*;
import java.io.*;

public class ButtonAction extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private MyPanel panel;

	ButtonAction(MyPanel panel) {
		super("Connect and send request");
		this.panel = panel;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String serverName = "localhost";
	      int port = 6066;
	      try
	      {
	    	  StyledDocument doc = this.panel.getTextPane().getStyledDocument();
	    	  doc.insertString(doc.getLength(),"Connecting to " + serverName
                      + " on port " + port+"\n", null);
	         Socket client = new Socket(serverName, port);
	         doc.insertString(doc.getLength(),"Just connected to "
                     + client.getRemoteSocketAddress()+"\n", null);
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);
	         out.writeUTF(this.panel.getSelectedRadio()
                     + client.getLocalSocketAddress());
	         InputStream inFromServer = client.getInputStream();
	         DataInputStream in =
	                        new DataInputStream(inFromServer);
	         doc.insertString(doc.getLength(),"Server says " + in.readUTF()+"\n", null);
	         client.close();
	      }catch(IOException  e1)
	      {
	         e1.printStackTrace();
	      } catch (BadLocationException e1) {

			e1.printStackTrace();
		} 
	   
	}

}
