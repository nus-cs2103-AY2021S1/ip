package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command when user deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String taskIndex = userInput.substring(7);
            int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
            Task deletedTask = taskList.delete(index);
            int listSize = taskList.size();

            storage.deleteTask(index);

            ui.print("Noted. I've removed this task:\n"
                    + deletedTask.toString() + "\nNow you have " + (listSize)
                    + (listSize > 1 ? " tasks" : " task")
                    + " in the list.");

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me delete.");
        }
    }
}