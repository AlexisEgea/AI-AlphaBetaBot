import Game.ConnectFourGame;
import Game.Launcher;
import Player.Player;
import Player.HumanPlayer;
import Player.Bot.RandomBot;
import Player.Bot.MinMaxBot;
import Player.Bot.AlphaBetaBot;
import Player.Bot.AlphaBetaSortBot;

import static utils.Constant.RED;
import static utils.Constant.YELLOW;

public static void main(String[] args) {
    ConnectFourGame game = new ConnectFourGame();

    Player humanPlayer = new HumanPlayer(RED);
//    Player randomBot = new RandomBot(YELLOW, game.getGrid().getColumnSize());
//    Player minMaxBot = new MinMaxBot(YELLOW, humanPlayer, game);
//    Player alphaBetaBot = new AlphaBetaBot(YELLOW, humanPlayer, game);
    Player alphaBetaSortBot = new AlphaBetaSortBot(YELLOW, humanPlayer, game);

    Player[] players = {humanPlayer, alphaBetaSortBot};
    game.initPlayer(players);

    Launcher launcher = new Launcher(game, 2);
    launcher.startGame();
}
