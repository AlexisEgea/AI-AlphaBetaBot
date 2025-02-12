package Algorithm;

import Game.Game;
import Player.Player;
import utils.Evaluation;

import java.util.ArrayList;

public class AlphaBetaSort implements Algorithm {
    private final Player [] players;
    private final int dept;
    private int evaluation;


    public AlphaBetaSort(Player playerOne, Player playerTwo){
        this.players = new Player[2];
        this.players[0] = playerOne;
        this.players[1] = playerTwo;
        this.dept = 10;

    }
    @Override
    public int bestAction(Game game, Player player) {
        // [alpha, beta] -> [-∞,+∞]
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        int bestScore, score;
        int bestAction = 0;

        Boolean maxNode = (player == this.players[0]);

        ArrayList<Integer> actions = getSortPossibleMove(game);

        bestScore = maxNode ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        this.evaluation = 0;

        for (Integer action : actions) {
            game.playAction(player, action);

            if (maxNode) {  // MAX
                score = this.alphaBetaSort(game, this.players[1], this.dept, alpha, beta);
                if(score > bestScore) {
                    bestScore = score;
                    bestAction = action;
                    alpha = Math.max(alpha, bestScore);
                }
            } else { // MIN
                score = this.alphaBetaSort(game, this.players[1], this.dept, alpha, beta);
                if (score < bestScore) {
                    bestScore = score;
                    bestAction = action;
                    beta = Math.min(beta, bestScore);
                }
            }

            game.undoAction();

            if(alpha >= beta){
                System.out.println("evaluation: " + evaluation);
                return bestAction;
            }

        }
        System.out.println("evaluation: " + evaluation);
        return bestAction;

    }

    public int alphaBetaSort(Game game, Player player, int dept, int alpha, int beta){
        int bestScore, score;
        boolean maxNode = (player == this.players[0]);

        Player opponent;

        if (dept == 0 || game.endGame(this.players)) {
            evaluation++;
            return Evaluation.eval(game, this.players[0]);
        }

        ArrayList<Integer> actions = getSortPossibleMove(game);

        opponent = maxNode ? this.players[1] : this.players[0];
        bestScore = maxNode ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Integer action : actions) {

            game.playAction(player, action);
            score = this.alphaBetaSort(game, opponent, dept - 1, alpha, beta);
            game.undoAction();

            if (maxNode) { // MAX
                if (score > bestScore){
                    bestScore = score;
                    alpha = Math.max(alpha, bestScore);
                }


            } else { // MIN
                if (score < bestScore){
                    bestScore = score;
                    beta = Math.min(beta, bestScore);
                }

            }

            if(alpha >= beta)
                return bestScore;

        }

        return bestScore;
    }

    public ArrayList<Integer> getSortPossibleMove(Game game){
        ArrayList<Integer> sortActions = new ArrayList<>();
        ArrayList<Integer> actions = game.getPossibleMoves();

        int size = actions.size();
        int mid = size / 2;

        sortActions.add(actions.get(mid));

        int left = mid - 1;
        int right = mid + 1;

        while (left >= 0 || right < size) {
            if (right < size) {
                sortActions.add(actions.get(right));
                right++;
            }
            if (left >= 0) {
                sortActions.add(actions.get(left));
                left--;
            }
        }

        return sortActions;
    }
}

