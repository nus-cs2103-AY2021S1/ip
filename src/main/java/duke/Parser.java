package duke;

import java.util.List;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.InvalidTaskIdException;
import duke.exception.MissingKeywordException;
import duke.exception.MissingTaskDetailsException;
import duke.exception.MissingTaskIdException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * A parser that is used to parse inputs from the user.
 */
public class Parser {
    private enum ErrorMessage {
        FAILED_TO_MARK_TASK_AS_COMPLETE("Failed to mark task as complete!"),
        FAILED_TO_DELETE_TASK("Failed to delete task!"),
        FAILED_TO_UPDATE_TASK("Failed to update task!"),
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
        case "help":
            return Parser.getHelpCommand();

        case "list":
            return Parser.getListCommand();

        case "find":
            return Parser.getFindCommand(commandInputs);

        case "update":
            return Parser.getUpdateCommand(commandInputs);

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

    private static HelpCommand getHelpCommand() {
        return new HelpCommand();
    }

    private static UpdateCommand getUpdateCommand(String[] commandInputs) throws DukeException {
        // "update" needs to be accompanied by task ID
        if (commandInputs.length < 2) {
            throw new MissingTaskIdException(ErrorMessage.FAILED_TO_UPDATE_TASK.getMessage());
        }

        // Should look something like [:3", "todo desc"] or ["3", "event desc /at 2020-10-10"]
        String[] commandDetails = commandInputs[1].trim().split(" ", 2);

        int taskId;

        try {
            taskId = Integer.parseInt(commandDetails[0]);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIdException(ErrorMessage.FAILED_TO_UPDATE_TASK.getMessage());
        }

        // ["todo", "desc"] or ["event", "desc /at 2020-10-10"]
        String[] taskDetails = commandDetails[1].split(" ", 2);
        String command = taskDetails[0].trim();
        if (List.of("todo", "deadline", "event").contains(command)) {
            return new UpdateCommand(taskId, parseTaskInputs(command, taskDetails, false));
        } else {
            throw new DukeException("Invalid inputs to the update command!");
        }
    }

    private static DeleteCommand getDeleteCommand(String[] commandInputs) throws DukeException {
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
        return new AddCommand(parseTaskInputs(command, commandInputs, true));
    }

    /**
     * Returns the task that will be created from the given inputs
     *
     * @param command       The command used to create the task - "todo", "deadline", or "event"
     * @param commandInputs The array of string that is obtained by splitting the full command at
     *                      the first whitespace.
     * @return The task that will be created from the given inputs
     * @throws DukeException If the inputs are invalid
     */
    private static Task parseTaskInputs(String command, String[] commandInputs,
                                        boolean isCreatingTask) throws DukeException {
        assert command.equals("todo") || command.equals("deadline") || command.equals("event");

        // "todo", "deadline", "event" needs to be accompanied with details on the task to be
        // created
        if (commandInputs.length < 2) {
            if (isCreatingTask) {
                throw new MissingTaskDetailsException(
                        ErrorMessage.FAILED_TO_CREATE_TASK.getMessage());
            } else {
                throw new MissingTaskDetailsException(
                        ErrorMessage.FAILED_TO_UPDATE_TASK.getMessage());
            }
        }

        String commandDetails = commandInputs[1].trim();

        switch (command) {
        case "todo":
            return new Todo(commandDetails);

        case "deadline":
            String[] deadlineDetails = commandDetails.split("/by", 2);
            if (deadlineDetails.length < 2) {
                if (isCreatingTask) {
                    throw new MissingTaskDetailsException(
                            ErrorMessage.FAILED_TO_CREATE_DEADLINE_TASK.getMessage(),
                            "No deadline was specified!");
                } else {
                    throw new MissingTaskDetailsException(
                            ErrorMessage.FAILED_TO_UPDATE_TASK.getMessage(),
                            "No deadline was specified!");
                }
            }

            String deadlineDescription = deadlineDetails[0].trim();
            String by = deadlineDetails[1].trim();

            return new Deadline(deadlineDescription, by);

        case "event":
            // Last case would be creating an event
            String[] eventDetails = commandDetails.split("/at", 2);

            if (eventDetails.length < 2) {
                if (isCreatingTask) {
                    throw new MissingTaskDetailsException(
                            ErrorMessage.FAILED_TO_CREATE_EVENT_TASK.getMessage(),
                            "No date was specified!");
                } else {
                    throw new MissingTaskDetailsException(
                            ErrorMessage.FAILED_TO_UPDATE_TASK.getMessage(),
                            "No date was specified!");
                }
            }

            String eventDescription = eventDetails[0].trim();
            String at = eventDetails[1].trim();

            return new Event(eventDescription, at);
        default:
            // Shouldn't happen
            throw new DukeException(ErrorMessage.FAILED_TO_CREATE_EVENT_TASK.getMessage());
        }
    }
}
