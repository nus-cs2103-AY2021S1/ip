package duke;

import duke.command.CommandParser;
import duke.data.DukeCommandSet;
import duke.data.DukeState;
import duke.data.DukeTaskListStub;
import duke.storage.DukeStorage;
import duke.storage.TaskStorageStub;
import duke.ui.UiStub;

import java.io.IOException;

public class DukeStub extends Duke {

    public DukeStub() {
        commandSet = new DukeCommandSet();
        state = new DukeState();
        ui = new UiStub(this);
        commandParser = new CommandParser();
        taskList = new DukeTaskListStub();
        try {
            taskStorage = new TaskStorageStub();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        storage = new DukeStorage(this);
    }
}
