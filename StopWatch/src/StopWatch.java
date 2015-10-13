
//
//	Name:		Le, Emily
//	Project 	#1
//	Due:		10/7/15
//	Course:		cs-245-01-f15
//
//	Description:
//		This is a simple stop watch program that servers as a GUI intro
//

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StopWatch implements ActionListener
{
	JLabel jlabel;
	long time = 0;
	boolean startPressed = false;
	
	public StopWatch(){
		
		// Create a new JFrame container
		JFrame jframe = new JFrame("L.Emily's Stopwatch");
		
		// Specify FlowLayout for the layout manager
		jframe.setLayout(new FlowLayout());
		
		// Give the frame the initial size
		jframe.setSize(280, 90);
		
		// Terminate the program when the user closes the application
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		
		// Make two buttons
		JButton start = new JButton("Start");
		JButton stop = new JButton("Stop");
		
		// Add action listeners
		start.addActionListener(this);
		stop.addActionListener(this);
		
		// Add the buttons to the content pane
		jframe.add(start);
		jframe.add(stop);
		
		// Create a text-based label
		jlabel = new JLabel("Press Start to begin timing.");
		
		// Add label to the frame
		jframe.add(jlabel);
		
		// Display the frame
		jframe.setVisible(true);
		
	}

	// Handle button events
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Start"))
		{
			jlabel.setText("The Stopwatch has started...");
			time = e.getWhen();
			startPressed = true;
		}
		else
		{
			time = e.getWhen() - time;
			if (startPressed == true)
			{
				jlabel.setText("Time Stopped: " + (time / 1000.0) + " sec");
			}
			else
			{
				jlabel.setText("You need to press the START button first");
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		// Create the frame on the event dispatching thread
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new StopWatch();
			}
		});
	}
}
