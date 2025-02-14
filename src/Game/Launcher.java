package Game;

import Player.Player;

public class Launcher {
    private Game game;
    private int numberParty;

    public Launcher(Game game, int numberParty){
        this.game = game;
        this.numberParty = numberParty;
    }

    public void startGame(Boolean debug){
        int party = 0;
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
}
