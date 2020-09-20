package duke.command;

import duke.task.TaskList;
import duke.dukeexception.InvalidInputException;
import duke.Ui;
import duke.Storage;

/**
 * Command for marking a specified <code>Task</code> as done.
 */
public class DoneCommand extends Command {
    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String execute(TaskList taskList, Storage storage) throws InvalidInputException {
        try {
            taskList.markAsDone(taskNumber);
            return Ui.showDoneTask(taskList.getTask(taskNumber));
        } catch (Exception e) {
            // index out of range of taskList
            throw new InvalidInputException("Task does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
