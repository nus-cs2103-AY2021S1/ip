package duke;

import java.time.LocalDateTime;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.FixedDurationTask;
import duke.task.Todo;

/**
 * Parser class that helps to understand user input and returns the correct commands.
 */
public class Parser {

    private static Command parseDone(String input) throws DukeException {
        assert input.contains("done");
        if (input.matches("done\\s*")) {
            throw new EmptyArgumentException("duke.task.Task Index");
        }
        if (!input.matches("done \\d+")) {
            throw new InvalidArgumentException("duke.task.Task Index");
        }
        String[] arr = input.split(" ");
        int index = Integer.parseInt(arr[1]) - 1;
        return new DoneCommand(index);
    }
    private static Command parseTodoTask(String input) throws DukeException {
        assert input.contains("todo");
        if (input.matches("todo\\s*")) {
            throw new EmptyArgumentException("todo's description");
        }
        return new AddTaskCommand(new Todo(input.substring(5)));
    }
    private static Command parseFixedDurationTask(String input) throws DukeException {
        String command = "fdtask";
        assert input.contains(command);
        if (input.matches(command + "\\s*")) {
            throw new EmptyArgumentException(command + "'s description");
        }
        if (!input.matches(command + " .+/duration .+")) {
            throw new InvalidArgumentException(command + "'s description");
        }
        String[] split = input.substring(command.length() + 1).split("/duration");
        String dateTimeString = split[1].stripLeading();
        return new AddTaskCommand(new FixedDurationTask(split[0].stripTrailing(), dateTimeString));
    }
    private static Command parseDeadlineTask(String input) throws DukeException {
        assert input.contains("deadline");
        if (input.matches("deadline\\s*")) {
            throw new EmptyArgumentException("deadline's description");
        }
        if (!input.matches("deadline .+/by \\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
            throw new InvalidArgumentException("deadline's description/time");
        }
        String[] split = input.substring(9).split("/by");
        String dateTimeString = split[1].stripLeading();
        LocalDateTime dateTime = parseDateTime(dateTimeString);
        return new AddTaskCommand(new Deadline(split[0].stripTrailing(), dateTime));
    }
    private static Command parseEventTask(String input) throws DukeException {
        assert input.contains("event");
        if (input.matches("event\\s*")) {
            throw new EmptyArgumentException("event's description");
        }
        if (!input.matches("event .+/at \\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
            throw new InvalidArgumentException("event description/datetime");
        }
        String[] split = input.substring(6).split("/at");
        String dateTimeString = split[1].stripLeading();
        return new AddTaskCommand(new Event(split[0].stripTrailing(), parseDateTime(dateTimeString)));
    }
    private static Command parseDelete(String input) throws DukeException {
        assert input.contains("delete");
        if (input.matches("delete\\s*")) {
            throw new EmptyArgumentException("Task Index");
        }
        if (!input.matches("delete \\d+")) {
            throw new InvalidArgumentException("Task Index");
        }
        String[] arr = input.split(" ");
        int index = Integer.parseInt(arr[1]) - 1;
        return new DeleteCommand(index);
    }
    private static Command parseFind(String input) throws EmptyArgumentException {
        assert input.contains("find");
        if (input.matches("find\\s*")) {
            throw new EmptyArgumentException("find argument");
        }
        return new FindCommand(input.substring(5));

    }
    /**
     * Parse a dateTime string of format : yyyy-mm-dd HHmm
     * and returns with format : yyyy-mm-ddTHH:mm
     *
     * @param s  String to be parsed.
     * @return  LocalDateTime parsable string.
     */
    public static LocalDateTime parseDateTime(String s) {
        String[] dateTimeSplit = s.split(" ");
        String date = dateTimeSplit[0];
        String hour = dateTimeSplit[1].substring(0, 2);
        String min = dateTimeSplit[1].substring(2);
        return LocalDateTime.parse(date + "T" + hour + ":" + min);
    }

    /**
     * Main method of the Parser class, this will evaluate a input string and
     * return the appropriate command.
     *
     * @param input  Input to be parsed
     * @return  Command Object that represents the input's desires.
     * @throws DukeException  If input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.matches("done.*")) {
            return parseDone(input);
        } else if (input.matches("todo.*")) {
            return parseTodoTask(input);
        } else if (input.matches("deadline.*")) {
            return parseDeadlineTask(input);
        } else if (input.matches("event.*")) {
            return parseEventTask(input);
        } else if (input.matches("fdtask.*")) {
            return parseFixedDurationTask(input);
        } else if (input.matches("delete.*")) {
            return parseDelete(input);
        } else if (input.matches("find.*")) {
            return parseFind(input);
        } else {
            assert !input.contains("done")
                    && !input.contains("todo")
                    && !input.contains("deadline")
                    && !input.contains("event")
                    && !input.contains("delete")
                    && !input.contains("fdtask")
                    && !input.contains("find");
            throw new InvalidCommandException();
        }
    }


}
