import duke.Command;
import duke.Storage;
import duke.Ui;
import duke.resource.Parser;
import duke.resource.TaskList;
import duke.util.DukeException;

/**
 * Duke is a bot that functions as a user's task manager.
 */

public class Duke {

    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructor that creates a Duke object.
     * @param filePath the filepath task sessions will be saved in
     */

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ui.welcome();
        try {
            tasks = TaskList.parse(this.storage.load());
            ui.loaded(tasks);
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    /**
     * Runs Duke's user input scanning that only terminates when a "bye" command is given.
     */

    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.read();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.shouldExit();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }

    /**
     * Creates a new Duke object and runs it, starting the entire chat-bot.
     * @param args input arguments for main; unused
     */

    public static void main(String[] args) {
        // change the filePath below to save elsewhere
        new Duke("./src/main/data/duke.txt").run();
    }
}
