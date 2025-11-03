package casino;

public class balanceManager {
	private long balance = 100_000_000_000_000L; // 100T
    private final casinoUI ui;

    public balanceManager(casinoUI ui) {
        this.ui = ui;
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
        ui.getClass(); 
        SwingUtilities.invokeLater(() ->
            ui.getClass();
        );
        java.awt.Component[] components = ui.getClass().getFields(); 
        try {
            java.lang.reflect.Field field = CasinoUI.class.getDeclaredField("balanceLabel");
            field.setAccessible(true);
            javax.swing.JLabel label = (javax.swing.JLabel) field.get(ui);
            label.setText("Balance: " + format(balance));
        } catch (Exception e) {
        }
    }
}
