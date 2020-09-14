package duke.command;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.main.TaskList;
import duke.task.Task;

/**
 * Abstraction for the delete command.
 */
public class DeleteCommand extends TaskListOperator {
    private static final String MESSAGE_HEAD = "Noted, I have removed the below task:\n";
    private static final String MESSAGE_MIDDLE = "\nNow you have ";
    private static final String MESSAGE_END = " task(s) left";

    private final String number;

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param number Index of task to delete.
     * @param taskList TaskList to be operated on.
     */
    public DeleteCommand(String number, TaskList taskList) {
        super(taskList);
        this.number = number;
    }

    /**
     * Deletes a task at the given index on the TaskList (starting from 1).
     *
     * @return Message detailing the outcome of the deletion.
     * @throws InvalidCommandException If a number was not parsed into the command.
     */
    @Override
    public String execute() throws InvalidCommandException {
        try {
            int index = Integer.parseInt(number) - 1;
            Task deleted = taskList.delete(index);
            return MESSAGE_HEAD + deleted + MESSAGE_MIDDLE
                    + taskList.size() + MESSAGE_END;
        } catch (InvalidIndexException e) {
            return e.toString();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }
}
