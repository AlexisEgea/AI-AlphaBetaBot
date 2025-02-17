package MVC.Controller;

import Game.Game;
import MVC.View.View;
import Player.HumanPlayer;

import java.util.ArrayList;

/**
 * The Controller class manages the interaction between the Game logic and the Views (observers).
 * It handles user inputs (actions), updates the views, and triggers game state changes.
 * It follows the Model-View-Controller (MVC) design pattern to separate concerns.
 */
public class Controller {
    private final Game game;

    private final ArrayList<View> observers;

    public Controller(Game game) {
        this.game = game;

        this.observers = new ArrayList<>();

    }

    /**
     * Gets the game instance being managed by this controller.
     *
     * @return The game instance.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Adds a view observer to the list of observers.
     *
     * @param observer The view to add as an observer.
     */
    public void addObserver(View observer){
        this.observers.add(observer);
    }

    /**
     * Reactions a view observer from the list of observers.
     *
     * @param observer The view to remove as an observer.
     */
    public void removeObserver(View observer){
        this.observers.remove(observer);
    }

    /**
     * Notifies all registered observers to update their views.
     */
    public void notifyObservers(){
        for (View observer : this.observers) {
            observer.update();
        }
    }

    /**
     * Notifies all registered observers to update their views with the final (end) result of the game.
     * */
    public void notifyEndGame(){
        for (View observer : this.observers) {
            observer.updateEndGame();
        }
    }

    /**
     * Executes a human player's action by processing their input (column choice).
     *
     * @param column The column where the player wants to drop their piece.
     */
    public void playHumanAction(int column) {
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

    /**
     * Executes a bot player's action automatically.
     */
    public void playBotAction() {
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
