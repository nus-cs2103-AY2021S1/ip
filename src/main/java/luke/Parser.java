package luke;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import luke.commands.AddCommand;
import luke.commands.Command;
import luke.commands.DeleteCommand;
import luke.commands.DoneCommand;
import luke.commands.ExitCommand;
import luke.commands.FindCommand;
import luke.commands.ListCommand;
import luke.exception.LukeDateTimeException;
import luke.exception.LukeEmptyCommandException;
import luke.exception.LukeException;
import luke.exception.LukeUnknownCommandException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;

/**
 * Represents a Parser to interpret task and command.
 */
public class Parser {
    /**
     * Parses a line that contains information about a task
     * to generate the corresponding task.
     *
     * @param taskStr the line that contains information about a task
     * @return the corresponding task
     * @throws LukeException if insufficient details are given for a task
     */
    public static Task parseTask(String taskStr) throws LukeException {
        assert taskStr != "" : "Task should not be empty.";
        String[] taskDetails = taskStr.split("\\|");
        Task parsedTask = null;
        if (taskDetails[0].equals("T")) {
            parsedTask = new Todo(taskDetails[2]);
        } else if (taskDetails[0].equals("D")) {
            parsedTask = new Deadline(taskDetails[2], LocalDateTime.parse(taskDetails[3]));
        } else if (taskDetails[0].equals("E")) {
            parsedTask = new Event(taskDetails[2], taskDetails[3]);
        }
        if (taskDetails[1].equals("1")) {
            parsedTask.markAsDone();
        }
        return parsedTask;
    }

    /**
     * Parses user input that contains information about a command
     * to generate the corresponding command.
     *
     * @param input the input from the user
     * @return the corresponding command
     * @throws LukeException if command is given in incorrect format
     */
    public static Command parseCommand(String input) throws LukeException {
        assert input != "" : "User input should not be empty.";
        String[] commandSplit = input.trim().split(" ", 2);
        String commandType = commandSplit[0].trim();
        try {
            switch (commandType) {
            case "list":
                return new ListCommand();
            case "todo":
                return parseTodoCommand(commandSplit);
            case "deadline":
                return parseDeadlineCommand(commandSplit);
            case "event":
                return parseEventCommand(commandSplit);
            case "delete":
                return parseDeleteCommand(commandSplit);
            case "done":
                return parseDoneCommand(commandSplit);
            case "find":
                return parseFindCommand(commandSplit);
            case "bye":
                return new ExitCommand();
            default:
                throw new LukeUnknownCommandException(commandType);
            }
        } catch (DateTimeException e) {
            throw new LukeDateTimeException(e.getMessage());
        }
    }

    private static Command parseTodoCommand(String[] commandSplit) throws LukeEmptyCommandException {
        try {
            String description = commandSplit[1].trim();
            return new AddCommand(new Todo(description));
        } catch (IndexOutOfBoundsException e) {
            throw new LukeEmptyCommandException(commandSplit[0]);
        }
    }

    private static Command parseDeadlineCommand(String[] commandSplit) throws LukeEmptyCommandException {
        try {
            String[] deadlineDetails = commandSplit[1].trim().split("/by");
            String deadlineDescription = deadlineDetails[0].trim();
            LocalDateTime by = LocalDateTime.parse(deadlineDetails[1].trim());
            return new AddCommand(new Deadline(deadlineDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new LukeEmptyCommandException(commandSplit[0]);
        }
    }

    private static Command parseEventCommand(String[] commandSplit) throws LukeEmptyCommandException {
        try {
            String[] eventDetails = commandSplit[1].trim().split("/at");
            String eventDescription = eventDetails[0].trim();
            String at = eventDetails[1].trim();
            return new AddCommand(new Event(eventDescription, at));
        } catch (IndexOutOfBoundsException e) {
            throw new LukeEmptyCommandException(commandSplit[0]);
        }
    }

    private static Command parseDeleteCommand(String[] commandSplit) throws LukeEmptyCommandException {
        try {
            return new DoneCommand(Integer.parseInt(commandSplit[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new LukeEmptyCommandException(commandSplit[0]);
        }
    }

    private static Command parseDoneCommand(String[] commandSplit) throws LukeEmptyCommandException {
        try {
            return new DeleteCommand(Integer.parseInt(commandSplit[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new LukeEmptyCommandException(commandSplit[0]);
        }
    }

    private static Command parseFindCommand(String[] commandSplit) throws LukeEmptyCommandException {
        try {
            return new FindCommand(commandSplit[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new LukeEmptyCommandException(commandSplit[0]);
        }
    }
}

