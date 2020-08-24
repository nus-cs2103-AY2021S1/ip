package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

@FunctionalInterface
public interface CommandExecutable {
    void run(TaskList taskList, Ui ui, String[] arguments) throws DukeException;
}
