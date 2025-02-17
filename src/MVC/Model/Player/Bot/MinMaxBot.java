package MVC.Model.Player.Bot;

import MVC.Model.Algorithm.MinMax;
import MVC.Model.Game.Game;
import MVC.Model.Player.Player;

public class MinMaxBot extends Player {
    private Player playerOne;
    private Player playerTwo;
    private Game game;
    private MinMax minMaxAlgorithm;

    public MinMaxBot(Player playerTwo, Game game) {
        super();
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