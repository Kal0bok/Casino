package casino;

import javax.swing.*;
import java.awt.*;

public class AccountUI {
	 private JFrame frame;
	    private final AccountManager.Player player;

	    public AccountUI(AccountManager.Player player) {
	        this.player = player;
	        initUI();
}
