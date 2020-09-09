package duke.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineDateEdit;
import duke.command.DeleteCommand;
import duke.command.DescriptionEdit;
import duke.command.DoneCommand;
import duke.command.EditCommand;
import duke.command.ErrorCommand;
import duke.command.EventDateEdit;
import duke.command.EventDateType;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * This class contains a function to parse user input.
 */
public class Parser {

    /**
     * Returns the appropriate Command based on the given user input.
     *
     * @param input the user input.
     * @return a Command that should be executed in response to the user input.
     */
    public static Command parse(String input) {
        input = input.strip();
        String[] split = input.split("\\s+", 2); // this should contain at least "", even when input is empty
        assert split.length >= 1;

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
                int taskNumber = parseTaskNumber(args, "you have completed", "done 1");
                return new DoneCommand(taskNumber);
            } else if (command.equals("delete")) {
                int taskNumber = parseTaskNumber(args, "you want to remove", "delete 1");
                return new DeleteCommand(taskNumber);
            } else if (command.equals("todo")) {
                if (args.isEmpty()) {
                    return new ErrorCommand("Couldn't add todo! The description of a todo cannot be empty.");
                }
                return new AddCommand(new Task(args));
            } else if (command.equals("deadline")) {
                return new AddCommand(parseDeadline(args));
            } else if (command.equals("event")) {
                return new AddCommand(parseEvent(args));
            } else if (command.equals("find")) {
                ensureArgsPresent(args, "what phrase you would like to search for");
                return new FindCommand(args);
            } else if (command.equals("edit")) {
                return parseEditCommand(args);
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
            throw new DukeParsingException(String.format("I don't understand that. Did you mean %s?", commandName));
        }
    }

    private static void ensureArgsPresent(String args, String errorString) throws DukeParsingException {
        if (args.isBlank()) {
            throw new DukeParsingException("I need to know " + errorString + "!");
        }
    }

    private static int parseTaskNumber(String args, String taskDescription, String example)
            throws DukeParsingException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new DukeParsingException(
                    String.format("You need to tell me the number of the task %s. Eg. %s", taskDescription, example));
        }
    }

    private static EditCommand parseEditCommand(String args) throws DukeParsingException {
        // TODO: implement multiple edits at once eg. edit 1 /start <start> /end <end>
        String[] argsSplit;
        try {
            argsSplit = splitAround(args, "\\s+/((start)|(end)|(date)|(description))\\s+");
        } catch (DukeParsingException e) {
            throw new DukeParsingException("Couldn't edit item. To edit an item, talk to me using the format:\n"
                    + "edit <task number> <what to edit> <edited content>");
        }
        int taskNumber = parseTaskNumber(argsSplit[0], "you want to edit", "edit 1 /description <new description>");
        String content = argsSplit[1];
        if (args.contains("/start") || args.contains("/end") || args.contains("/date")) {
            ensureArgsPresent(content, "the new date");
            Date newDate = parseDate(content);
            if (args.contains("/start")) {
                return new EditCommand(taskNumber, new EventDateEdit(newDate, EventDateType.START));
            } else if (args.contains("/end")) {
                return new EditCommand(taskNumber, new EventDateEdit(newDate, EventDateType.END));
            } else if (args.contains("/date")) {
                return new EditCommand(taskNumber, new DeadlineDateEdit(newDate));
            }
        } else if (args.contains("/description")) {
            ensureArgsPresent(content, "the new task description");
            return new EditCommand(taskNumber, new DescriptionEdit(content));
        }

        throw new DukeParsingException("You need to tell me what you want to edit!"); // TODO better help message
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
            throw new DukeParsingException("Couldn't add deadline! To add a deadline, talk to me using "
                    + "the format deadline <description> /by <date>!");
        }

        // allow DukeParsingException thrown here to be propagated, since it contains a useful error message
        // for the user
        Date date = parseDate(argsSplit[1]);

        return new Deadline(description, date);
    }

    /**
     * Splits a string into 2 around the first occurrence of a regex pattern. An exception is thrown if the pattern does
     * not exist, or if either of the tokens are blank.
     */
    private static String[] splitAround(String string, String pattern) throws DukeParsingException {
        int splitSize = 2;
        String[] argSplit = string.split(pattern, splitSize);

        if (argSplit.length != splitSize) {
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
        // TODO: improve time parsing - this accepts nonsense time formats eg. 27:00. Hm also does not work for some
        // reason.
        final DateFormat withTime = new SimpleDateFormat("d/M/y H:m");

        try {
            return withTime.parse(dateString);
        } catch (ParseException e) {
            // ignore, because we want to try parsing with date only after this
        }

        try {
            return dateOnly.parse(dateString);
        } catch (ParseException e) {
            throw new DukeParsingException(
                    "Invalid date format! I only understand dates in the format day/month/year time "
                            + "or day/month/year. Eg. 15/01/2020 17:00 for 15th January 5pm");
        }

    }
}
