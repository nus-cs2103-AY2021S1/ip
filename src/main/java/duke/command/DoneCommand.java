package duke.command;

import java.util.StringJoiner;

import duke.DukeException;
import duke.TaskList;

/**
 * Command to mark a task in the task list as done.
 */
public class DoneCommand implements Command {

    private final String fullCommand;
    private final TaskList taskList;

    /**
     * Initialise command with the command string and task list
     * containing the task to mark as done.
     * @param fullCommand The entire input string from the user.
     * @param taskList The list containing all tasks of the user.
     */
    public DoneCommand(String fullCommand, TaskList taskList) {
        this.fullCommand = fullCommand;
        this.taskList = taskList;
    }

    @Override
    public String executeWithResponse() throws DukeException {
        if (fullCommand.substring(4).length() <= 1) {
            throw new DukeException("Invalid number for done command.");
        } else {
            StringJoiner responseBuilder = new StringJoiner("\n");
            // For processing "done" command with the corresponding integer value.
            String numString = fullCommand.substring(5);
            int entryNum;
            try {
                entryNum = Integer.parseInt(numString);
            } catch (NumberFormatException e) {
                throw new DukeException("You did not pass in a number for done command.");
            }
            if (taskList.markTaskDone(entryNum)) {
                responseBuilder.add("Nice! I've marked this task as done:");
                responseBuilder.add(taskList.getTask(entryNum - 1).toString());
            } else {
                responseBuilder.add("Failed to mark task as done.");
            }
            return responseBuilder.toString();
        }
    }

    @Override
    public boolean continueDuke() {
        return true;
    }
}
