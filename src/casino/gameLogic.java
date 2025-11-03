package casino;

import java.util.Random;

public class gameLogic {
	private static final String[] EMOJIS = {"Cherry", "Lemon", "Watermelon", "Beer", "Diamond"};
    private static final Random random = new Random();

    private final casinoUI ui;

    public gameLogic(casinoUI ui) {
        this.ui = ui;
	
}
    public void spin(String betInput) {
        ui.getSpinButton().setEnabled(false);
        ui.getResultLabel().setText("Spinning...");
        ui.getResultLabel().setForeground(java.awt.Color.YELLOW);

        long bet;
        try {
            bet = Long.parseLong(betInput.trim());
        } catch (Exception e) {
            showError("Error: Enter a valid number!");
            return;
        }

        if (bet <= 0) {
            showError("Bet must be > 0!");
            return;
        }

        if (bet > ui.getBalanceManager().getBalance()) {
            showError("Not enough balance! Max: " + ui.getBalanceManager().format(ui.getBalanceManager().getBalance()));
            return;
        }

        ui.getBalanceManager().subtract(bet);
        animateReels(() -> finishSpin(bet));
    }
}