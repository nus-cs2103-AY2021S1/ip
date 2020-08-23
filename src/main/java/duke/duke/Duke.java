package duke.duke;

import java.io.File;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;
import duke.commands.Command;
import duke.parser.Parser;

/**
 * Represents Duke object. Is responsible for the overall running of Duke.
 *
 * @author Kishen Ashok Kumar
 */
public class Duke {

    private Storage store;
    private TaskList tasks;
    private UI ui;

    /**
     * Creates a Duke object with the respective directory and file paths
     * for loading and saving of task list.
     *
     * @param directoryPath directory path containing text file to load.
     * @param filePath name of text file to load.
     */
    public Duke(String directoryPath, String filePath) {
        ui = new UI();
        store = new Storage(directoryPath, filePath);
        File loadFile = store.loadData(ui);
        if (loadFile != null) {
            tasks = new TaskList(loadFile);
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the instance of Duke.
     * Is responsible for the coordination of all Duke functions.
     */
    public void run() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, store);
            isExit = command.isExit();
            if (!isExit) {
                ui.displayBlankLine();
            }
        }
    }

    /**
     * Creates an instance of a Duke object and runs it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data", "duke.txt");
        duke.run();
    }

}
