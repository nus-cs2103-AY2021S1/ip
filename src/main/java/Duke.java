import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.util.Scanner;

//TODO: Stretch Goals: Level 8- Use date related command

/**
 * Driver of {@code Duke} programme.
 */
public class Duke {

    private static final String DATA_FILE = "data/duke.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises the Duke programme and load tasks from default file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_FILE);
        try {
            taskList = new TaskList(storage.load());
            ui.showLoadSuccess(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Initialises the Duke programme and load tasks from data file.
     *
     * @param filePath Path of data file to be loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            ui.showLoadSuccess(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Executes the {@code Duke} programme.
     *
     * @deprecated For GUI, use {@link #run(String)} instead.
     */
    @Deprecated
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!Command.isTerminated) {
            try {
                String input = scanner.nextLine();
                Command c = Parser.parse(input);
                c.execute(taskList, ui);
            } catch (DukeException de) {
                ui.printError(de);
            }
        }
    }

    /**
     * Executes the {@code Duke} programe using the input provided
     *
     * @param input Command to run.
     */
    public void run(String input) {
        if (Command.isTerminated)
            return;
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui);

            if (c instanceof ExitCommand) {
                terminate();
            }

        } catch (DukeException de) {
            ui.printError(de);
        }
    }

    /**
     * Performs saving and clean up on programme termination.
     */
    public void terminate() {
        storage.saveToFile(taskList.export());
        ui.showExit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke(DATA_FILE);
        duke.run();
        duke.terminate();
    }

    /**
     * Retrieves the output buffer to display in UI.
     *
     * @return output string.
     */
    public String getUiUpdate() {
        return ui.flush();
    }
}
