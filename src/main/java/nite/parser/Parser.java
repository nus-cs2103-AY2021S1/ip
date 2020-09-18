package nite.parser;

import nite.command.AddCommand;
import nite.command.Command;
import nite.command.DeleteCommand;
import nite.command.DoneCommand;
import nite.command.ExitCommand;
import nite.command.FindCommand;
import nite.command.HelpCommand;
import nite.command.InvalidCommand;
import nite.command.ListCommand;
import nite.command.SortCommand;
import nite.exception.NiteException;
import nite.task.Deadline;
import nite.task.Event;
import nite.task.Task;
import nite.task.ToDo;

/**
 * Represents the Parser used to make sense of user commands.
 */
public class Parser {
    /**
     * Parses user input into executable Commands.
     *
     * @param fullCommand Entire user input.
     * @return duke.Duke Command corresponding to input.
     * @throws NiteException If task is not successfully parsed.
     */
    public static Command parse(String fullCommand) throws NiteException {
        Command command;

        if (fullCommand.startsWith("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("list")) {
            command = new ListCommand();
        } else if (fullCommand.startsWith("help")) {
            command = new HelpCommand();
        } else if (fullCommand.startsWith("done")) {
            try {
                command = new DoneCommand(Integer.parseInt(fullCommand.split(" ")[1]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                throw new NiteException("Invalid format of task number!");
            }
        } else if (fullCommand.startsWith("delete")) {
            try {
                command = new DeleteCommand(Integer.parseInt(fullCommand.split(" ")[1]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                throw new NiteException("Invalid format of task number!");
            }
        } else if (fullCommand.startsWith("todo")) {
            command = new AddCommand(parseTask("todo", fullCommand));
        } else if (fullCommand.startsWith("deadline")) {
            command = new AddCommand(parseTask("deadline", fullCommand));
        } else if (fullCommand.startsWith("event")) {
            command = new AddCommand(parseTask("event", fullCommand));
        } else if (fullCommand.startsWith("find")) {
            try {
                command = new FindCommand(fullCommand.split(" ")[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new NiteException("Invalid format of find command!");
            }
        } else if (fullCommand.startsWith("sort")) {
            command = new SortCommand(fullCommand.split(" "));
        } else {
            command = new InvalidCommand(fullCommand);
        }
        return command;
    }

    /**
     * Parses a task input into Task objects.
     *
     * @param type Type of task (todo, deadline, or event).
     * @param taskString The full input of the task.
     * @return Task obtained from the input.
     * @throws NiteException If task is not successfully parsed.
     */
    public static Task parseTask(String type, String taskString) throws NiteException {
        Task task;
        switch (type) {
        case "todo":
            task = getTodo(taskString);
            break;
        case "deadline":
            task = getDeadline(taskString);
            break;
        case "event":
            task = getEvent(taskString);
            break;
        default:
            assert false : "Cases should be exhaustive.";
            throw new NiteException("Unexpected value: " + type);
        }
        return task;
    }

    private static Task getEvent(String taskString) throws NiteException {
        Task task;
        if (taskString.length() <= 6) {
            throw new NiteException("The description of an event cannot be empty.");
        }
        String[] taskArr2 = taskString.substring(6).split(" /at ");
        try {
            task = new Event(taskArr2[0], taskArr2[1]);
        } catch (IndexOutOfBoundsException ex) {
            throw new NiteException("Invalid description of an event.");
        }
        return task;
    }

    private static Task getDeadline(String taskString) throws NiteException {
        Task task;
        if (taskString.length() <= 9) {
            throw new NiteException("The description of a deadline cannot be empty.");
        }
        String[] taskArr = taskString.substring(9).split(" /by ");
        try {
            task = new Deadline(taskArr[0], taskArr[1]);
        } catch (IndexOutOfBoundsException ex) {
            throw new NiteException("Invalid description of a deadline.");
        }
        return task;
    }

    private static Task getTodo(String taskString) throws NiteException {
        Task task;
        if (taskString.length() <= 5) {
            throw new NiteException("The description of a todo cannot be empty.");
        }
        task = new ToDo(taskString.substring(5));
        return task;
    }
}
