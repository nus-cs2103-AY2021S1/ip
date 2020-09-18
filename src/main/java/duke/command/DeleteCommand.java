package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.TaskList;
import main.java.duke.dukeexception.InvalidInputException;
import main.java.duke.Ui;
import main.java.duke.Storage;

public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        try {
            String s = ui.showDeleteTask(taskList.getTask(taskNumber));
            taskList.deleteTask(taskNumber);
            s += ui.showNumberOfTasksLeft(taskList);
            return s;
        } catch (Exception e) {
            // index out of range of taskList
            throw new InvalidInputException("Task does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
