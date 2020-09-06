package duke;

import duke.command.Command;
import duke.component.DukeException;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Chat bot that can manage your tasks!
 *
 * @author Tian Fang
 * @version v0.1
 * @since 2020-08-18
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Initializes Duke with the given file path
     */
    public Duke() {
        String filePath = "data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readList());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    /**
     * main method of Duke
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.giveResponse(e.getMessage());
            }
        }
    }

    /**
     * Gives response to the given input.
     * @param input the given input
     * @return      corresponding repsonse
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.giveResponse(e.getMessage());
        }
    }
}
