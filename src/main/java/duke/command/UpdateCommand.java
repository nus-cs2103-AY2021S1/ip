package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.Task;
import main.java.duke.task.TaskList;
import main.java.duke.dukeexception.InvalidInputException;
import main.java.duke.Ui;
import main.java.duke.Storage;

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
            throw new InvalidInputException("Task does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
