import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        Tracker tracker = new Tracker("./data/duke.txt");
        Bot bot = new Bot(tracker);
        bot.start();
    }
}
