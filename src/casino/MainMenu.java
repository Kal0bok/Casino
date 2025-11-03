package casino;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private final JFrame frame;
    private final AccountManager accountManager;

    public MainMenu() {
        accountManager = new AccountManager(this);
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

    // ... (всё как раньше, но кнопки теперь вызывают AccountManager)

    private void createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 40, 100));
        buttonPanel.setBackground(new Color(20, 30, 60));

        JButton registerBtn = createStyledButton("REGISTER", new Color(50, 205, 50));
        JButton loginBtn = createStyledButton("LOGIN", new Color(30, 144, 255));
        JButton exitBtn = createStyledButton("EXIT", new Color(220, 20, 60));

        registerBtn.addActionListener(e -> accountManager.register());
        loginBtn.addActionListener(e -> accountManager.login());
        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(registerBtn);
        buttonPanel.add(loginBtn);
        buttonPanel.add(exitBtn);

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    // ... (createTitle, createInfoButtons, showHelp, showRules — без изменений)

    public JFrame getFrame() {
        return frame;
    }

    // Остальное — как в предыдущей версии
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

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 22));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void createInfoButtons() {
        JPanel cornerPanel = new JPanel(new BorderLayout());
        cornerPanel.setBackground(new Color(20, 30, 60));
        cornerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton helpBtn = new JButton("?");
        helpBtn.setFont(new Font("Arial", Font.BOLD, 18));
        helpBtn.setForeground(Color.CYAN);
        helpBtn.setBackground(new Color(40, 40, 80));
        helpBtn.setPreferredSize(new Dimension(40, 40));
        helpBtn.setToolTipText("How to play");
        helpBtn.addActionListener(e -> showHelp());

        JButton rulesBtn = new JButton("!");
        rulesBtn.setFont(new Font("Arial", Font.BOLD, 18));
        rulesBtn.setForeground(Color.YELLOW);
        rulesBtn.setBackground(new Color(80, 40, 40));
        rulesBtn.setPreferredSize(new Dimension(40, 40));
        rulesBtn.setToolTipText("Game Rules");
        rulesBtn.addActionListener(e -> showRules());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(20, 30, 60));
        leftPanel.add(helpBtn);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(20, 30, 60));
        rightPanel.add(rulesBtn);

        cornerPanel.add(leftPanel, BorderLayout.WEST);
        cornerPanel.add(rightPanel, BorderLayout.EAST);

        frame.add(cornerPanel, BorderLayout.SOUTH);
    }

    private void showHelp() {
        String help = """
            <html>
            <h2>How to Play:</h2>
            <ul>
            <li>Register or login</li>
            <li>Enter your bet</li>
            <li>Press <b>SPIN!</b></li>
            <li>Match symbols to win!</li>
            </ul>
            </html>
            """;
        JOptionPane.showMessageDialog(frame, help, "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showRules() {
        String rules = """
            <html>
            <h2>Game Rules:</h2>
            <table border='0'>
            <tr><td>Cherry Cherry Cherry</td><td>= x2</td></tr>
            <tr><td>Lemon Lemon Lemon</td><td>= x3</td></tr>
            <tr><td>Watermelon Watermelon Watermelon</td><td>= x4</td></tr>
            <tr><td>Beer Beer Beer</td><td>= x10</td></tr>
            <tr><td>Diamond Diamond Diamond</td><td>= x50</td></tr>
            <tr><td><i>Any 2 same</i></td><td>= x2</td></tr>
            </table>
            <p><b>Admin has 300T!</b></p>
            </html>
            """;
        JOptionPane.showMessageDialog(frame, rules, "Rules", JOptionPane.WARNING_MESSAGE);
    }
}