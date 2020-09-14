package duke.command;

import java.util.StringJoiner;

import duke.DukeException;
import duke.TaskList;
import duke.parser.Parser;
import duke.task.Deadline;

/**
 * Command to create a Deadline task.
 */
public class DeadlineCommand implements Command {

    private final String fullCommand;
    private final TaskList taskList;
    private boolean isDone;

    /**
     * Initialise a deadline command with the user's input and the task list.
     * @param fullCommand The full user input into Duke for this Deadline command.
     * @param taskList The list where the new Deadline will be stored into.
     */
    public DeadlineCommand(String fullCommand, TaskList taskList) {
        this.fullCommand = fullCommand;
        this.taskList = taskList;
        this.isDone = false;
    }

    /**
     * Initialise a deadline command with the user's input and the task list.
     * @param fullCommand The full user input into Duke for this Deadline command.
     * @param taskList The list where the new Deadline will be stored into.
     * @param isDone The state of whether the Deadline task has been done.
     */
    public DeadlineCommand(String fullCommand, TaskList taskList, boolean isDone) {
        this.fullCommand = fullCommand;
        this.taskList = taskList;
        this.isDone = isDone;
    }

    @Override
    public String executeWithResponse() throws DukeException {
        if (fullCommand.substring(8).length() <= 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else {
            StringJoiner responseBuilder = new StringJoiner("\n");
            String description = fullCommand.substring(9);
            String[] processedDesc = Parser.parseTimedTask(description);
            Deadline d = new Deadline(processedDesc[0], processedDesc[1], isDone);
            if (taskList.addTask(d)) {
                responseBuilder.add("Got it, I've added this deadline: " + d);
                responseBuilder.add("Now you have " + taskList.getSize() + " tasks in the list.");
            } else {
                responseBuilder.add("Failed to add the deadline.");
            }
            return responseBuilder.toString();
        }
    }

    @Override
    public boolean continueDuke() {
        return true;
    }
}
