package MVC.Model.Player.Bot;

import MVC.Model.Algorithm.AlphaBeta;
import MVC.Model.Game.Game;
import MVC.Model.Player.Player;

public class AlphaBetaBot extends Player {
    private Player playerOne;
    private Player playerTwo;
    private Game game;
    private AlphaBeta alphaBetaAlgorithm;

    public AlphaBetaBot(Player playerTwo, Game game) {
        super();
        this.playerOne = this;
        this.playerTwo = playerTwo;
        this.game = game;
        this.alphaBetaAlgorithm = new AlphaBeta(this.playerOne, this.playerTwo);
    }

    @Override
    public int decide() {
        return alphaBetaAlgorithm.bestAction(this.game, this.playerOne);
    }

}