package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.PriorityCommand;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTaskTypeException;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     * @param fullCommand Full user input string.
     * @return The command to be executed based on user input.
     * @throws InvalidInputException If description is empty for adding new tasks.
     * @throws InvalidCommandException If command is not defined.
     * @throws InvalidTaskTypeException If task is invalid.
     * @throws InvalidIndexException If index of task is invalid.
     */
    static Command parse (String fullCommand)
            throws InvalidInputException, InvalidCommandException, InvalidTaskTypeException {
        String[] commands = fullCommand.trim().split(" ", 2);
        assert !fullCommand.isEmpty() : "Command cannot be empty";
        switch(commands[0]) {
        case "bye":
            return new ExitCommand();
        case "todo":
        case "deadline":
        case "event":
            if (commands.length <= 1) {
                throw new InvalidInputException("☹ OOPS!!! The description of a task cannot be empty.");
            }
            return prepareAdd(commands[0], commands[1]);
        case "delete":
            if (commands.length <= 1) {
                throw new InvalidInputException("☹ OOPS!!! An index for a task needs to be provided");
            }
            return prepareDelete(commands[1]);
        case "done":
            if (commands.length <= 1) {
                throw new InvalidInputException("☹ OOPS!!! An index for a task needs to be provided");
            }
            return prepareDone(commands[1]);
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(commands[1]);
        case "priority":
            return preparePriority(commands[1]);
        default:
            throw new InvalidCommandException("Invalid Command: " + commands[0]);
        }
    }

    static Command preparePriority(String command) throws InvalidCommandException {
        String[] details = command.trim().split(" ");

        switch(details[0]) {
        case "/add" : {
            return new PriorityCommand(Action.ADD,
                Integer.valueOf(details[1]), Priority.valueOf(details[2].toUpperCase()));
        }
        case "/update": {
            return new PriorityCommand(Action.UPDATE,
                Integer.valueOf(details[1]), Priority.valueOf(details[2].toUpperCase()));
        }
        case "/delete": {
            return new PriorityCommand(Action.DELETE, Integer.valueOf(details[1]));
        }
        default:
            throw new InvalidCommandException("Invalid Command: " + details[0]);
        }
    }

    /**
     * Parses argument in the context of adding a new task.
     * @param command Type of task added.
     * @param task Task to be added.
     * @return The prepared command to add new task.
     * @throws InvalidInputException If taskDetails is empty or lacking date or timing for Deadline and Event Task.
     * @throws InvalidTaskTypeException If command is not deadline, todo, event.
     */
    static Command prepareAdd(String command, String task)
        throws InvalidInputException, InvalidTaskTypeException {
        switch (command) {
        case "todo": {
            String taskDetails = task.trim();
            if (task.isEmpty()) {
                throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(taskDetails, null, TaskType.TODO);
        }
        case "deadline": {
            String[] taskDetails = task.trim().split(" /by ");
            if (taskDetails[0].isEmpty()) {
                throw new InvalidInputException("☹ OOPS!!! The description of a deadline task cannot be empty.");
            }
            if (taskDetails.length < 2) {
                throw new InvalidInputException("☹ OOPS!!! The deadline of a deadline task cannot be empty.");
            }
            return new AddCommand(taskDetails[0], taskDetails[1], TaskType.DEADLINE);
        }
        case "event": {
            String[] taskDetails = task.trim().split(" /at ");
            if (taskDetails[0].isEmpty()) {
                throw new InvalidInputException("☹ OOPS!!! The description of an event task cannot be empty.");
            }
            if (taskDetails.length < 2) {
                throw new InvalidInputException("☹ OOPS!!! The timing of an event task cannot be empty.");
            }
            return new AddCommand(taskDetails[0], taskDetails[1], TaskType.EVENT);
        }
        default:
            throw new InvalidTaskTypeException("Invalid task type:" + command);
        }
    }

    /**
     * Parses argument in the context of deleting a new task.
     * @param index Index of task to be deleted.
     * @return Returns a prepared command to delete specified task.
     * @throws InvalidInputException If index is not a number.
     */
    static Command prepareDelete(String index) throws InvalidInputException {
        try {
            int taskIndex = Integer.valueOf(index);
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException exception) {
            throw new InvalidInputException("Input is not a number");
        }
    }

    /**
     * Parses argument in the context of marking a task as completed.
     * @param index Index of task to be mark as done.
     * @return Returns a prepared command to mark task as completed.
     * @throws InvalidInputException If index is not a number.
     */
    static Command prepareDone(String index) throws InvalidInputException {
        try {
            int taskIndex = Integer.valueOf(index);
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException exception) {
            throw new InvalidInputException("Input is not a number");
        }
    }
}
