import Game.ConnectFour;
import Game.Launcher;
import Player.Bot.AlphaBetaBot;
import Player.Bot.AlphaBetaSortBot;
import Player.Bot.MinMaxBot;
import Player.HumanPlayer;
import Player.Player;
import Player.Bot.RandomBot;

import static utils.Constant.RED;
import static utils.Constant.YELLOW;

public static void main(String[] args) {
    ConnectFour game = new ConnectFour(7, 6);
    Player humanPlayer = new HumanPlayer(RED);
    Player randomBot = new RandomBot(YELLOW, game.getColumnSize());
    Player minMaxBot = new MinMaxBot(YELLOW, humanPlayer, game);
    Player alphaBetaBot = new AlphaBetaBot(YELLOW, humanPlayer, game);
    Player alphaBetaSortBot = new AlphaBetaSortBot(YELLOW, humanPlayer, game);

    Player[] players = {humanPlayer, alphaBetaSortBot};
    Launcher launcher = new Launcher(players, game, 1);
    launcher.startGame(true);
}
