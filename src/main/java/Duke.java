import java.io.IOException;
import java.text.ParseException;

/**
 * A Duke object represents the chatbot which responds to users' inputs.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-08-26
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Public constructor of Duke to create a Duke object to deal with user inputs.
     * @param filePath Path where file containing past and new tasks can be loaded from and saved to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException | ParseException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        welcomeMsg();
        ui.start(tasks);
        storage.writeToFile(tasks);
    }

    /**
     * Prints a welcome message when the bot is first launched.
     */
    public void welcomeMsg() {
        String welcomeMsg = "Hello! I'm Duke, some call me a parrot.\n"
                + "What can I do for you?";
        System.out.println(welcomeMsg);
    }

    /**
     * When the bot is first launched, a new Duke object is created to process user inputs.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        try {
            new Duke("./data/duke.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
