package duke;

import java.io.IOException;

import duke.command.CommandParser;
import duke.data.DukeCommandSet;
import duke.data.DukeState;
import duke.data.DukeTaskListStub;
import duke.gui.GuiStub;
import duke.storage.DukeStorage;
import duke.storage.TaskStorageStub;
import duke.ui.UiStub;

public class DukeStub extends Duke {

    /**
     * Constructs a DukeStub.
     */
    public DukeStub() {
        commandSet = new DukeCommandSet();
        state = new DukeState();
        uiResponse = new UiStub(this);
        guiResponse = new GuiStub(this);
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
