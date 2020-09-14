package duke;

import duke.ui.Ui;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.command.Command;
import duke.parser.Parser;

/**
 * Class contains main method of the Duke application.
 * Duke manages, stores and track tasks as specified by the user.
 */
public class Duke {
    private static String TASKS_PATHNAME = "data/tasks.txt";

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor with no param.
     * The filepath for storage is set as the static variable, TASKS_PATHNAME.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(TASKS_PATHNAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * main method of Duke
     * Uses the Ui class as the medium for interaction with user.
     * Ui class uses command line to interact with user. (System.in and System.out).
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.showLine();
        System.out.println(ui.welcome());
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                ui.showOutput(c.execute(taskList, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String welcome() {
        return ui.welcome();
    }

    /**
     * Returns the response by the duke program as a string.
     * This method is used by Gui to get response from duke program.
     * @param input
     * @return
     */
    public String getResponse(String input) {
        try{
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
