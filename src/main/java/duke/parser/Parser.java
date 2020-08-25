package duke.parser;

import duke.command.Command;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class Parser {
    public static Command parse(String input) {
        input = input.strip();
        String[] split = input.split("\\s+", 2); // guranteed to contain at least ""
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";

        try {
            if (command.equals("bye")) {
                ensureNoArgs(args, command);
                return new ByeCommand();
            } else if (command.equals("list")) {
                ensureNoArgs(args, command);
                return new ListCommand();
            } else if (command.equals("done")) {
                int index = parseTaskNumber(args, "you have completed", "done 1") - 1;
                return new DoneCommand(index);
            } else if (command.equals("delete")) {
                int index = parseTaskNumber(args, "you want to remove", "delete 1") - 1;
                return new DeleteCommand(index);
            } else if (command.equals("todo")) {
                if (args.isEmpty()) {
                    return new ErrorCommand(
                            "Couldn't add todo! The description of a todo cannot be empty.");
                }
                return new AddCommand(new Task(args));
            } else if (command.equals("deadline")) {
                return new AddCommand(parseDeadline(args));
            } else if (command.equals("event")) {
                return new AddCommand(parseEvent(args));
            } else if (input.isBlank()) {
                return new ErrorCommand("You need to tell me what you want me to do!");
            } else {
                return new ErrorCommand("Sorry, I don't understand that!");
            }
        } catch (DukeParsingException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    // helpers for parse function

    private static void ensureNoArgs(String args, String commandName) throws DukeParsingException {
        if (!args.isEmpty()) {
            throw new DukeParsingException(
                    String.format("I don't understand that. Did you mean %s?", commandName));
        }
    }

    private static int parseTaskNumber(String args, String taskDescription, String example)
            throws DukeParsingException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new DukeParsingException(
                    String.format("You need to tell me the number of the task %s. Eg. %s",
                            taskDescription, example));
        }
    }

    private static Event parseEvent(String args) throws DukeParsingException {
        String[] argsSplit;
        String description;
        String[] dateStrings;

        try {
            argsSplit = splitAround(args, "\\s+/at\\s+");
            description = argsSplit[0];
            dateStrings = splitAround(argsSplit[1], "\\s*-\\s*");
        } catch (DukeParsingException e) {
            throw new DukeParsingException("Couldn't add event! To add an event, talk to me using "
                    + "the format event <description> /at <start>-<end>!");
        }

        Date start = parseDate(dateStrings[0]);
        Date end = parseDate(dateStrings[1]);
        if (start.after(end)) {
            throw new DukeParsingException("Start date is after end date!");
        }
        return new Event(description, start, end);
    }

    private static Deadline parseDeadline(String args) throws DukeParsingException {
        String[] argsSplit;
        String description;
        try {
            argsSplit = splitAround(args, "\\s+/by\\s+");
            description = argsSplit[0];
        } catch (DukeParsingException e) {
            throw new DukeParsingException(
                    "Couldn't add deadline! To add a deadline, talk to me using "
                            + "the format deadline <description> /by <date>!");
        }
        Date date = parseDate(argsSplit[1]);
        return new Deadline(description, date);
    }

    /**
     * Splits a string into 2 around the first occurence of a regex pattern. An exception is thrown
     * if the pattern does not exist, or if either of the tokens are blank.
     */
    private static String[] splitAround(String string, String pattern) throws DukeParsingException {
        int n = 2;
        String[] argSplit = string.split(pattern, n);
        if (argSplit.length != n) {
            throw new DukeParsingException("");
        }
        for (String s : argSplit) {
            if (s.isBlank()) {
                throw new DukeParsingException("");
            }
        }
        return argSplit;
    }

    private static Date parseDate(String dateString) throws DukeParsingException {
        final DateFormat dateOnly = new SimpleDateFormat("d/M/y");
        final DateFormat withTime = new SimpleDateFormat("d/M/y H:m");

        try {
            return withTime.parse(dateString);
        } catch (ParseException e) {}

        try {
            return dateOnly.parse(dateString);
        } catch (ParseException e) {
            throw new DukeParsingException(
                    "Invalid date format! I only understand dates in the format day/month/year time"
                            + "or day/month/year. Eg. 15/01/2020 17:00 for 15th January 5pm");
        }

    }
}
