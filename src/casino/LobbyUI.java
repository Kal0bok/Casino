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

private void createMainContent() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(15, 30, 60));

    // Top rectangle
    JPanel promoPanel = new JPanel(new GridLayout(1, 2));
    promoPanel.setBackground(new Color(25, 45, 85));
    promoPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    JLabel gifLabel = new JLabel("GIF PLACEHOLDER", SwingConstants.CENTER);
    gifLabel.setForeground(Color.LIGHT_GRAY);
    gifLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    JLabel textLabel = new JLabel("<html><h1 style='color:white;'>CASINO</h1><p style='color:lightgray;'>Your favorite place to play and win!</p></html>", SwingConstants.CENTER);

    promoPanel.add(gifLabel);
    promoPanel.add(textLabel);

    // Bottom section with 3 clickable game cards
    JPanel gamesPanel = new JPanel(new GridLayout(1, 3, 20, 0));
    gamesPanel.setBackground(new Color(15, 30, 60));
    gamesPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

    gamesPanel.add(createGameCard("Poker", "GIF_PATH_POKER", "Play cards, test your skill!", e -> openSection("Poker")));
    gamesPanel.add(createGameCard("Spins", "GIF_PATH_SPINS", "Try your luck on the slots!", e -> openSection("Casino")));
    gamesPanel.add(createGameCard("Roulette", "GIF_PATH_ROULETTE", "Spin the wheel and win big!", e -> openSection("Roulette")));

    mainPanel.add(promoPanel, BorderLayout.NORTH);
    mainPanel.add(gamesPanel, BorderLayout.CENTER);

    frame.add(mainPanel, BorderLayout.CENTER);
    private JPanel createGameCard(String title, String gifPath, String desc, java.awt.event.ActionListener listener) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(25, 45, 85));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                listener.actionPerformed(new ActionEvent(panel, ActionEvent.ACTION_PERFORMED, null));
            }
        });

        JLabel name = new JLabel(title, SwingConstants.CENTER);
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setForeground(Color.WHITE);

        JLabel gif = new JLabel("[GIF: " + gifPath + "]", SwingConstants.CENTER);
        gif.setForeground(Color.LIGHT_GRAY);
        gif.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        // TODO: Replace [GIF: ...] with actual GIF file using new ImageIcon("path")

        JLabel descLabel = new JLabel("<html><div style='color:lightgray; text-align:center;'>" + desc + "</div></html>", SwingConstants.CENTER);

        panel.add(name, BorderLayout.NORTH);
        panel.add(gif, BorderLayout.CENTER);
        panel.add(descLabel, BorderLayout.SOUTH);

        return panel;
    }
}