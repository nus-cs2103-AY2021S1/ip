package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;
import duke.task.TaskList;

public class EditCommand extends Command {
    private CommandType commandType;
    private int index;

    /**
     * Constructs a new EditCommand object of the specified CommandType with the index where the command is executed.
     *
     * @param commandType
     * @param index
     */
    public EditCommand(CommandType commandType, int index) {
        this.commandType = commandType;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output;
        switch (this.commandType) {
        case DONE:
            Task doneTask = tasks.markTaskAsDone(index);
            output = ui.showDoneTask(doneTask.toString());
            storage.replaceLine(index, doneTask.toFileString());
            break;
        case DELETE:
            Task deletedTask = tasks.deleteTask(index);
            output = ui.showDeletedTask(deletedTask.toString(), tasks.getNumTasks());
            storage.deleteLine(index);
            break;
        default:
            output = ui.addErrorPrefix(DukeException.INVALID_COMMAND_EXCEPTION.getMessage());
        }
        return output;
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
