package duke;

import java.io.IOException;

import duke.command.CommandParser;
import duke.data.DukeCommandSet;
import duke.data.DukeState;
import duke.data.DukeTaskList;
import duke.input.UserInput;
import duke.storage.DukeStorage;
import duke.storage.TaskStorage;
import duke.ui.Ui;

/**
 * Duke is a console program that can save tasks and modify saved tasks.
 */
public class Duke {

    protected DukeCommandSet commandSet;
    protected DukeState state;
    protected Ui ui;
    protected CommandParser commandParser;
    protected DukeTaskList taskList;
    protected TaskStorage taskStorage;
    protected DukeStorage storage;

    protected Duke() {
        commandSet = new DukeCommandSet();
        state = new DukeState();
        ui = new Ui(this);
        commandParser = new CommandParser();
        taskList = new DukeTaskList();
        try {
            taskStorage = new TaskStorage();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        storage = new DukeStorage(this);
    }

    private void run() throws IOException {
        storage.loadSavedTasks();
        ui.greet();

        while (!state.getExitLoop()) {
            String inputLine = UserInput.getOneLine();
            commandParser.parse(inputLine, this);
        }

        storage.saveCurrentTasks();
    }

    /**
     * The main function of Duke
     * @param args arguments
     * @throws IOException likely to be thrown when there are problems
     * loading or saving data
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    public DukeCommandSet getCommandSet() {
        return commandSet;
    }

    public DukeState getState() {
        return state;
    }

    public Ui getUi() {
        return ui;
    }

    public CommandParser getCommandParser() {
        return commandParser;
    }

    public DukeTaskList getTaskList() {
        return taskList;
    }

    public TaskStorage getTaskStorage() {
        return taskStorage;
    }

    public DukeStorage getStorage() {
        return storage;
    }
}
