package clientsPackage;

import javax.swing.JRadioButton;

public class MyRadioButton extends JRadioButton {

	private static final long serialVersionUID = 1L;
	private String name;
	
	public MyRadioButton(String name){
		super(name,false);
		this.name=name;
	}
	public String toString(){
		return name;
	}
}
