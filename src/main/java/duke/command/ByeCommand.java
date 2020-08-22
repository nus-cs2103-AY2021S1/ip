package duke.command;

import duke.Ui;
import duke.Storage;

import duke.exception.DukeException;

import duke.task.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
