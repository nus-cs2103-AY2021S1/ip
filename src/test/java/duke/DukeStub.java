package duke;

import duke.command.CommandParser;
import duke.data.DukeCommandSet;
import duke.data.DukeState;
import duke.data.DukeTaskListStub;
import duke.storage.DukeStorage;
import duke.ui.UiStub;

public class DukeStub extends Duke {

    public DukeStub() {
        commandSet = new DukeCommandSet();
        state = new DukeState();
        ui = new UiStub(this);
        commandParser = new CommandParser();
        taskList = new DukeTaskListStub();
        storage = new DukeStorage(this);
    }
}
