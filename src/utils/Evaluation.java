package utils;


import Game.Game;
import Player.Player;

import static utils.Constant.EMPTY;


public class Evaluation {
    public static int eval(Game game , Player player) {
        int score = 0;
        int scoreCell;

        // Nobody won
        if (game.isGridFull())
            return 0;

        for (int column = 0; column < game.getColumnSize(); column++) {
            for (int line = 0; line < game.getLineSize(); line++) {
                if (game.getCell(column, line) != EMPTY) {
                    scoreCell = Evaluation.scoreCell(game, column, line);
                    if (game.getCell(column, line) == player.getPlayerId())
                        score += scoreCell;
                    else
                        score -= scoreCell;
                }
            }
        }
        return score;
    }

    public static int scoreCell(Game game, int column, int line) {
        int[][][] dir = { { { 1, 0 }, { 1, 1 }, { 0, 1 }, { 1, -1 } }, // To the right
                { { -1, 0 }, { -1, -1 }, { 0, -1 }, { -1, 1 } } // To the left
        };

        int playerCell = game.getCell(column, line);

        int x, y, directionScore, totalScore = 0, distance = 1;

        // For the 4 directions
        for (int d = 0; d < 4; d++) {
            for (int s = 0; s < 2; s++) {
                directionScore = 1;
                distance = 1;

                x = column + dir[s][d][0];
                y = line + dir[s][d][1];
                while (x < game.getColumnSize() && x >= 0 && y < game.getLineSize() && y >= 0 && distance <= 3) {
                    if (game.getCell(x, y) != EMPTY && game.getCell(x, y) != playerCell) {
                        directionScore = 0;
                        break;
                    }
                    if (game.getCell(x, y) != EMPTY && game.getCell(x, y) == playerCell)
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