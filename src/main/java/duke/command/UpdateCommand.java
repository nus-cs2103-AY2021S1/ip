package main.java.duke.command;

import main.java.duke.task.Task;
import main.java.duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class UpdateCommand extends Command {
    int taskNumber;
    String newTaskDesc;

    public UpdateCommand(int taskNumber, String newTaskDesc) {
        this.taskNumber = taskNumber;
        this.newTaskDesc = newTaskDesc;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws main.java.duke.dukeexception.InvalidInputException {
        try {
            Task updatedTask = taskList.updateTaskDesc(taskNumber, newTaskDesc);
            ui.showUpdateTask(updatedTask);
        } catch (Exception e) {
            throw new main.java.duke.dukeexception.InvalidInputException("Task does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
