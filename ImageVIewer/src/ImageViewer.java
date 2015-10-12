import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;


//
//	Name:		Le, Emily
//	Homework: 	#1
//	Due:		10/12/15
//	Course:		cs-245-01-f15
//
//	Description:
//		This is a program that will display an image of types GIF, JPEG, or PNG.
//

public class ImageViewer
{
	public ImageViewer(String image)
	{
		// Create a new JFrame container
		JFrame jframe = new JFrame("L.Emily's Stopwatch");
		
		jframe.setLayout(new BorderLayout());

		
		// Give the frame the initial size
		jframe.setSize(800, 600);
		
		// Terminate the program when the user closes the application
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		
		// Create the object that will hold the image (GIF, JPEG, or PNG)
		ImageIcon icon = new ImageIcon(image);
		
		// Crate the label that will display the icon image
		JLabel imageLabel = new JLabel("image and text", icon, JLabel.CENTER);
		imageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		
		// adding image to the frame
		jframe.add(imageLabel);
		
		jframe.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		final String image = args[0];

		// Create the frame on the event dispatching thread
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ImageViewer(image);
			}
		});
	}
}
