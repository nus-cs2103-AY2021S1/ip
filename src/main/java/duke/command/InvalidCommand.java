package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.tasklist.TaskList;

import duke.ui.Ui;

public class InvalidCommand extends Command{
    public InvalidCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(ui.LINE + "Invalid duke.command! \n" + ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
