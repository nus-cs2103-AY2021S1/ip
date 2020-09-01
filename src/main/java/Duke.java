import duke.task.TaskList;
import duke.tool.Command;
import duke.tool.Parser;
import duke.tool.Storage;
import duke.tool.Ui;

/**
 * Represents Duke, a task scheduling bot.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Command command;

    /**
     * Constructs a new Duke object.
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
     * Executes the bot.
     */
    public void run() {
        ui.printWelcomeMessage();
        parser.parse(storage, ui, taskList, command);
        ui.sendBye();
    }

    //run bot
    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}
