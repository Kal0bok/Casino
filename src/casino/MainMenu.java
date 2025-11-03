package casino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
    
    private void createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 40, 100));
        buttonPanel.setBackground(new Color(20, 30, 60));

        JButton registerBtn = createStyledButton("REGISTER", new Color(50, 205, 50));
        JButton loginBtn = createStyledButton("LOGIN", new Color(30, 144, 255));
        JButton exitBtn = createStyledButton("EXIT", new Color(220, 20, 60));

        registerBtn.addActionListener(e -> showMessage("Registration coming soon!"));
        loginBtn.addActionListener(e -> {
            frame.dispose();
            new CasinoUI(); // Переход в игру
        });
        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(registerBtn);
        buttonPanel.add(loginBtn);
        buttonPanel.add(exitBtn);

        frame.add(buttonPanel, BorderLayout.CENTER);
    }
}
