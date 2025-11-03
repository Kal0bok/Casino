package casino;

import javax.swing.*;

import java.awt.GridLayout;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static final String FILE = "accounts.txt";
    private final Map<String, Player> accounts = new HashMap<>();
    private final MainMenu mainMenu;
	@SuppressWarnings("unused")
	private long balance;

    public AccountManager(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        loadAccounts();
        createAdminIfNotExists();
    }

    private void loadAccounts() {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":", 3);
                if (parts.length == 3) {
                    accounts.put(parts[0], new Player(parts[0], parts[1], Long.parseLong(parts[2])));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading accounts!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createAdminIfNotExists() {
        if (!accounts.containsKey("admin")) {
            accounts.put("admin", new Player("admin", "admin", 300_000_000_000_000L)); // 300T
            saveAccounts();
        }
    }

    public void register() {
        JTextField nickField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 10));
        panel.add(new JLabel("Nickname:"));
        panel.add(nickField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);

        int result = JOptionPane.showConfirmDialog(mainMenu.getFrame(), panel,
                "Register New Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nick = nickField.getText().trim();
            String pass = new String(passField.getPassword()).trim();

            if (nick.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(mainMenu.getFrame(), "Fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (accounts.containsKey(nick)) {
                JOptionPane.showMessageDialog(mainMenu.getFrame(), "Nickname already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            accounts.put(nick, new Player(nick, pass, 1000L));
            saveAccounts();
            JOptionPane.showMessageDialog(mainMenu.getFrame(),
                    "<html><h2>Account Created!</h2><p>Nickname: <b>" + nick + "</b><br>Balance: <b>1,000</b> coins</p></html>",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            loginAndStart(nick);
        }
    }

    public void login() {
        JTextField nickField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 10));
        panel.add(new JLabel("Nickname:"));
        panel.add(nickField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);

        int result = JOptionPane.showConfirmDialog(mainMenu.getFrame(), panel,
                "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nick = nickField.getText().trim();
            String pass = new String(passField.getPassword()).trim();

            Player player = accounts.get(nick);
            if (player == null || !player.password.equals(pass)) {
                JOptionPane.showMessageDialog(mainMenu.getFrame(), "Wrong nickname or password!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            loginAndStart(nick);
        }
    }

    private void loginAndStart(String nickname) {
        Player player = accounts.get(nickname);
        mainMenu.getFrame().dispose();
        new casinoUI(player);  
    }

    public long getBalance() {
        return balance;  
    }

    public void setBalance(long balance) {
        this.balance = balance;  
    }

    private void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Player p : accounts.values()) {
                bw.write(p.nickname + ":" + p.password + ":" + p.balance);
                bw.newLine();
            }
        } catch (Exception e) {
            System.err.println("Failed to save accounts: " + e.getMessage());
        }
    }

    public static class Player {
        private String nickname;
        private String password;
        private long balance;

        Player(String n, String p, long b) {
            this.nickname = n;
            this.password = p;
            this.balance = b;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPassword() {
            return password;
        }

        public long getBalance() {
            return balance;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }
    }
}