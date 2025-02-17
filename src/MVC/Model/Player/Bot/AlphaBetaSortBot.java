package MVC.Model.Player.Bot;

import MVC.Model.Algorithm.AlphaBetaSort;
import MVC.Model.Game.Game;
import MVC.Model.Player.Player;

public class AlphaBetaSortBot extends Player {
    private Player playerOne;
    private Player playerTwo;
    private Game game;
    private AlphaBetaSort alphaBetaSortAlgorithm;

    public AlphaBetaSortBot(int playerOneId, Player playerTwo, Game game) {
        super(playerOneId);
        this.playerOne = this;
        this.playerTwo = playerTwo;
        this.game = game;
        this.alphaBetaSortAlgorithm = new AlphaBetaSort(this.playerOne, this.playerTwo);
    }

    @Override
    public int decide() {
        return alphaBetaSortAlgorithm.bestAction(this.game, this.playerOne);
    }

}