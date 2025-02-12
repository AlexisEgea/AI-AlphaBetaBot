package game;

import player.Player;

public class Launcher {
    private Game game;
    private Player[] players;
    private int numberParty;

    public Launcher(Player [] players, Game game, int numberParty){
        this.players = players;
        this.game = game;
        this.numberParty = numberParty;
        this.initPlayerColor();
    }

    private void initPlayerColor(){
        int color = 1;
        for(int i=0; i<players.length; i++){
            players[i].setPlayerId(color);
            color++;
        }
    }

    public void startGame(Boolean debug){
        for(int i=0; i<numberParty; i++){
            i++;
            System.out.println("Game " + i + " ____________________");
            System.out.println(this.game);
            System.out.println();

            int currentPlayerIndex = 0;
            while(!this.game.endGame(players)){

                System.out.println("Player " + this.players[currentPlayerIndex].getPlayerId());

                Boolean goodAction = false;
                while(!goodAction){
                    int action = players[currentPlayerIndex].decide();
                    goodAction = this.game.playAction(this.players[currentPlayerIndex].getPlayerId(), action);
                }
                System.out.println(this.game);
                currentPlayerIndex = (currentPlayerIndex + 1) % this.players.length;

            }
        }
    }
}
