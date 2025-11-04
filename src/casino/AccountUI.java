package casino;

import javax.swing.*;
import java.awt.*;

public class AccountUI {
    private JFrame frame;
    private final AccountManager.Player player;
    @SuppressWarnings("unused")
	private final AccountManager accountManager;

    public AccountUI(AccountManager.Player player, AccountManager accountManager) {
        this.player = player;
        this.accountManager = accountManager;

        initUI(); 
    }

    private void initUI() {
        frame = new JFrame("Player Profile");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(20, 30, 60));

        JLabel name = new JLabel("Username: " + player.getNickname(), SwingConstants.CENTER);
        name.setFont(new Font("Arial", Font.BOLD, 18));
        name.setForeground(Color.WHITE);

        JLabel balance = new JLabel("Balance: " + player.getBalance(), SwingConstants.CENTER);
        balance.setFont(new Font("Arial", Font.BOLD, 18));
        balance.setForeground(Color.GREEN);

        JLabel info = new JLabel("Your account is secure 🎲", SwingConstants.CENTER);
        info.setForeground(Color.LIGHT_GRAY);

        frame.add(name);
        frame.add(balance);
        frame.add(info);

        frame.setVisible(true);
    }
}
