package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A command dealing with editing or deleting an entry from a Duke-list.
 */
public class EditCommand extends Command {
    /** The Type of the EditCommand. */
    private CommandType commandType;
    /** The index associated with the command. */
    private int index;

    /**
     * Constructs a new EditCommand object of the specified CommandType with the index where the command is executed.
     *
     * @param commandType The type of the EditCommand.
     * @param index The index associated with the command.
     */
    public EditCommand(CommandType commandType, int index) {
        this.commandType = commandType;
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        TaskList tasks = storage.getTasks();
        switch (this.commandType) {
        case DONE:
            Task doneTask = tasks.markTaskAsDone(index);
            ui.showDoneTask(doneTask.toString());
            storage.replaceLine(index, doneTask.toFileString());
            break;
        case DELETE:
            Task deletedTask = tasks.deleteTask(index);
            ui.showDeletedTask(deletedTask.toString(), tasks.getNumEntries());
            storage.deleteLine(index);
            break;
        default:
            throw UserInputException.INVALID_COMMAND;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return commandType.toString() + " " + index;
    }
}
