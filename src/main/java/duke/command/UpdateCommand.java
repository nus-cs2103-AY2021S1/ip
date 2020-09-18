package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.dukeexception.InvalidInputException;
import duke.Ui;
import duke.Storage;

public class UpdateCommand extends Command {
    int taskNumber;
    String newTaskDesc;

    public UpdateCommand(int taskNumber, String newTaskDesc) {
        this.taskNumber = taskNumber;
        this.newTaskDesc = newTaskDesc;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        try {
            Task updatedTask = taskList.updateTaskDesc(taskNumber, newTaskDesc);
            return ui.showUpdateTask(updatedTask);
        } catch (Exception e) {
            throw new InvalidInputException("The task you specified does not exist.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
