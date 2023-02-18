package graphics;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class MigExample {
	public static void main(String[] args) {
		new MigExample();
	}

	MigExample() {
		JFrame window = new JFrame ("Mig example");
		window.setSize(700,300);		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); //centres the window
		//setting JFrame background (rarely done)
		window.getContentPane().setBackground(Color.decode("#440066"));

		JPanel panel = new JPanel( new MigLayout("wrap 4, insets 10 20") );
		setupGUI(panel);

		window.add(panel);
		window.pack();
		window.setVisible(true);
	}

	void setupGUI(JPanel p) {
		p.setBackground(new Color(220,220,255));		
		//name and textbox
		p.add(new JLabel("Name:"),"align right");
		p.add(new JTextField("Full name", 30),"span 2, wrap");
		//phone
		p.add(new JLabel("Cell phone:"),"align right");
		p.add(new JTextField( 10),"");

		p.add(new JLabel("Extra Curricular"), "skip 1");

		p.add(new JLabel("Grade:"),"align right");
		String s1[] = { "9", "10", "11", "12" };
		JComboBox grades = new JComboBox(s1);
		p.add(grades,"");

		p.add(new JCheckBox("sports"),"sizegroup g1, skip 1, wrap");
		p.add(new JCheckBox("clubs"),"sizegroup g1, skip 3, wrap");
		p.add(new JCheckBox("volunteer"),"sizegroup g1, skip 3, wrap");
	}
}
