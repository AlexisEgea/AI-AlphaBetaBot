package utils;

import Game.Game;
import Player.Player;

import static utils.Constant.EMPTY;

public class Evaluation {
    /**
     * Evaluates the current game state and returns a score representing the position
     * of the player in the game. The score is calculated based on the player's pieces
     * on the board and their potential to win.
     *
     * The evaluation is done by summing the score of all cells owned by the player
     * and subtracting the score of cells owned by the opponent. The higher the score,
     * the more favorable the game state is for the player.
     *
     * The scoring considers factors such as the number of connected pieces in various
     * directions (horizontal, vertical, diagonals) and gives higher scores for positions
     * that contribute to potential victories.
     *
     * @param game   The current game state, including the grid and players.
     * @param player The player whose perspective the evaluation is done from.
     * @return The evaluated score, where a positive value indicates a favorable
     *         position for the player, and a negative value indicates a disadvantageous position.
     */

    public static int eval(Game game, Player player) {
        int score = 0;
        int scoreCell;

        // Nobody won
        if (game.drawGame())
            return 0;

        for (int column = 0; column < game.getGrid().getColumnSize(); column++) {
            for (int line = 0; line < game.getGrid().getLineSize(); line++) {
                if (game.getGrid().getCell(column, line) != EMPTY) {
                    scoreCell = Evaluation.scoreCell(game, column, line);
                    if (game.getGrid().getCell(column, line) == player.getPlayerId())
                        score += scoreCell;
                    else
                        score -= scoreCell;
                }
            }
        }
        return score;
    }

    /**
     * Compute the score for a specific cell on the grid based on the number of connected pieces in the four directions
     * (vertical, horizontal, and diagonals).
     *
     * @param game   The current game state.
     * @param column The column index of the cell.
     * @param line   The line index of the cell.
     * @return The score for the cell based on its contribution to potential winning positions.
     */
    public static int scoreCell(Game game, int column, int line) {
        int[][][] dir = { { { 1, 0 }, { 1, 1 }, { 0, 1 }, { 1, -1 } }, // to the right
                { { -1, 0 }, { -1, -1 }, { 0, -1 }, { -1, 1 } } // to the left
        };
        int playerCell = game.getGrid().getCell(column, line);
        int x, y, directionScore, totalScore = 0, distance = 1;

        // For the four directions
        for (int d = 0; d < 4; d++) {
            for (int s = 0; s < 2; s++) {
                directionScore = 1;
                distance = 1;

                x = column + dir[s][d][0];
                y = line + dir[s][d][1];
                while (x < game.getGrid().getColumnSize() && x >= 0 && y < game.getGrid().getLineSize() && y >= 0 && distance <= 3) {
                    if (game.getGrid().getCell(x, y) != EMPTY && game.getGrid().getCell(x, y) != playerCell) {
                        directionScore = 0;
                        break;
                    }
                    if (game.getGrid().getCell(x, y) != EMPTY && game.getGrid().getCell(x, y) == playerCell)
                        directionScore++;
                    distance++;
                    x += dir[s][d][0];
                    y += dir[s][d][1];
                }
                if (directionScore == 4)
                    totalScore += 10000;
                else if (distance > 3)
                    totalScore += directionScore;
            }
        }
        return totalScore;
    }
}