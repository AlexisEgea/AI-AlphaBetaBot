package Algorithm;

import Game.Game;
import Player.Player;
import utils.Evaluation;

import java.util.ArrayList;

public class MinMax implements Algorithm {
    private final Player [] players;
    private final int dept;
    private int evaluation;


    public MinMax(Player playerOne, Player playerTwo){
        this.players = new Player[2];
        this.players[0] = playerOne;
        this.players[1] = playerTwo;
        this.dept = 7;

    }
    @Override
    public int bestAction(Game game, Player player) {
        int bestScore, score;
        int bestAction = 0;

        Boolean maxNode = (player == this.players[0]);

        ArrayList<Integer> actions = game.getPossibleMoves();

        bestScore = maxNode ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        this.evaluation = 0;

        for (Integer action : actions) {
            game.playAction(player.getPlayerId(), action);

            if (maxNode) {  // MAX
                score = this.minMax(game, this.players[1], this.dept);
                if(score > bestScore) {
                    bestScore = score;
                    bestAction = action;
                }
            } else { // MIN
                score = this.minMax(game, this.players[1], this.dept);
                if (score < bestScore) {
                    bestScore = score;
                    bestAction = action;
                }
            }

            game.undoAction();

        }
        System.out.println("evaluation: " + evaluation);
        return bestAction;

    }

    public int minMax(Game game, Player player, int dept){
        int bestScore, score;
        boolean maxNode = (player == this.players[0]);

        Player opponent;

        if (dept == 0 || game.endGame(this.players)) {
            evaluation++;
            return Evaluation.eval(game, this.players[0]);
        }

        ArrayList<Integer> actions = game.getPossibleMoves();

        opponent = maxNode ? this.players[1] : this.players[0];
        bestScore = maxNode ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Integer action : actions) {

            game.playAction(player.getPlayerId(), action);
            score = this.minMax(game, opponent, dept - 1);
            game.undoAction();

            if (maxNode) { // MAX
                if (score > bestScore)
                    bestScore = score;
            } else { // MIN
                if (score < bestScore)
                    bestScore = score;
            }

        }

        return bestScore;
    }
}