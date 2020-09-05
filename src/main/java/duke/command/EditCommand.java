package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;
import duke.task.TaskList;

public class EditCommand extends Command {
    private CommandType commandType;
    private int index;

    public EditCommand(CommandType commandType, int index) {
        this.commandType = commandType;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (this.commandType) {
        case DONE:
            Task doneTask = tasks.markTaskAsDone(index);
            ui.showDoneTask(doneTask.toString());
            storage.replaceLine(index, doneTask.toFileString());
            break;
        case DELETE:
            Task deletedTask = tasks.deleteTask(index);
            ui.showDeletedTask(deletedTask.toString(), tasks.getNumTasks());
            storage.deleteLine(index);
            break;
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString() {
        return commandType.toString() + " " + index;
    }
}
