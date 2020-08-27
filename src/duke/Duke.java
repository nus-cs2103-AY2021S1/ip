package duke;

import duke.data.DukeCommandSet;
import duke.data.DukeTaskList;
import duke.input.UserInput;
import duke.command.CommandParser;
import duke.data.DukeState;
import duke.storage.DukeStorage;
import duke.storage.TaskStorage;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    public DukeCommandSet commandSet;
    public DukeState state;
    public Ui ui;
    public CommandParser commandParser;
    public DukeTaskList taskList;
    public TaskStorage taskStorage;
    public DukeStorage storage;

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

        while (!state.exitLoop) {
            String inputLine = UserInput.getOneLine();
            commandParser.parse(inputLine, this);
        }

        storage.saveCurrentTasks();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
