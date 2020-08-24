package duke.command;

import duke.error.DukeError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public interface Command {
    void execute(TaskList taskList, UI ui, Storage storage) throws DukeError;

    boolean isExit();
}
