package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.TaskList;
import main.java.duke.dukeexception.InvalidInputException;
import duke.Ui;
import duke.Storage;

public class DoneCommand extends Command {
    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        try {
            taskList.markAsDone(taskNumber);
            ui.showDoneTask(taskList.getTask(taskNumber));
        } catch (Exception e) {
            throw new InvalidInputException("Task does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
