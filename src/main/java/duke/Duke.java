package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Personal Assistant that keeps track of a user's list of tasks.
 */
public class Duke {
    private static final Path storageFilePath = Paths.get(".", "data", "test.txt");
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor to create a Duke object.
     * <p>
     * Duke consists of a <code>Ui</code>, <code>TaskList</code>, and <code>Storage</code> which
     * is responsible for user interactions, keeping track of user's tasks, and writing tasks
     * to the user's local storage respectively.
     */
    Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.storageFilePath);
        try {
            this.taskList = new TaskList(this.storage.getAllTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Begins running Duke, and waits for user input to interact with Duke.
     *
     * @param args user input from the command line.
     */
    public static void main(String[] args){
        new Duke().run();
    }

    private void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = this.ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(userInput);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
