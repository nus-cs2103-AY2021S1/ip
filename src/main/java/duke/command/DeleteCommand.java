package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        try {
            // parse for argument - item number
            int itemNumber = Integer.parseInt(args.split(" ")[1]);
            Task task = taskItems.removeTask(itemNumber);
            ui.deleteTaskReply(task, taskItems);
            storage.saveTaskToMemory(taskItems.getAll());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Cannot delete task that does not exist");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
