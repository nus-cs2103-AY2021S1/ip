package main.java.duke.command;

import main.java.duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class DoneCommand extends Command {
    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws main.java.duke.dukeexception.InvalidInputException {
        try {
            taskList.markAsDone(taskNumber);
            ui.showDoneTask(taskList.getTask(taskNumber));
        } catch (Exception e) {
            throw new main.java.duke.dukeexception.InvalidInputException("Task does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
