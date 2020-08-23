
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class Duke {
    public final static String FILEPATH = System.getProperty("user.dir") + "/duke.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;
    private final Ui ui;

    /**
     * Calls method run().
     * @param args expecting the array of objects.
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    /**
     * Creates a Duke.
     * Initializes the Duke bot.
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        parser = new Parser();
        tasks = new TaskList();
        storage.load();
    }

    /**
     * Runs the entire program.
     * Main driver of Duke bot.
     */
    public void run() throws IOException {
        storage.appendToFile("Hello! I'm Duke\n" + "What can I do for you?");

        while (true) {
            String fullCommand = ui.readCommand();
            String first = parser.parse(fullCommand);
            if (first.equals("bye")) {
                storage.appendToFile("Bye. Hope to see you again soon!");
                break;
            }
            tasks.operate(storage, fullCommand, first);
        }
    }
}