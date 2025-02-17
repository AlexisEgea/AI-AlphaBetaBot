package Player.Bot;

import MVC.Model.Algorithm.AlphaBeta;
import Game.Game;
import Player.Player;

public class AlphaBetaBot extends Player {
    private Player playerOne;
    private Player playerTwo;
    private Game game;
    private AlphaBeta alphaBetaAlgorithm;

    public AlphaBetaBot(int playerOneId, Player playerTwo, Game game) {
        super(playerOneId);
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