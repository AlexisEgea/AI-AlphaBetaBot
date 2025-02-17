package MVC.Model.Player.Bot;

import MVC.Model.Player.Player;

import java.util.Random;

public class RandomBot extends Player {
    private final Random random;
    private int columnSize;

    public RandomBot(int playerId, int columnSize) {
        super(playerId);
        this.columnSize = columnSize;
        this.random = new Random();

    }

    @Override
    public int decide() {
        return random.nextInt(columnSize) + 1;
    }
}
