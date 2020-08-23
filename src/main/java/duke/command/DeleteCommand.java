package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;
import duke.exceptions.NoSuchTaskException;

public class DeleteCommand extends Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        Task deletedTask = tasks.deleteTask(taskNumber);
        ui.printMessage(String.format("Okay, I've deleted the following task: \n %s", deletedTask.toString()));
        storage.updateTasks(tasks.getListOfTasks());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DeleteCommand) {
            return this.taskNumber == ((DeleteCommand) other).taskNumber;
        } else {
            return false;
        }
    }
}
