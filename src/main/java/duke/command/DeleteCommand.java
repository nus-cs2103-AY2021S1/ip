package duke.command;

import java.util.StringJoiner;

import duke.DukeException;
import duke.TaskList;

/**
 * Command to delete a task from the task list based on its index.
 */
public class DeleteCommand implements Command {

    private final String fullCommand;
    private final TaskList taskList;

    /**
     * Initialise Delete command with the entire user input
     * and the task list from which a task will be deleted.
     * @param fullCommand The entire user command.
     * @param taskList The list containing tasks from which one
     *                 task will be deleted.
     */
    public DeleteCommand(String fullCommand, TaskList taskList) {
        this.fullCommand = fullCommand;
        this.taskList = taskList;
    }

    @Override
    public String executeWithResponse() throws DukeException {
        if (fullCommand.substring(6).length() <= 1) {
            throw new DukeException("Invalid number for delete command.");
        } else {
            StringJoiner responseBuilder = new StringJoiner("\n");
            String numString = fullCommand.substring(7);
            int entryNum;
            try {
                entryNum = Integer.parseInt(numString);
            } catch (NumberFormatException e) {
                throw new DukeException("You did not pass in a number for delete!");
            }
            String taskToRemoveString = taskList.getTask(entryNum - 1).toString();
            if (taskList.deleteTask(entryNum)) {
                responseBuilder.add("Noted. I have removed this task:");
                responseBuilder.add(taskToRemoveString);
                responseBuilder.add("Now you have " + taskList.getSize() + " tasks in the list.");
            } else {
                responseBuilder.add("Failed to delete task.");
            }
            return responseBuilder.toString();
        }
    }

    @Override
    public boolean continueDuke() {
        return true;
    }
}
