package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class UndoCommand extends Command {

    private final Command prevCommand;

    public UndoCommand(Command prevCommand) {
        this.prevCommand = prevCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        prevCommand.undo(tasks);
        ui.displayUndoCommand();
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) throws DukeException {
        prevCommand.undo(tasks);
        return ui.getUndoCommandResponseAsString();
    }

    @Override
    public void undo(TaskList tasks) {
        return;
    }
}
