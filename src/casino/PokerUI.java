package casino;

import javax.swing.*;
import java.awt.*;

public class PokerUI {
    public PokerUI(AccountManager.Player player, AccountManager accountManager) {
        JFrame frame = new JFrame("Poker");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(20, 30, 60));
        frame.add(new JLabel("Poker game will be here soon 🎴", SwingConstants.CENTER));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
