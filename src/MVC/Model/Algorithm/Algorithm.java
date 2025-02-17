package Algorithm;

import Game.Game;
import Player.Player;

public interface Algorithm {
    /**
     * This method aims to use an algorithm to determine the best action to win the game.
     *
     * @param game   The game in which the action is performed
     * @param player The player making the action
     * @return The best action to play
     */
    int bestAction(Game game, Player player);
}
