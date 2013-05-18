package serverPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

public class ServerMonitor implements Runnable {
	
	private FileSystem system;
	
	private JFrame window;
	private JPanel barsPanel;
	private JScrollPane scrollPane;
	Map<DataStorage, JProgressBar> bars;
	
	public ServerMonitor(FileSystem system)
	{
		this.system = system;
		
		bars = new HashMap<>();
		
		for(DataStorage ds : system.getStorages())
			bars.put(ds, new JProgressBar(0, 100));
	}

	@Override
	public void run() {
		
		initGUI();
		
		for(;;)
		{
			try { Thread.sleep(500); }
			catch (InterruptedException e) { }
			
			updateGUI();
		}
	}
	
	private void initGUI()
	{
		barsPanel = new JPanel();
		//barsPanel.setLayout(new BoxLayout(barsPanel, BoxLayout.Y_AXIS));
		barsPanel.setLayout(new GridLayout(bars.size(), 2));
		
		for(DataStorage ds : bars.keySet()) {
			barsPanel.add(new JLabel(ds.toString()));
			barsPanel.add(bars.get(ds));
		}
		
		/*
		for(JProgressBar pb : bars.values()) {
			
			barsPanel.add(pb);
		}*/
		
		window = new JFrame("Empty Window");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		window.add(barsPanel);
		window.pack();
		window.setVisible(true);
	}
	
	private void updateGUI()
	{
		for(DataStorage ds : bars.keySet()) {
			bars.get(ds).setValue((int)(100*ds.getFillFactor()));
		}
	}
}
