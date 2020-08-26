package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * DoneCommand is an extension of a command.
 * It marks a task based on the taskNumber in the TaskList as done and updates the changes in the Storage.
 */
public class DoneCommand extends Command {
    private String taskNumber;
    
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
