package luke;

import luke.commands.*;
import luke.exception.LukeException;
import luke.exception.LukeUnknownCommandException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static Task parseTask(String taskStr) throws LukeException {
        assert taskStr != "" : "Task should not be empty.";
        String[] taskDetails = taskStr.split(" \\| ");
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

    public static Command parseCommand(String input) throws LukeException {
        assert input != "" : "User input should not be empty.";
        List<String> commandSplit = Arrays.asList(input.split(" "));
        String commandType = commandSplit.get(0);
        try {
            switch (commandType) {
            case "list":
                return new ListCommand();
            case "todo":
                parseTodoCommand(commandSplit);
            case "deadline":
                String[] deadlineDetails = commandSplit.get(1).split("/by");
                String deadlineDescription = deadlineDetails[0].trim();
                LocalDateTime by = LocalDateTime.parse(deadlineDetails[1].trim());
                return new AddCommand(new Deadline(deadlineDescription, by));
            case "event":
                String[] eventDetails = commandSplit.get(1).split("/at");
                String eventDescription = eventDetails[0].trim();
                String at = eventDetails[1].trim();
                return new AddCommand(new Event(eventDescription, at));
            case "delete":
                return new DeleteCommand(Integer.parseInt(commandSplit.get(1)));
            case "done":
                return new DoneCommand(Integer.parseInt(commandSplit.get(1)));
            case "bye":
                return new ExitCommand();
            default:
                throw new LukeUnknownCommandException(commandType);
            }
        } catch (DateTimeException dateTimeException) {
            throw new LukeException("Please enter the date and time in the format 'DD-MM-YYYY HHMM'!");
        } catch (Exception exception) {
            throw new LukeException("Unable to read command. Please enter it in the correct format!");
        }
    }

    private static Command parseTodoCommand(List<String> commandSplit) throws LukeUnknownCommandException {
        try {
            String description = commandSplit.get(1);
            return new AddCommand(new Todo(description));
        } catch (IndexOutOfBoundsException e) {
            throw new LukeUnknownCommandException(commandSplit.get(0));
        }

    }

}

