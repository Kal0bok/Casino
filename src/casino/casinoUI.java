package casino;

import javax.swing.*;
import java.awt.*;

public class casinoUI {

    private JFrame frame;
    @SuppressWarnings("unused")
	private JPanel reelPanel;
    private JLabel[] reels = new JLabel[3];
    private JTextField betField;
    private JButton spinButton;
    private JLabel balanceLabel;
    private JLabel resultLabel;

    private final gameLogic gameLogic;
    private final balanceManager balanceManager;
	private AccountManager.Player player;

    public casinoUI(AccountManager.Player player) {
        this.player = player;

        this.balanceManager = new balanceManager(this, player.getBalance());
        this.gameLogic = new gameLogic(this);

        initializeUI();

        frame = new JFrame("Slot Casino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 400);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(15, 30, 60));

        reelPanel = setupReels();
        setupControlPanel();
        setupInfoPanel();

        frame.setVisible(true);
        balanceManager.updateBalance();
    }

    private void initializeUI() {
		// TODO Auto-generated method stub
		
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

    private void setupInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));
        panel.setBackground(new Color(15, 30, 60));

        balanceLabel = new JLabel("", SwingConstants.CENTER);
        balanceLabel.setForeground(Color.CYAN);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        resultLabel = new JLabel("Press SPIN to play!", SwingConstants.CENTER);
        resultLabel.setForeground(Color.LIGHT_GRAY);
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 16));

        panel.add(balanceLabel);
        panel.add(resultLabel);

        frame.add(panel, BorderLayout.NORTH);
    }
    
    public void updatePlayerBalance() {
        player.setBalance(balanceManager.getBalance());
    }

    public JLabel[] getReels() { return reels; }
    public JLabel getResultLabel() { return resultLabel; }
    public JButton getSpinButton() { return spinButton; }
    public balanceManager getbalanceManager() { return balanceManager; }
    public JLabel getBalanceLabel() { return balanceLabel; }  
}

