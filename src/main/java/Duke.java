/**
 * Entry point of The program
 */
import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList<Task> tasks;


    public static void main(String[] args) {
        /**
         * Instantiates a new chatBot for the user to interact with
         */
        Storage storage = new Storage();
        storage.loadFile();
        ChatBot chatBot = new ChatBot("Kai");
        chatBot.welcome();
        chatBot.getInput();

    }
}
