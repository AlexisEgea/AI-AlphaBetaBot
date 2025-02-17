import MVC.Model.Game.ConnectFourGame;
import MVC.Model.Game.Game;
import MVC.Model.Player.Bot.AlphaBetaSortBot;
import MVC.Model.Player.HumanPlayer;
import MVC.Model.Player.Player;

import static MVC.Model.Utils.Constant.RED;
import static MVC.Model.Utils.Constant.YELLOW;

public class Launcher {
    private Game game;
    private int numberParty;

    public Launcher(Game game, int numberParty){
        this.game = game;
        this.numberParty = numberParty;
    }

    /**
     * Starts and runs the game for the specified number of parties.
     * For each game, players take turns playing until the game ends.
     * The game is reset after each party.
     */
    public void startGame(){
        int party;
        for(int i=0; i<this.numberParty; i++){
            party = i+1;
            System.out.println("Game " + party + " ____________________");
            System.out.println(this.game.getGrid());
            System.out.println();

            while(!this.game.endGame()){
                System.out.println("Player " + this.game.getCurrentPlayer().getPlayerId());

                Boolean goodAction = false;
                while(!goodAction){
                    int action = this.game.getCurrentPlayer().decide();
                    goodAction = this.game.playAction(this.game.getCurrentPlayer(), action);
                }
                this.game.switchCurrentPlayer();
            }
            this.game.resetGame();
        }
    }

    public static void main(String[] args) {
        ConnectFourGame game = new ConnectFourGame();

        Player humanPlayer = new HumanPlayer(RED);
//    MVC.Model.Player randomBot = new RandomBot(YELLOW, game.getGrid().getColumnSize());
//    MVC.Model.Player minMaxBot = new MinMaxBot(YELLOW, humanPlayer, game);
//    MVC.Model.Player alphaBetaBot = new AlphaBetaBot(YELLOW, humanPlayer, game);
        Player alphaBetaSortBot = new AlphaBetaSortBot(YELLOW, humanPlayer, game);

        Player[] players = {humanPlayer, alphaBetaSortBot};
        game.initPlayer(players);

        Launcher launcher = new Launcher(game, 2);
        launcher.startGame();
    }
}
