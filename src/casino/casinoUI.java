package casino;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class сasinoUI {
    private final JFrame frame;
    private final JPanel reelPanel;
    private final JLabel[] reels = new JLabel[3];
    private final JTextField betField;
    private final JButton spinButton;
    private final JLabel balanceLabel;
    private final JLabel resultLabel;

    private final GameLogic gameLogic;
    private final BalanceManager balanceManager;

    public casinoUI() {
        gameLogic = new GameLogic(this);
        balanceManager = new BalanceManager(this);

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
