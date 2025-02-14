package MVC.Controller;

import Game.Game;
import MVC.View.View;
import Player.HumanPlayer;

import java.util.ArrayList;

public class Controller {
    private Game game;

    private ArrayList<View> observers;

    public Controller(Game game) {
        this.game = game;

        this.observers = new ArrayList<>();

    }

    public Game getGame() {
        return game;
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

    public void playHumanMove(int column) {
        if(this.game.getCurrentPlayer() instanceof HumanPlayer) {
            boolean goodAction = game.playAction(this.game.getCurrentPlayer(), column);
            if (goodAction) {
                this.getGame().switchCurrentPlayer();
                this.notifyObservers();
            }
            if (this.game.endGame()){
                this.notifyEndGame();
            }
        }
    }

    public void playBotMove() {
        if(!(this.game.getCurrentPlayer() instanceof HumanPlayer)){
            int action = this.game.getCurrentPlayer().decide();
            boolean goodAction = game.playAction(this.getGame().getCurrentPlayer(), action);
            if (goodAction) {
                this.getGame().switchCurrentPlayer();
                this.notifyObservers();
            }
            if (this.game.endGame()){
                this.notifyEndGame();
            }
        }
    }

}
