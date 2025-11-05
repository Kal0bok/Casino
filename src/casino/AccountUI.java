package casino;

import javax.swing.*;
import java.awt.*;

public class AccountUI {
    private JFrame frame;
    private final AccountManager.Player player;
    private final AccountManager accountManager;

    public AccountUI(AccountManager.Player player, AccountManager accountManager) {
        this.player = player;
        this.accountManager = accountManager;
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Player Profile");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(20, 30, 60));
        frame.setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        infoPanel.setBackground(new Color(20, 30, 60));

        JLabel name = new JLabel("Username: " + player.getNickname(), SwingConstants.CENTER);
        name.setFont(new Font("Arial", Font.BOLD, 18));
        name.setForeground(Color.WHITE);

        JLabel balance = new JLabel("Balance: " + player.getBalance(), SwingConstants.CENTER);
        balance.setFont(new Font("Arial", Font.BOLD, 18));
        balance.setForeground(Color.GREEN);

        JLabel info = new JLabel("Your account is secure 🎲", SwingConstants.CENTER);
        info.setForeground(Color.LIGHT_GRAY);
        info.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(name);
        infoPanel.add(balance);
        infoPanel.add(info);

        JButton backButton = new JButton("← Back to Lobby");
        backButton.setBackground(new Color(40, 60, 100));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setPreferredSize(new Dimension(0, 20));
        backButton.addActionListener(e -> {
            frame.dispose();
            new LobbyUI(player, accountManager);
        });

        frame.add(infoPanel, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
