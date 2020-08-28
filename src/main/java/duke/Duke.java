package duke;

import java.util.ArrayList;
import java.util.List;

import duke.fxcommand.Command;
import duke.exception.DukeException;
import duke.utils.DukeState;

/**
 * The Duke object initializes the core classes: Ui, Storage, Parser and TaskList, and contains the main logic
 * that integrates them together to run the Duke application.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private DukeState dukeState;

    /**
     * Initializes a Duke object.
     *
     * @param filePath the filePath where the storage will load from and save data to.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList tmpTaskList;
        try {
            tmpTaskList = new TaskList(storage.load());
            ui.printWithWrapper(new ArrayList<>(List.of("Duke has loaded from a previously saved file!")),
                    false, false);
        } catch (DukeException e) {
            tmpTaskList = new TaskList();
            ui.printWithWrapper(new ArrayList<>(List.of(
                    e.getPrettyErrorMsg(),
                    "Duke will start from a clean taskList!")), false, true);
        }

        this.taskList = tmpTaskList;
        this.dukeState = DukeState.RUNNING;
    }

    /**
     * The main processing method of Duke. It waits for user input, parses, then executes the desired
     * command.
     */
    private void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                Command cmd = Parser.parseInput(input);
                cmd.execute(ui, storage, taskList);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.printWithWrapper(new ArrayList<>(List.of(e.getPrettyErrorMsg())), false, true);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parseInput(input);
            if (cmd.isExit()) {
                dukeState = DukeState.EXITED;
            }
            return cmd.execute(ui, storage, taskList);
        } catch (DukeException e) {
            return e.getPrettyErrorMsg();
        }
    }

    public DukeState getDukeState() {
        return dukeState;
    }

    /*
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
     */
}
