import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represent the main class to run the Duke program.
 */
public class Duke  {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.tasks = new TaskList(storage.load());
    }
    
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("duke.txt");
        this.parser = new Parser();
        this.tasks = new TaskList(storage.load());
    }
    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return parser.parse(input, tasks, storage);
    }
}
