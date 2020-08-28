package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;

public interface Command {
    void execute(TaskList tasks, Ui ui);
    boolean isExit();
}
