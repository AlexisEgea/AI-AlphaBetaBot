package Player.Bot;

import Algorithm.MinMax;
import Game.Game;
import Player.Player;

public class MinMaxBot extends Player {
    private Player playerOne;
    private Player playerTwo;
    private Game game;
    private MinMax minMaxAlgorithm;

    public MinMaxBot(int playerOneId, Player playerTwo, Game game) {
        super(playerOneId);
        this.playerOne = this;
        this.playerTwo = playerTwo;
        this.game = game;
        this.minMaxAlgorithm = new MinMax(this.playerOne, this.playerTwo);
    }

    @Override
    public int decide() {
        return minMaxAlgorithm.bestAction(this.game, this.playerOne);
    }

}