package MVC.Model.Algorithm;

import MVC.Model.Game.Game;
import MVC.Model.Player.Player;
import MVC.Model.Utils.Evaluation;

import java.util.ArrayList;

public class AlphaBeta implements Algorithm {
    private final Player [] players;
    private final int dept; // Represents the number of anticipated actions
    private int evaluation; // Tracks the number evaluations performed during the decision-making process

    public AlphaBeta(Player playerOne, Player playerTwo){
        this.players = new Player[2];
        this.players[0] = playerOne;
        this.players[1] = playerTwo;
        this.dept = 7;
    }

    /**
     * Determines the best possible action for the given player using the AlphaBeta algorithm.
     *
     * @param game   The game in which the action is performed
     * @param player The player making the action
     * @return the best action to play
     */
    @Override
    public int bestAction(Game game, Player player) {
        // [alpha, beta] -> [-∞,+∞]
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

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
            if (maxNode) { // Maximizing player
                score = this.alphabeta(game, this.players[1], this.dept, alpha, beta);
                if(score > bestScore) {
                    bestScore = score;
                    bestAction = action;
                }
            } else { // Minimizing player
                score = this.alphabeta(game, this.players[1], this.dept, alpha, beta);
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
     * Implements the Alpha-Beta Pruning optimization for the MinMax algorithm.
     * This function recursively explores the game tree while pruning branches
     * that do not need to be evaluated, improving performance.
     *
     * @param game   The current state of the game
     * @param player The player making the action
     * @param dept   The remaining depth to explore in the search tree
     * @param alpha  The best already guaranteed value for the maximizing player
     * @param beta   The best already guaranteed value for the minimizing player
     * @return The evaluated score of the game state
     */
    public int alphabeta(Game game, Player player, int dept, int alpha, int beta){
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
            System.out.print("AlphaBeta Dept: " + dept + " - ");
            // Simulate playing the action
            game.playAction(player, action);
            // Recursive call to evaluate the action
            score = this.alphabeta(game, opponent, dept - 1, alpha, beta);
            // Undo the action to restore the game state
            game.undoAction();
            // Update best score and alpha/beta depending on whether it's a max or min node
            if (maxNode) { // Maximizing player
                if (score > bestScore){
                    bestScore = score;
                    alpha = Math.max(alpha, bestScore);
                }
            } else { // Maximizing player
                if (score < bestScore){
                    bestScore = score;
                    beta = Math.min(beta, bestScore);
                }
            }
            // Alpha-beta pruning:
            // Cut off remaining branches as they won’t influence the final decision.
            if(alpha >= beta)
                return bestScore;
        }
        return bestScore;
    }
}

