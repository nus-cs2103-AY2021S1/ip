package duke.command;

import java.util.StringJoiner;

import duke.DukeException;
import duke.TaskList;
import duke.task.Todo;

/**
 * Command to create a To-do Task.
 */
public class TodoCommand implements Command {

    private final String fullCommand;
    private final TaskList taskList;
    private boolean isDone;

    /**
     * Initialise command to create a new To-do to be
     * placed inside the task list.
     * @param fullCommand The user's full input of the to-do command.
     * @param taskList The list containing the tasks of the user.
     */
    public TodoCommand(String fullCommand, TaskList taskList) {
        this.fullCommand = fullCommand;
        this.taskList = taskList;
        this.isDone = false;
    }

    /**
     * Initialise command to create a To-do to be
     * placed inside the task list.
     * @param fullCommand The user's full input of the to-do command.
     * @param taskList The list containing the tasks of the user.
     * @param isDone The state of whether the To-do is done.
     */
    public TodoCommand(String fullCommand, TaskList taskList, boolean isDone) {
        this.fullCommand = fullCommand;
        this.taskList = taskList;
        this.isDone = isDone;
    }

    @Override
    public String executeWithResponse() throws DukeException {
        if (fullCommand.substring(4).length() <= 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            StringJoiner responseBuilder = new StringJoiner("\n");
            String description = fullCommand.substring(5);
            Todo d = new Todo(description, isDone);
            if (taskList.addTask(d)) {
                responseBuilder.add("Got it, I've added this todo: " + d);
                responseBuilder.add("Now you have " + taskList.getSize() + " tasks in the list.");
            } else {
                responseBuilder.add("Failed to add the todo.");
            }
            return responseBuilder.toString();
        }
    }

    @Override
    public boolean continueDuke() {
        return true;
    }

}
