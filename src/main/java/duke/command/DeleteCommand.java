package duke.command;

import duke.TaskList;
import duke.UserInterface;
import duke.exception.DukeIndexException;

/**
 * DeleteCommand class for when delete command is prompted by User
 * @author Kor Ming Soon
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for delete command.
     *
     * @param index index of the task belonging in the list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execution command for Delete.
     *
     * @param taskList list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @throws DukeIndexException When the input index does not match the list.
     * @return response for when user deletes a task.
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui) throws DukeIndexException {

        if (this.index > taskList.getTaskSize() - 1 || this.index < 0) {
            String errorMessage = "Wrong list number input. "
                    + "Please put a number between 1 and "
                    + taskList.getTaskSize();
            throw new DukeIndexException(errorMessage);
        }

        String response = ui.printDelete(taskList.getTaskDetail(index).toString(),
                taskList.getTaskSize() - 1);
        taskList.removeTask(index);
        return response;
    }
}
