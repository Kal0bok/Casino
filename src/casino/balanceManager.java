package casino;

import java.lang.reflect.Field;

import javax.swing.SwingUtilities;

public class balanceManager {
    private long balance = 100_000_000_000_000L; // 100T
    private final casinoUI ui;

    public balanceManager(casinoUI casinoUI, long balance2) {
		this.ui = null;
		// TODO Auto-generated constructor stub
	}

	public void add(long amount) {
        balance += amount;
        updateBalance();
    }

    public void subtract(long amount) {
        balance -= amount;
        updateBalance();
    }

    public long getBalance() {
        return balance;
    }

    public void updateBalance() {
        ui.getClass(); // IDE fix
        SwingUtilities.invokeLater(() ->
        	ui.getClass()
        	);
        @SuppressWarnings("unused")
		Field[] components = ui.getClass().getFields();
        try {
            java.lang.reflect.Field field = casinoUI.class.getDeclaredField("balanceLabel");
            field.setAccessible(true);
            javax.swing.JLabel label = (javax.swing.JLabel) field.get(ui);
            label.setText("Balance: " + format(balance));
        } catch (Exception e) {
            // fallback
        }
    }

    public String format(long amount) {
        if (amount >= 1_000_000_000_000L) {
            return String.format("%.1fT", amount / 1_000_000_000_000.0);
        } else if (amount >= 1_000_000_000L) {
            return String.format("%.1fB", amount / 1_000_000_000.0);
        } else if (amount >= 1_000_000L) {
            return String.format("%.1fM", amount / 1_000_000.0);
        } else {
            return String.valueOf(amount);
        }
    }
}