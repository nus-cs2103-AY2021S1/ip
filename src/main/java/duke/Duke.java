package duke;

import java.util.LinkedList;

import duke.command.Command;
import duke.command.ReversibleCommand;
import duke.component.DukeException;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Chat bot that can manage your tasks!
 *
 * @author Tian Fang
 * @version v0.2
 * @since 2020-08-18
 */
public class Duke {
    private static final String FILE_PATH = "data/tasks.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private LinkedList<ReversibleCommand> reversibleCommands;
    private boolean isActive = true;
    /**
     * Initializes Duke with the given file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        reversibleCommands = new LinkedList<>();
        try {
            taskList = new TaskList(storage.readList());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    /**
     * main method of Duke.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage, reversibleCommands);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.giveResponse(e.getMessage());
            }
        }
    }

    /**
     * Gives response to the given input.
     * @param input the given input.
     * @return      corresponding response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isActive = !c.isExit();
            return c.execute(taskList, ui, storage, reversibleCommands);
        } catch (DukeException e) {
            return ui.giveResponse(e.getMessage());
        }
    }

    public boolean isActive() {
        return isActive;
    }
}
