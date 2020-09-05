package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.InvalidTaskIdException;
import duke.exception.MissingKeywordException;
import duke.exception.MissingTaskDetailsException;
import duke.exception.MissingTaskIdException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * A parser that is used to parse inputs from the user.
 */
public class Parser {
    private enum ErrorMessage {
        FAILED_TO_MARK_TASK_AS_COMPLETE("Failed to mark task as complete!"),
        FAILED_TO_DELETE_TASK("Failed to delete task!"),
        FAILED_TO_CREATE_TASK("Failed to create task!"),
        FAILED_TO_CREATE_DEADLINE_TASK("Failed to create Deadline task!"),
        FAILED_TO_CREATE_EVENT_TASK("Failed to create Event task!");

        private final String message;

        ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    /**
     * Parses the full command given by the user as input.
     *
     * @param fullCommand The input by the user that is to be parsed.
     * @return A <code>Command</code> that corresponds to the user input.
     * @throws DukeException If the user input is invalid.
     */
    static Command parse(String fullCommand) throws DukeException {
        String[] commandInputs = fullCommand.trim().split(" ", 2);

        if (commandInputs.length == 0) {
            throw new DukeException("Something went wrong when parsing your inputs!");
        }

        String command = commandInputs[0].trim().toLowerCase();

        switch (command) {
        case "list":
            return Parser.getListCommand();

        case "find":
            return Parser.getFindCommand(commandInputs);

        case "done":
            return Parser.getDoneCommand(commandInputs);

        case "delete":
            return Parser.getDeleteCommand(commandInputs);

        case "todo":
        case "deadline":
        case "event":
            return Parser.getAddCommand(command, commandInputs);

        case "bye":
            return Parser.getByeCommand();

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static ByeCommand getByeCommand() {
        return new ByeCommand();
    }

    private static DeleteCommand getDeleteCommand(String[] commandInputs) throws DukeException {
        // "delete" needs to be accompanied by task ID
        if (commandInputs.length < 2) {
            throw new MissingTaskIdException(ErrorMessage.FAILED_TO_DELETE_TASK.getMessage());
        }

        String commandDetails = commandInputs[1].trim();

        try {
            int taskId = Integer.parseInt(commandDetails);
            return new DeleteCommand(taskId);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIdException(ErrorMessage.FAILED_TO_DELETE_TASK.getMessage());
        }
    }

    private static DoneCommand getDoneCommand(String[] commandInputs)
            throws MissingTaskIdException, InvalidTaskIdException {
        // "done" needs to be accompanied by task ID
        if (commandInputs.length < 2) {
            throw new MissingTaskIdException(
                    ErrorMessage.FAILED_TO_MARK_TASK_AS_COMPLETE.getMessage());
        }

        String commandDetails = commandInputs[1].trim();

        try {
            int taskId = Integer.parseInt(commandDetails);
            return new DoneCommand(taskId);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIdException(
                    ErrorMessage.FAILED_TO_MARK_TASK_AS_COMPLETE.getMessage());
        }
    }

    private static FindCommand getFindCommand(String[] commandInputs) throws DukeException {
        // "find" needs to be accompanied by a keyword
        if (commandInputs.length < 2) {
            throw new MissingKeywordException("I'm not sure what tasks to search for...");
        }

        String commandDetails = commandInputs[1].trim();

        return new FindCommand(commandDetails);
    }

    private static ListCommand getListCommand() {
        return new ListCommand();
    }

    private static AddCommand getAddCommand(String command, String[] commandInputs)
            throws DukeException {
        // "todo", "deadline", "event" needs to be accompanied with details on the task to be
        // created
        if (commandInputs.length < 2) {
            throw new MissingTaskDetailsException(ErrorMessage.FAILED_TO_CREATE_TASK.getMessage());
        }

        String commandDetails = commandInputs[1].trim();

        if (command.equals("todo")) {
            return new AddCommand(new Todo(commandDetails));
        } else if (command.equals("deadline")) {
            String[] deadlineDetails = commandDetails.split("/by", 2);

            if (deadlineDetails.length < 2) {
                throw new MissingTaskDetailsException(
                        ErrorMessage.FAILED_TO_CREATE_DEADLINE_TASK.getMessage(),
                        "No deadline was specified!");
            }

            String description = deadlineDetails[0].trim();
            String by = deadlineDetails[1].trim();

            return new AddCommand(new Deadline(description, by));
        } else {
            // Last case would be creating an event
            String[] eventDetails = commandDetails.split("/at", 2);

            if (eventDetails.length < 2) {
                throw new MissingTaskDetailsException(
                        ErrorMessage.FAILED_TO_CREATE_EVENT_TASK.getMessage(),
                        "No date was specified!");
            }

            String description = eventDetails[0].trim();
            String at = eventDetails[1].trim();

            return new AddCommand(new Event(description, at));
        }
    }
}
