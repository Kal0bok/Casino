package casino;

import javax.swing.*;
import java.awt.*;

public class casinoUI {

    private JFrame frame;
    private JPanel reelPanel;
    private JLabel[] reels = new JLabel[3];
    private JTextField betField;
    private JButton spinButton;
    private JButton backButton;
    private JLabel balanceLabel;
    private JLabel resultLabel;

    private final gameLogic gameLogic;
    private final balanceManager balanceManager;
    private final AccountManager.Player player;
    private final AccountManager accountManager;

    public casinoUI(AccountManager.Player player, AccountManager accountManager) {
        this.player = player;
        this.accountManager = accountManager;
        this.balanceManager = new balanceManager(this, player.getBalance());
        this.gameLogic = new gameLogic(this);

        initializeUI();
        balanceManager.setBalance(player.getBalance());
        balanceManager.updateBalance();
    }

    private void initializeUI() {
        frame = new JFrame("Spins");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 450);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(15, 30, 60));

        reelPanel = setupReels();
        setupInfoPanel();
        setupControlPanel();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel setupReels() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 15, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(new Color(10, 25, 45));

        for (int i = 0; i < 3; i++) {
            reels[i] = new JLabel("?", SwingConstants.CENTER);
            reels[i].setFont(new Font("Segoe UI Emoji", Font.BOLD, 70));
            reels[i].setForeground(Color.YELLOW);
            reels[i].setOpaque(true);
            reels[i].setBackground(new Color(20, 40, 80));
            reels[i].setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3, true));
            panel.add(reels[i]);
        }

        frame.add(panel, BorderLayout.CENTER);
        return panel;
    }

    private void setupInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.setBackground(new Color(15, 30, 60));

        backButton = new JButton("←");
        backButton.setBackground(new Color(40, 60, 100));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(40, 30));
        backButton.setMargin(new Insets(2, 6, 2, 6));
        backButton.addActionListener(e -> {
            frame.dispose();
            new LobbyUI(player, accountManager);
        });

        balanceLabel = new JLabel("", SwingConstants.CENTER);
        balanceLabel.setForeground(Color.CYAN);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        resultLabel = new JLabel("Press SPIN to play!", SwingConstants.CENTER);
        resultLabel.setForeground(Color.LIGHT_GRAY);
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 15));

        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setBackground(new Color(15, 30, 60));
        topRow.add(backButton, BorderLayout.WEST);
        topRow.add(balanceLabel, BorderLayout.CENTER);

        panel.add(topRow, BorderLayout.NORTH);
        panel.add(resultLabel, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.NORTH);
    }

    private void setupControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(20, 40, 70));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel betLabel = new JLabel("Bet:");
        betLabel.setForeground(Color.CYAN);
        betLabel.setFont(new Font("Arial", Font.BOLD, 16));

        betField = new JTextField("1000", 10);
        betField.setFont(new Font("Arial", Font.BOLD, 16));
        betField.setHorizontalAlignment(JTextField.CENTER);

        spinButton = new JButton("SPIN!");
        spinButton.setFont(new Font("Arial", Font.BOLD, 18));
        spinButton.setBackground(new Color(255, 215, 0));
        spinButton.setForeground(Color.BLACK);
        spinButton.setFocusPainted(false);
        spinButton.setPreferredSize(new Dimension(140, 45));
        spinButton.addActionListener(e -> gameLogic.spin(betField.getText()));

        panel.add(betLabel);
        panel.add(betField);
        panel.add(spinButton);

        frame.add(panel, BorderLayout.SOUTH);
    }

    public void updatePlayerBalance() {
        player.setBalance(balanceManager.getBalance());
    }

    public AccountManager.Player getPlayer() {
        return player;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public JLabel[] getReels() { return reels; }
    public JLabel getResultLabel() { return resultLabel; }
    public JButton getSpinButton() { return spinButton; }
    public balanceManager getbalanceManager() { return balanceManager; }
    public JLabel getBalanceLabel() { return balanceLabel; }
}
