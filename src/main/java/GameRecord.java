public class GameRecord {
    String player;
    String isPlayerWon;
    String playerMove;
    String botMove;

    public GameRecord(String player, String isPlayerWon, String playerMove, String botMove) {
        this.player = player;
        this.isPlayerWon = isPlayerWon;
        this.playerMove = playerMove;
        this.botMove = botMove;
    }

    @Override
    public String toString() {
        return "Player " + player + " has " + isPlayerWon + "\n     Player: " + playerMove + " | Bot: " + botMove + "\n";
    }
}
