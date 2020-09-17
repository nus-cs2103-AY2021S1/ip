package main.java.duke.command;

import main.java.duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws main.java.duke.dukeexception.InvalidInputException {
        try {
            ui.showDeleteTask(taskList.getTask(taskNumber));
            taskList.deleteTask(taskNumber);
            ui.showNumberOfTasksLeft(taskList);
        } catch (Exception e) {
            throw new main.java.duke.dukeexception.InvalidInputException("Task does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
