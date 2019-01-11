package demo.swingui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleWindow implements ActionListener {

	// Frame, components, and a console-reader.
	private JFrame frame;
	private JLabel label = new JLabel("Enter text:");
	private JTextField textField = new JTextField(20);
	private JButton button = new JButton("Click me");


	public SimpleWindow() {

		JPanel pane; // Pane, contains components

		// Create the JFrame, add components, set event handlers, and then make the frame visible.
		frame = new JFrame("My Simple Swing Frame");
		pane = new JPanel();
		pane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pane.add(label);
		pane.add(textField);
		pane.add(button);

		button.addActionListener(this);

		frame.getContentPane().add(pane);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(frame, 
				                      textField.getText(),
				                      "My Dialog Box", 
				                      JOptionPane.PLAIN_MESSAGE);
	}
}
