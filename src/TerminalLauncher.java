import MVC.Model.Game.ConnectFourGame;
import MVC.Model.Game.Game;
import MVC.Model.Player.Bot.AlphaBetaSortBot;
import MVC.Model.Player.HumanPlayer;
import MVC.Model.Player.Player;

import static MVC.Model.Utils.Constant.RED;
import static MVC.Model.Utils.Constant.YELLOW;

public class TerminalLauncher {
    private Game game;
    private int numberParty;

    public TerminalLauncher(Game game, int numberParty){
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
        Player player1 = new HumanPlayer();
//        Player player2 = new RandomBot(game.getGrid().getColumnSize());
//        Player player2 = new MinMaxBot(player1, game);
//        Player player2 = new AlphaBetaBot(player1, game);
        Player player2 = new AlphaBetaSortBot(player1, game);

        game.initPlayer(new Player[]{player1, player2});

        TerminalLauncher launcher = new TerminalLauncher(game, 2);
        launcher.startGame();
    }
}
