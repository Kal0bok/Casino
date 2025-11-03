package casino;

import javax.swing.*;

public class balanceManager { 
    private long balance;
    private final casinoUI ui; 

    public balanceManager(casinoUI ui, long initialBalance) {
        this.ui = ui;
        this.balance = initialBalance;
        updateBalance();
    }

    public void add(long amount) {
        balance += amount;
        updateBalance();
        ui.updatePlayerBalance(); 
    }

    public void subtract(long amount) {
        if (amount > balance) amount = balance;
        balance -= amount;
        updateBalance();
        ui.updatePlayerBalance();
    }

    public long getBalance() {
        return balance;
    }

    public void updateBalance() {
        SwingUtilities.invokeLater(() -> {
            ui.getBalanceLabel().setText("Balance: " + format(balance));
        });
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