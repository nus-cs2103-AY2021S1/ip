package duke.command;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.main.TaskList;
import duke.task.Task;

/**
 * Abstraction for the done command.
 */
public class DoneCommand extends TaskListOperator {
    private static final String MESSAGE = "Well done! The following task is complete:\n";

    private final String number;

    /**
     * Constructs a new DoneCommand object.
     *
     * @param number Index of task to be completed.
     * @param taskList TaskList to be operated on.
     */
    public DoneCommand(String number, TaskList taskList) {
        super(taskList);
        this.number = number;
    }

    /**
     * Marks a task at the given index on the TaskList (starting from 1) as complete.
     *
     * @return Message detailing the outcome of the operation.
     * @throws InvalidCommandException If a number was not parsed into the command.
     */
    @Override
    public String execute() throws InvalidCommandException {
        try {
            int index = Integer.parseInt(number) - 1;
            Task done = taskList.complete(index);
            return MESSAGE + done;
        } catch (InvalidIndexException e) {
            return e.toString();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }
}
