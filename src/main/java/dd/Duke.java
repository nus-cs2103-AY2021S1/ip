package dd;

import dd.commands.Command;
import dd.exception.DukeException;
import dd.parser.Parser;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

import java.io.IOException;

/**
 * The main Duke class to create and manage a task list.
 */
public class Duke {

    private DataStorage ds;
    private Ui ui;
    private TaskList tasks;

    /**
     * Class Constructor.
     */
    public Duke() {
        this.ds = new DataStorage();
        this.ui = new Ui();

        try {
            tasks = new TaskList(ds.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Takes in user input, parses into a command and executes
     * the next command till an exit command is given.
     */
    private void run() {
        ui.greeting();

        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);

                c.execute(tasks, ui, ds);
                isExit = c.isExit();
            } catch (DukeException e){
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Starts a task tracking system with prior saved data, if applicable.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
