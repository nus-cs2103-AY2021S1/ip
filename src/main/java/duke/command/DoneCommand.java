package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.ExceptionType;

/**
 * This class marks a specific task chosen by user as done.
 */
public class DoneCommand extends Command {
    public DoneCommand() {
        super();
    }

    /**
     * Marks task at index given as done.
     *
     * @param index index of task in task list.
     * @param userTasks list of user tasks.
     * @param storage storage object.
     * @return response after execution of command.
     */
    public String execute(int index, TaskList userTasks, Storage storage) throws DukeException {
        try {
            if (index >= userTasks.getTaskListSize()) {
                throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
            } else {
                userTasks.markTaskAsDone(index);
                response = new Ui().markAsDoneMessage(userTasks.getTask(index));
            }
            storage.saveToFile(userTasks.getTaskList());
        } catch (DukeException ex) {
            response = new Ui().errorMessage(ex);
        }
        return getResponse();
    }
}