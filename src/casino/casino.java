package casino;

import javax.swing.SwingUtilities;

/*
casino
├── Casino.java        → Main class (start)
├── CasinoUI.java          → Visual (GUI)
├── GameLogic.java         → GAME LOGIC	(spin, animation)
└── BalanceManager.java    → Balance, wins, formating
*/

public class casino {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(casinoUI::new);
    }
}