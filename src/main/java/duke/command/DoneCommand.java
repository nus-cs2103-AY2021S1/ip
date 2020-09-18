package duke.command;

import duke.command.Command;
import duke.task.TaskList;
import duke.dukeexception.InvalidInputException;
import duke.Ui;
import duke.Storage;

public class DoneCommand extends Command {
    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        try {
            taskList.markAsDone(taskNumber);
            return ui.showDoneTask(taskList.getTask(taskNumber));
        } catch (Exception e) {
            // index out of range of taskList
            throw new InvalidInputException("Task does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
