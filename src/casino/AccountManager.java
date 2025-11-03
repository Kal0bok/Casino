package casino;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {
	private static final String FILE = "accounts.txt";
    private final Map<String, Player> accounts = new HashMap<>();
    private final MainMenu mainMenu;
	
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
}
