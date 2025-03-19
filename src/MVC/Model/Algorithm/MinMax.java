package MVC.Model.Algorithm;

import MVC.Model.Game.Game;
import MVC.Model.Player.Player;
import MVC.Model.Utils.Evaluation;

import java.util.ArrayList;

public class MinMax implements Algorithm {
    private final Player [] players;
    private final int dept; // Represents the number of anticipated actions
    private int evaluation; // Tracks the number evaluations performed during the decision-making process


    public MinMax(Player playerOne, Player playerTwo){
        this.players = new Player[2];
        this.players[0] = playerOne;
        this.players[1] = playerTwo;
        this.dept = 3;
    }

    /**
     * Determines the best possible action for the given player using the MinMax algorithm.
     *
     * @param game   The game in which the action is performed
     * @param player The player making the action
     * @return the best action to play
    */
    @Override
    public int bestAction(Game game, Player player) {
        int bestScore, score;
        int bestAction = 0;

        Boolean maxNode = (player == this.players[0]);
        bestScore = maxNode ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        this.evaluation = 0;

        // Retrieve all possible actions
        ArrayList<Integer> actions = game.getPossibleActions();
        for (Integer action : actions) {
            // Simulate playing the action
            game.playAction(player, action);
            // Evaluate and get the action with best score (i.e., the best action)
            if (maxNode) {  // Maximizing player
                score = this.minMax(game, this.players[1], this.dept);
                if(score > bestScore) {
                    bestScore = score;
                    bestAction = action;
                }
            } else { // Minimizing player
                score = this.minMax(game, this.players[1], this.dept);
                if (score < bestScore) {
                    bestScore = score;
                    bestAction = action;
                }
            }
            // Undo the action to restore the game state
            game.undoAction();
        }
        System.out.println("evaluation: " + evaluation);
        return bestAction;
    }

    /**
     * Recursive MinMax function to evaluate the game tree.
     *
     * @param game   The game
     * @param player The player making the action
     * @param dept   The depth in the search tree
     * @return The evaluated score of the game state
    */
    public int minMax(Game game, Player player, int dept){
        // Base case: if maximum depth is reached or game is over
        if (dept == 0 || game.endGame()) {
            evaluation++;
            return Evaluation.eval(game, this.players[0]);
        }

        int bestScore, score;
        boolean maxNode = (player == this.players[0]);
        bestScore = maxNode ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Player opponent = maxNode ? this.players[1] : this.players[0];

        // Retrieve all possible actions
        ArrayList<Integer> actions = game.getPossibleActions();
        for (Integer action : actions) {
            System.out.print("MinMax Dept: " + dept + " - ");
            // Simulate playing the action
            game.playAction(player, action);
            // Recursive call to evaluate the action
            score = this.minMax(game, opponent, dept - 1);
            // Undo the action to restore the game state
            game.undoAction();
            // Update best score depending on whether it's a max or min node
            if (maxNode) { // Maximizing player
                if (score > bestScore)
                    bestScore = score;
            } else { // Maximizing player
                if (score < bestScore)
                    bestScore = score;
            }
        }
        return bestScore;
    }
}