import Game.ConnectFourGame;
import Player.Bot.AlphaBetaBot;
import Player.Bot.MinMaxBot;
import Player.HumanPlayer;
import Player.Player;
import Player.Bot.AlphaBetaSortBot;
import MVC.View.View;
import MVC.Controller.Controller;
import static utils.Constant.RED;
import static utils.Constant.YELLOW;

public class app {
    public static void main(String[] args) {
        ConnectFourGame game = new ConnectFourGame();
        Player player1 = new HumanPlayer(RED);
//        Player player2 = new HumanPlayer(YELLOW);
//        Player player2 = new MinMaxBot(YELLOW, player1, game);
//        Player player2 = new AlphaBetaBot(YELLOW, player1, game);
        Player player2 = new AlphaBetaSortBot(YELLOW, player1, game);
        game.initPlayer(new Player[]{player1, player2});
        Controller controller = new Controller(game);
        View view = new View(controller);
        controller.addObserver(view);
    }
}
