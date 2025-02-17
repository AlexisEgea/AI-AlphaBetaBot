package Player;

public abstract class Player {
    private int playerId;

    public Player(int playerId){
        this.playerId = playerId;
    }

    /**
     *  Player's decision-making logic for choosing an action in the game.
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
