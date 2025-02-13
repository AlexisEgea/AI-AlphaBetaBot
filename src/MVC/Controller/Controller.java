package MVC.Controller;

import Game.Game;
import MVC.View.View;
import Player.Player;
import Player.HumanPlayer;

import java.util.ArrayList;

public class Controller {
    private Game game;
//    private View view;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private ArrayList<View> observers;

    public Controller(Game game, Player player1, Player player2) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;

        this.observers = new ArrayList<>();

    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void addObserver(View observer){
        this.observers.add(observer);
    }

    public void removeObserver(View observer){
        this.observers.remove(observer);
    }

    public void notifyObservers(){
        for (View observer : this.observers) {
            observer.update();
        }
    }

    public void notifyEndGame(){
        for (View observer : this.observers) {
            observer.updateEndGame();
        }
    }

    public void playAction(int column) {
        this.playHumanMove(column);
        if (this.game.endGame(new Player[]{this.player1, this.player2}))
            this.notifyEndGame();
        else{
            this.playBotMove();
            System.out.println("DEBUG: Yellow player has played.");
            if (this.game.endGame(new Player[]{this.player1, this.player2}))
                this.notifyEndGame();
        }
    }

        public void playHumanMove(int column) {
        if(currentPlayer instanceof HumanPlayer){
            boolean goodAction = game.playAction(currentPlayer, column);
            if (goodAction){
                this.switchPlayer();
                this.notifyObservers();

            }
        }

    }

    public void playBotMove() {
        if(!(currentPlayer instanceof HumanPlayer)){
            int action = currentPlayer.decide();
            boolean goodAction = game.playAction(currentPlayer, action);
            if (goodAction) {
                this.switchPlayer();
                this.notifyObservers();
            }
        }
        if (this.game.endGame(new Player[]{this.player1, this.player2}))
            this.notifyEndGame();
    }

    private void switchPlayer() {
        int currentPlayerId = (currentPlayer.getPlayerId() + 1) % 2;
        if(currentPlayerId == player1.getPlayerId())
            currentPlayer = player1;
        else
            currentPlayer = player2;

    }

}
