package casino;

import java.util.Random;

public class gameLogic {
	private static final String[] EMOJIS = {"Cherry", "Lemon", "Watermelon", "Beer", "Diamond"};
    private static final Random random = new Random();

    private final casinoUI ui;

    public gameLogic(casinoUI ui) {
        this.ui = ui;
	
}
}