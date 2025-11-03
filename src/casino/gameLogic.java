package casino;

import java.util.Random;
import javax.swing.*;

public class gameLogic {
    private static final String[] EMOJIS = {"🍒", "🍋", "🍉", "🍺", "💎"};
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

        if (bet > ui.getbalanceManager().getBalance()) {
            showError("Not enough balance! Max: " + ui.getbalanceManager().format(ui.getbalanceManager().getBalance()));
            return;
        }

        ui.getbalanceManager().subtract(bet);
        animateReels(() -> finishSpin(bet));
    }

    private void animateReels(Runnable onFinish) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 14; i++) {
                    for (JLabel reel : ui.getReels()) {
                        reel.setText(EMOJIS[random.nextInt(EMOJIS.length)]);
                    }
                    Thread.sleep(110);
                }
                onFinish.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void finishSpin(long bet) {
        String[] result = generateSpin();
        long win = calculateWin(result, bet);

        SwingUtilities.invokeLater(() -> {
            displayResult(result);
            if (win > 0) {
                ui.getbalanceManager().add(win);
                ui.getResultLabel().setText("WIN: +" + ui.getbalanceManager().format(win) + " (x" + (win / bet) + ")");
                ui.getResultLabel().setForeground(java.awt.Color.GREEN);
            } else {
                ui.getResultLabel().setText("Lost. Try again!");
                ui.getResultLabel().setForeground(java.awt.Color.ORANGE);
            }
            ui.getSpinButton().setEnabled(true);

            if (ui.getbalanceManager().getBalance() <= 0) {
                ui.getResultLabel().setText("BANKRUPT! Game Over.");
                ui.getResultLabel().setForeground(java.awt.Color.RED);
                ui.getSpinButton().setEnabled(false);
            }
        });
    }

    private String[] generateSpin() {
        return new String[]{
            EMOJIS[random.nextInt(EMOJIS.length)],
            EMOJIS[random.nextInt(EMOJIS.length)],
            EMOJIS[random.nextInt(EMOJIS.length)]
        };
    }

    private void displayResult(String[] result) {
        for (int i = 0; i < 3; i++) {
            ui.getReels()[i].setText(result[i]);
        }
    }

    private long calculateWin(String[] result, long bet) {
        boolean allSame = result[0].equals(result[1]) && result[1].equals(result[2]);
        boolean twoSame = !allSame && (result[0].equals(result[1]) || result[1].equals(result[2]) || result[0].equals(result[2]));

        if (allSame) {
        	return switch (result[0]) {
            case "🍒" -> bet * 2;
            case "🍋" -> bet * 3;
            case "🍉" -> bet * 4;
            case "🍺" -> bet * 10;
            case "💎" -> bet * 50;
            default -> 0;
            };
        } else if (twoSame) {
            return bet * 2;
        }
        return 0;
    }

    private void showError(String msg) {
        ui.getResultLabel().setText(msg);
        ui.getResultLabel().setForeground(java.awt.Color.RED);
        ui.getSpinButton().setEnabled(true);
    }
}