package Player.Bot;

import Player.Player;

import java.util.Random;

public class RandomBot extends Player {
    private final Random random;
    private int xSizeGrid;

    public RandomBot(int playerId, int xSizeGrid) {
        super(playerId);
        this.xSizeGrid = xSizeGrid;
        this.random = new Random();

    }

    @Override
    public int decide() {
        return random.nextInt(xSizeGrid) + 1;
    }
}
