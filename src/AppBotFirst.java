import MVC.Controller.Controller;
import MVC.Model.Game.ConnectFourGame;
import MVC.Model.Player.Bot.AlphaBetaSortBot;
import MVC.Model.Player.HumanPlayer;
import MVC.Model.Player.Player;
import MVC.View.View;


public class AppBotFirst {
    public static void main(String[] args) {
        ConnectFourGame game = new ConnectFourGame();
        Player player1 = new HumanPlayer();
        Player player2 = new AlphaBetaSortBot(player1, game);
        // Player player2 = new RandomBot(game.getGrid().getColumnSize());
        // Player player2 = new MinMaxBot(player1, game);
        // Player player2 = new AlphaBetaBot(player1, game);

        game.initPlayer(new Player[]{player2, player1});
        Controller controller = new Controller(game);
        View view = new View(controller);
        controller.addObserver(view);
    }
}
