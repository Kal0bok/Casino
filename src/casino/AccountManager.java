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
}
