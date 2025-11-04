package casino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LobbyUI {

	private JFrame frame;
    private final AccountManager.Player player;
    private final AccountManager accountManager;

    public LobbyUI(AccountManager.Player player, AccountManager accountManager) {
        this.player = player;
        this.accountManager = accountManager;
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Casino Lobby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(15, 30, 60));
        frame.setLocationRelativeTo(null);

        createTopBar();
        createMainContent();

        frame.setVisible(true);
    }
	
}
