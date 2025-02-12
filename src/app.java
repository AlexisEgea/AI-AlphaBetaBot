import Game.ConnectFourGame;
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
        // Player player2 = new HumanPlayer(YELLOW);
        Player player2 = new AlphaBetaSortBot(YELLOW, player1, game);
        View view = new View(game);
        Controller controller = new Controller(game, player1, player2);
        controller.add_observer(view);
        view.setController(controller);
    }
}
