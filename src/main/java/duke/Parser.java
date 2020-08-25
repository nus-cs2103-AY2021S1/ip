package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;

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
     */
    static Command parse (String fullCommand) throws InvalidInputException, InvalidCommandException {
        String[] commandArr = fullCommand.trim().split(" ", 2);
        switch(commandArr[0]) {
        case "bye":
            return new ExitCommand();
        case "todo":
        case "deadline":
        case "event":
            return prepareAdd(commandArr[0], commandArr[1]);
        case "delete":
            return prepareDelete(commandArr[1]);
        case "done":
            return prepareDone(commandArr[1]);
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(commandArr[1]);
        default:
            throw new InvalidCommandException("Invalid Command: " + commandArr[0]);
        }
    }

    /**
     * Parses argument in the context of adding a new task.
     * @param command Type of task added.
     * @param taskDetails Details of task to be added.
     * @return The prepared command to add new task.
     * @throws InvalidInputException If taskDetails is empty or lacking date or timing for Deadline and Event Task.
     * @throws InvalidCommandException If task type is not deadline, todo, event.
     */
    static Command prepareAdd(String command, String taskDetails) throws InvalidInputException, InvalidCommandException {
        switch (command) {
        case "todo": {
            String task = taskDetails.trim();
            if (task.isEmpty()) {
                throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(task, null, TaskType.TODO);
        }
        case "deadline": {
            String[] task = taskDetails.trim().split(" /by ");
            if (task[0].isEmpty()) {
                throw new InvalidInputException("☹ OOPS!!! The description of a deadline task cannot be empty.");
            }
            if (task.length < 2) {
                throw new InvalidInputException("☹ OOPS!!! The deadline of a deadline task cannot be empty.");
            }
            return new AddCommand(task[0], task[1], TaskType.DEADLINE);
        }
        case "event": {
            String[] task = taskDetails.trim().split(" /at ");
            if (task[0].isEmpty()) {
                throw new InvalidInputException("☹ OOPS!!! The description of an event task cannot be empty.");
            }
            if (task.length < 2) {
                throw new InvalidInputException("☹ OOPS!!! The timing of an event task cannot be empty.");
            }
            return new AddCommand(task[0], task[1], TaskType.EVENT);
        }
        default:
            throw new InvalidCommandException("Invalid Command: " + command);
        }
    }

    /**
     * Parses argument in the context of deleting a new task.
     * @param index Index of task to be deleted.
     * @return Returns a prepared command to delete specified task.
     */
    static Command prepareDelete(String index) {
        int taskIndex = Integer.valueOf(index);
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses argument in the context of marking a task as completed.
     * @param index Index of task to be mark as done.
     * @return Returns a prepared command to mark task as completed.
     */
    static Command prepareDone(String index) {
        int taskIndex = Integer.valueOf(index);
        return new DoneCommand(taskIndex);
    }
}
