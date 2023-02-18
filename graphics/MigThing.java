package graphics;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MigThing {
	public static void main (String[]args) {
		new MigThing();
	}

	ButtonGroup btngroup = new ButtonGroup();
	JRadioButton btn1 = new JRadioButton("Windows");
	JRadioButton btn2 = new JRadioButton("Unix");
	
	MigThing(){
		JFrame window = new JFrame("Mig Example");
		window.setSize(1600,800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setLocationRelativeTo(null);

		ButtonGroup btngroup = new ButtonGroup();
		JRadioButton btn1 = new JRadioButton("Windows");
		JRadioButton btn2 = new JRadioButton("Unix");
		btngroup.add(btn1);
		btngroup.add(btn2);
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 4, insets 10"));
		GUIsetup(panel);

		window.add(panel);
		window.pack();
		window.setVisible(true);
	}

	private void GUIsetup(JPanel panel) {
		panel.setBackground(new Color(204,204,255));		

		panel.add(new JLabel("Name:"),"align right");
		panel.add(new JTextField("",17), "span 3, wrap");

		//Radio Buttons 
		btngroup.add(btn1);
		btngroup.add(btn2);
		panel.add(new JLabel("System:"), "align right");
		panel.add(btn1);
		btn1.setBackground(new Color(204,204,255));
		panel.add(btn2,"wrap");
		btn2.setBackground(new Color(204,204,255));

		//Checkboxes
		panel.add(new JLabel("Language:"), "align right");
		panel.add(new Colouring("Java"));
		panel.add(new Colouring("C++"));
		panel.add(new Colouring("Perl"),"wrap");

		//Combo box
		panel.add(new JLabel("Year:"),"align right");
		String[] year = {"2017","2018","2019","2020","2021","2022"};
		JComboBox years = new JComboBox(year);
		panel.add(years,"wrap");
		years.setBackground(new Color(204,204,255));
		
		//J Buttons
		panel.add(new JButton("Okay"),"align center, sizegroup a, skip 1");
		panel.add(new JButton("Cancel"),"align center, sizegroup a");
		
	}
	
	class Colouring extends JCheckBox {
		Colouring(String text) {
			super(text);
			
			this.setBackground(new Color(204,204,255));
		}
	}
}