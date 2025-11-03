package casino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
    
    private void createTitle() {
        JLabel title = new JLabel("CASINO SLOTS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(new Color(255, 215, 0));
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(20, 30, 60));
        titlePanel.add(title, BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.NORTH);
    }
}
