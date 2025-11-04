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
	
    private void createTopBar() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(10, 20, 40));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));


        JLabel logo = new JLabel("🎰 CASINO", SwingConstants.LEFT);
        logo.setFont(new Font("Arial", Font.BOLD, 26));
        logo.setForeground(Color.ORANGE);
        logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                frame.dispose();
                new MainMenu(); 
            }
        });
}
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    centerPanel.setBackground(new Color(10, 20, 40));

    String[] sections = {"Casino", "Roulette", "Poker", "Esports", "Virtual Sports", "Sports"};
    for (String name : sections) {
        JButton btn = createNavButton(name);
        btn.addActionListener(e -> openSection(name));
        centerPanel.add(btn);
    }
}
	JButton profileBtn = new JButton(player.nickname + " ⮟");
	profileBtn.setFont(new Font("Arial", Font.BOLD, 16));
	profileBtn.setBackground(new Color(40, 60, 100));
	profileBtn.setForeground(Color.WHITE);
	profileBtn.setFocusPainted(false);
	profileBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	profileBtn.addActionListener(e -> openProfile());

	topPanel.add(logo, BorderLayout.WEST);
	topPanel.add(centerPanel, BorderLayout.CENTER);
	topPanel.add(profileBtn, BorderLayout.EAST);

	frame.add(topPanel, BorderLayout.NORTH);
}

private JButton createNavButton(String text) {
    JButton btn = new JButton(text);
    btn.setFont(new Font("Arial", Font.BOLD, 16));
    btn.setForeground(Color.WHITE);
    btn.setBackground(new Color(30, 50, 90));
    btn.setFocusPainted(false);
    btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    return btn;
}
private void openProfile() {
    new AccountUI(player);
}

private void openSection(String name) {
    frame.dispose();
    switch (name) {
        case "Casino" -> new SpinsUI(player, accountManager);
        case "Roulette" -> new RouletteUI(player, accountManager);
        case "Poker" -> new PokerUI(player, accountManager);
        case "Esports" -> new CyberSportUI(player, accountManager);
        case "Virtual Sports" -> new VirtualSportUI(player, accountManager);
        case "Sports" -> new SportUI(player, accountManager);
    }
}


}