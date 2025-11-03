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
}
