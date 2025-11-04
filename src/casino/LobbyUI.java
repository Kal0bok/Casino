package casino;

import javax.swing.*;
import java.awt.*;

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
        frame.getContentPane().setBackground(new Color(15, 25, 50));
        frame.setLocationRelativeTo(null);

        frame.add(createTopPanel(), BorderLayout.NORTH);
        frame.add(createMiddlePanel(), BorderLayout.CENTER);
        frame.add(createBottomPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(20, 30, 70));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("CASINO", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        titleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                frame.dispose();
                new MainMenu();
            }
        });

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navPanel.setBackground(new Color(20, 30, 70));

        JButton casinoButton = createNavButton("Casino");
        casinoButton.addActionListener(e -> {
            frame.dispose();
            new casinoUI(player, accountManager);
        });

        JButton rouletteButton = createNavButton("Roulette");
        rouletteButton.addActionListener(e -> openPlaceholder("Roulette"));

        JButton pokerButton = createNavButton("Poker");
        pokerButton.addActionListener(e -> openPlaceholder("Poker"));

        JButton esportsButton = createNavButton("Esports");
        esportsButton.addActionListener(e -> openPlaceholder("Esports"));

        JButton virtualSportsButton = createNavButton("Virtual Sports");
        virtualSportsButton.addActionListener(e -> openPlaceholder("Virtual Sports"));

        JButton sportsButton = createNavButton("Sports");
        sportsButton.addActionListener(e -> openPlaceholder("Sports"));

        navPanel.add(casinoButton);
        navPanel.add(rouletteButton);
        navPanel.add(pokerButton);
        navPanel.add(esportsButton);
        navPanel.add(virtualSportsButton);
        navPanel.add(sportsButton);

        JButton accountButton = createNavButton("My Account (" + player.getNickname() + ")");
        accountButton.addActionListener(e -> {
            frame.dispose();
            new AccountUI(player, accountManager);
        });

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(navPanel, BorderLayout.CENTER);
        topPanel.add(accountButton, BorderLayout.EAST);

        return topPanel;
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(40, 60, 100));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel createMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(2, 1, 10, 10));
        middlePanel.setBackground(new Color(15, 25, 50));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel promoPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        promoPanel.setBackground(new Color(15, 25, 50));

        JLabel gifLabel = new JLabel(new ImageIcon(getClass().getResource("/GIF/lobby.gif")), SwingConstants.CENTER);
        gifLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

        String promoHTML = "<html>"
                + "<div style='text-align:center;'>"
                + "<h2 style='color:#FFD700;'>200 CREDITS EVERY DAY IN SLOTS PUSH</h2>"
                + "<p style='color:white; font-size:14px;'>"
                + "Spin in November! Play your favorite slots and get cashback.<br>"
                + "Up to 200 credits per day and up to 6000 credits for the whole month."
                + "</p>"
                + "</div>"
                + "</html>";

        JLabel promoText = new JLabel(promoHTML, SwingConstants.CENTER);

        promoPanel.add(gifLabel);
        promoPanel.add(promoText);

        JPanel categoriesPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        categoriesPanel.setBackground(new Color(15, 25, 50));

        categoriesPanel.add(createGameCard("Poker", getClass().getResource("/GIF/poker.gif"), "Play poker and win big!"));
        categoriesPanel.add(createGameCard("Spins", getClass().getResource("/GIF/slots.gif"), "Try your luck with slot machines!"));
        categoriesPanel.add(createGameCard("Roulette", getClass().getResource("/GIF/roulette.gif"), "Spin the wheel and feel the thrill!"));

        middlePanel.add(promoPanel);
        middlePanel.add(categoriesPanel);

        return middlePanel;
    }


    private JPanel createGameCard(String title, Object gifPathOrURL, String description) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(25, 40, 80));
        card.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2, true));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JLabel gifLabel;
        if (gifPathOrURL instanceof String) {
            gifLabel = new JLabel(new ImageIcon((String) gifPathOrURL), SwingConstants.CENTER);
        } else if (gifPathOrURL instanceof java.net.URL) {
            gifLabel = new JLabel(new ImageIcon((java.net.URL) gifPathOrURL), SwingConstants.CENTER);
        } else {
            gifLabel = new JLabel("[GIF PLACEHOLDER]", SwingConstants.CENTER);
        }

        JLabel descLabel = new JLabel("<html><center>" + description + "</center></html>", SwingConstants.CENTER);
        descLabel.setForeground(Color.WHITE);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(gifLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createBottomPanel() {
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(20, 30, 70));
        bottom.add(new JLabel("© 2025 Casino Platform"));
        bottom.setForeground(Color.WHITE);
        return bottom;
    }

    private void openPlaceholder(String sectionName) {
        frame.dispose();
        JFrame placeholder = new JFrame(sectionName + " — Coming Soon");
        placeholder.setSize(600, 400);
        placeholder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        placeholder.setLocationRelativeTo(null);
        placeholder.getContentPane().setBackground(new Color(20, 30, 60));

        JLabel label = new JLabel(sectionName + " section coming soon!", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 22));

        JButton back = new JButton("← Back to Lobby");
        back.setBackground(new Color(40, 60, 100));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.addActionListener(e -> {
            placeholder.dispose();
            new LobbyUI(player, accountManager);
        });

        placeholder.add(label, BorderLayout.CENTER);
        placeholder.add(back, BorderLayout.SOUTH);
        placeholder.setVisible(true);
    }
}
