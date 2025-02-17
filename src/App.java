import MVC.Model.Game.ConnectFourGame;
import MVC.Model.Player.HumanPlayer;
import MVC.Model.Player.Player;
import MVC.Model.Player.Bot.AlphaBetaSortBot;
import MVC.View.View;
import MVC.Controller.Controller;
import static MVC.Model.utils.Constant.RED;
import static MVC.Model.utils.Constant.YELLOW;

public class app {
    public static void main(String[] args) {
        ConnectFourGame game = new ConnectFourGame();
        Player player1 = new HumanPlayer(RED);
//        MVC.Model.Player player2 = new HumanPlayer(YELLOW);
//        MVC.Model.Player player2 = new MinMaxBot(YELLOW, player1, game);
//        MVC.Model.Player player2 = new AlphaBetaBot(YELLOW, player1, game);
        Player player2 = new AlphaBetaSortBot(YELLOW, player1, game);
        game.initPlayer(new Player[]{player1, player2});
        Controller controller = new Controller(game);
        View view = new View(controller);
        controller.addObserver(view);
    }
}
