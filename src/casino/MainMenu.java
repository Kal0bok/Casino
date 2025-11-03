package casino;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class MainMenu {
	private final JFrame frame;

    public MainMenu() {
        frame = new JFrame("Slot Casino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(20, 30, 60));

        createTitle();
        createButtons();
        createInfoButtons();

        frame.setVisible(true);
    }
	
}
