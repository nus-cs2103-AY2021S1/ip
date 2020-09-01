package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.io.File;

/**
 * The Duke program implements a chatbot application that would take in the input from
 * the user as a todo list.
 */
public class Duke {

    /**
     * Parser that makes sense of the user's inputs
     */
    public static Parser parser;


    /**
     * The main method that runs the chatbot application.
     */
    public Duke() {
        File f = new File("D:\\24092014\\Joven\\UNI STUFF\\CS2103\\IP\\task.txt");
        Storage storage = new Storage(f);
        TaskList shelf = new TaskList(storage.loadFile());
        UI ui = new UI(shelf, storage);
        parser = new Parser(ui);
    }

    public String welcome() {
        return parser.welcome();
    }

    public static String getResponse(String input) {
        return parser.listen(input);
    }
}
