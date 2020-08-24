import java.util.ArrayList;
import java.util.List;

public class PlayBot {
    public String player;  //keep track of the player
    public List<GameRecord> gameLog;    //record the result of every round of game
    public PlayBot() {
        gameLog = new ArrayList<>();
    }

    public void setName(String name) {
        player = name;
    }

    public void greeting() {
        String greeting = "Welcome to paper-scissor-stone!\nPlease enter your name:";
        System.out.print(greeting);
    }

    public void playPaper() {
        String paper = "---------------------\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "|                    |\n"
                +      "---------------------\n";
        System.out.print(paper);
    }

    public void playScissor() {
        String scissor = "\\                   /\n"
                +        " \\                 /\n"
                +        "  \\               /\n"
                +        "   \\             /\n"
                +        "    \\           /\n"
                +        "     \\         /\n"
                +        "      \\       /\n"
                +        "       \\     /\n"
                +        "        \\   /\n"
                +        "         \\ /\n"
                +        "          /\\\n"
                +        "         /  \\\n"
                +        "        /    \\\n"
                +        "       /      \\\n";
        System.out.print(scissor);
    }

    public void playStone() {
        String stone = "/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|       I am a stone             |\n"
                +      "|________________________________|\n";
        System.out.print(stone);
    }

    public void win(String playerMove, String botMove) {
        gameLog.add(new GameRecord(player, "won", playerMove, botMove));
    }

    public void lose(String playerMove, String botMove) {
        gameLog.add(new GameRecord(player, "lost", playerMove, botMove));
    }

    public void draw1(String playerMove) {
        gameLog.add(new GameRecord(player, "come to a draw", playerMove, playerMove));
    }

    public void respond(String playerMove) {
        if (playerMove.equals("record")) {
            seeRecord();
        } else {
            double x = Math.random();
            if (x < 0.3333) {
                win(playerMove);
            } else if (x < 0.6666) {
                draw(playerMove);
            } else {
                lose(playerMove);
            }
        }
    }

    private void win(String playerMove) {
        if (playerMove.equals("paper")) {
            playScissor();
            System.out.print("Bot: scissor\nYou have lost.\n");
            lose("paper", "scissor");
        } else if (playerMove.equals("scissor")) {
            playStone();
            System.out.print("Bot: stone\nYou have lost.\n");
            lose("scissor", "stone");
        } else {
            playPaper();
            System.out.print("Bot: paper\nYou have lost.\n");
            lose("stone", "paper");
        }
    }

    private void lose(String playerMove) {
        if (playerMove.equals("paper")) {
            playStone();
            System.out.print("Bot: stone\nYou have won.\n");
            win("paper", "stone");
        } else if (playerMove.equals("scissor")) {
            playPaper();
            System.out.print("Bot: paper\nYou have won.\n");
            win("scissor", "paper");
        } else {
            playScissor();
            System.out.print("Bot: scissor\nYou have won.\n");
            win("stone", "scissor");
        }
    }

    private void draw(String playerMove) {
        if (playerMove.equals("paper")) {
            playPaper();
        } else if (playerMove.equals("scissor")) {
            playScissor();
        } else {
            playStone();
        }
        System.out.print("Bot: " + playerMove + "\nDraw.\n");
        draw1(playerMove);
    }

    public void intro() {
        String intro = "Enter 'paper' or 'scissor' or 'stone' to make your move.\nIf you want to see the game record, "
                + "enter 'record'\n";
        System.out.print(intro);
    }

    public void seeRecord() {
        String msg = "";
        if (gameLog.size() > 0) {
            for (GameRecord record : gameLog) {
                msg += record.toString();
            }

            System.out.print(msg);
        } else {
            System.out.print("You have no game record yet.\n");
        }
    }
}
