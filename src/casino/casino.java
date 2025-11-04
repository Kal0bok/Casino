package casino;

import javax.swing.SwingUtilities;

/*
casino
├── Casino.java        → Main class (start)
├── CasinoUI.java          → Visual (GUI)
├── MainMenu.java           → Start window
├── AccountManager.java     ← Registration, login, save
├── GameLogic.java         → GAME LOGIC	(spin, animation)
├──BalanceManager.java    → Balance, wins, formating
├── LobbyUI.java            ← New lobby screen (after login)
├── AccountUI.java          ← Window profile player
├── PokerUI.java            ← Poker game
├── RouletteUI.java         ← Roulette game
├── SpinsUI.java            ← Spins window (our old slots)
├── CyberSportUI.java       ← CyberSport game
├── VirtualSportUI.java     ← ЗаглVirtualSport game
├── SportUI.java            ← Sport game
*/

public class casino {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}