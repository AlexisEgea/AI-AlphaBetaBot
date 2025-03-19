package MVC.Model.Player;

import static MVC.Model.Utils.Constant.EMPTY;

public abstract class Player {
    private int playerId;

    public Player(){
        this.playerId = EMPTY;
    }

    /**
     *  MVC.Model.Player's decision-making logic for choosing an action in the game.
     *
     * @return The chosen action (usually an integer representing a column).
     */
    public abstract int decide();

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

}
