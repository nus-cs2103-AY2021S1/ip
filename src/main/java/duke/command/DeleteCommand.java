package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDeleteCommandException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that deletes a task.
 */
public class DeleteCommand extends UserCommand {

    /**
     * @param userInput User's input.
     */
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param taskList TaskList that contains all the existing tasks.
     * @param ui Ui that helps to print output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String[] deleteCommandArray = userInput.split(" ");
        if (deleteCommandArray.length < 2) {
            throw new InvalidDeleteCommandException();
        } else {
            int itemToBeDeleted = Integer.parseInt(deleteCommandArray[1]);
            if (itemToBeDeleted > taskList.listSize() || itemToBeDeleted <= 0) {
                throw new InvalidDeleteCommandException();
            } else {
                Task item = taskList.getTask(itemToBeDeleted - 1);
                taskList.deleteTask(itemToBeDeleted - 1);
                return ui.printResponse("Noted. I've removed this task:") + "\n"
                        + ui.printResponse(item.toString()) + "\n"
                        + ui.printListCount(taskList);
            }
        }
    }
}
