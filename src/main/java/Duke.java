import Duke.Tool.*;
import Duke.Task.*;

/**
 * Represent Duke, a task scheduling bot.
 */
public class Duke {
    
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Command command;

    /**
     * Construct a new Duke object.
     * @param filePath path of file storing the data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        command = new Command();
        taskList = new TaskList(storage.load());
    }

    /**
     * Execute the bot.
     */
    public void run() {
        this.ui.printWelcomeMessage();
        parser.parse(storage, ui, taskList, command);
        this.ui.sendBye();
    }

    //run bot
    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}
