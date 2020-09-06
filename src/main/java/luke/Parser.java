package luke;

import luke.commands.*;
import luke.exception.LukeException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Task parseTask(String taskStr) throws LukeException {
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
        String commandType = input.substring(0, input.indexOf(" ")).trim();
        String commandDetails = input.substring(input.indexOf(" ") + 1).trim();

        try {
            switch (commandType) {
            case "list":
                return new ListCommand();
            case "todo":
                return new AddCommand(new Todo(commandDetails));
            case "deadline":
                String[] deadlineDetails = commandDetails.split("/by");
                String deadlineDescription = deadlineDetails[0].trim();
                LocalDateTime by = LocalDateTime.parse(deadlineDetails[1].trim());
                return new AddCommand(new Deadline(deadlineDescription, by));
            case "event":
                String[] eventDetails = commandDetails.split("/at");
                String eventDescription = eventDetails[0].trim();
                String at = eventDetails[1].trim();
                return new AddCommand(new Event(eventDescription, at));
            case "delete":
                return new DeleteCommand(Integer.parseInt(commandDetails));
            case "done":
                return new DoneCommand(Integer.parseInt(commandDetails));
            case "bye":
                return new ExitCommand();
            default:
                throw new LukeException("Sorry I do not understand :( Please try another command.");
            }

        } catch (DateTimeException dateTimeException) {
            throw new LukeException("Please enter the date and time in the format 'DD-MM-YYYY HHMM'!");
        } catch (Exception exception) {
            throw new LukeException("Unable to read command. Please enter it in the correct format!");
        }
    }

}

