package Player;

public abstract class Player {
    private int playerId;

    public Player(int playerId){
        this.playerId = playerId;
    }

    public abstract int decide();

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

}
