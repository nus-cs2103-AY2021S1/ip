package duke.command;

import duke.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

public interface Command {
    void excute(TaskList tasks, Ui ui, Storage storage);
    boolean isExit();
}
