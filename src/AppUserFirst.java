import MVC.Model.Game.ConnectFourGame;
import MVC.Model.Player.HumanPlayer;
import MVC.Model.Player.Player;
import MVC.Model.Player.Bot.AlphaBetaSortBot;
import MVC.View.View;
import MVC.Controller.Controller;


public class AppUserFirst {
    public static void main(String[] args) {
        ConnectFourGame game = new ConnectFourGame();
        Player player1 = new HumanPlayer();
        Player player2 = new AlphaBetaSortBot(player1, game);
        // Player player2 = new RandomBot(game.getGrid().getColumnSize());
        // Player player2 = new MinMaxBot(player1, game);
        // Player player2 = new AlphaBetaBot(player1, game);

        game.initPlayer(new Player[]{player1, player2});
        Controller controller = new Controller(game);
        View view = new View(controller);
        controller.addObserver(view);
    }
}
