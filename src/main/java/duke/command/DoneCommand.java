package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Encapsulates a done command to be executed by Duke.
 */
public class DoneCommand extends Command {
    private String taskNumber;

    /**
     * Marks the task with the given task number in the TaskList as done and updates the changes in the Storage.
     * @param taskNumber Task number of the task.
     */
    public DoneCommand(String taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            taskList.doneTask(index);
            storage.saveTasks(taskList.getTask());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! The task number you entered does not exist.");
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.");
        }
    }
}
