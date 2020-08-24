import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.Command;
import duke.Parser;
import duke.DukeException;

/**
 * Represents a Duke chat bot. A <code>Duke</code> object
 * has a specific storage instance, a list of tasks, and
 * a user interface.
 */
public class Duke {
    private final Storage storage;
    private final TaskList list;
    private final Ui ui;

    private Duke(String filePath) {
        TaskList list1;
        this.storage = new Storage(filePath);
        try {
            list1 = this.storage.loadList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("Creating a new list instead...");
            // perhaps create the specified filePath and request for user confirmation?
            list1 = new TaskList();
        }
        this.list = list1;
        this.ui = new Ui();
    }

    private void run() {
        ui.printLogo();
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String query = ui.readLine();
                Command c = Parser.parse(query);
                c.execute(this.list, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }

    /**
     * Starts the Duke ChatBot with storage file at ./data/duke.txt.
     */
    public static void main(String[] args) {
        // Duke chatBot = new Duke("../data/duke.txt");
        Duke chatBot = new Duke("./data/duke.txt");
        chatBot.run();
    }
}

