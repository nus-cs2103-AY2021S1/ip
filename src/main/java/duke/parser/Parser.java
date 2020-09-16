package duke.parser;

import java.util.Date;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EditCommand;
import duke.command.ErrorCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.edit.DeadlineDateEdit;
import duke.edit.DescriptionEdit;
import duke.edit.EventDateEdit;
import duke.edit.EventDateType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * A class containing a single public function to parse user input.
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
                return parseByeCommand(command, args);
            } else if (command.equals("list")) {
                return parseListCommand(command, args);
            } else if (command.equals("done")) {
                return parseDoneCommand(args);
            } else if (command.equals("delete")) {
                return parseDeleteCommand(args);
            } else if (command.equals("todo")) {
                return parseAddCommand(args);
            } else if (command.equals("deadline")) {
                return new AddCommand(parseDeadline(args));
            } else if (command.equals("event")) {
                return new AddCommand(parseEvent(args));
            } else if (command.equals("find")) {
                return parseFindCommand(args);
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

    // parse functions for individual commands

    static ByeCommand parseByeCommand(String command, String args) throws DukeParsingException {
        ParsingHelper.ensureNoArgs(args, command);
        return new ByeCommand();
    }

    static ListCommand parseListCommand(String command, String args) throws DukeParsingException {
        ParsingHelper.ensureNoArgs(args, command);
        return new ListCommand();
    }

    static AddCommand parseAddCommand(String args) throws DukeParsingException {
        ParsingHelper.ensureArgsPresent(args, "Couldn't add todo! The description of a todo cannot be empty.");
        return new AddCommand(new Task(args));
    }

    static DoneCommand parseDoneCommand(String args) throws DukeParsingException {
        int taskNumber = ParsingHelper.parseTaskNumber(args, "you have completed", "done 1");
        return new DoneCommand(taskNumber);
    }

    static DeleteCommand parseDeleteCommand(String args) throws DukeParsingException {
        int taskNumber = ParsingHelper.parseTaskNumber(args, "you want to remove", "delete 1");
        return new DeleteCommand(taskNumber);
    }

    static FindCommand parseFindCommand(String args) throws DukeParsingException {
        ParsingHelper.ensureArgsPresent(args, "I need to know what phrase you would like to search for!");
        return new FindCommand(args);
    }

    static EditCommand parseEditCommand(String args) throws DukeParsingException {
        // TODO: implement multiple edits at once eg. edit 1 /start <start> /end <end>

        String missingArgsMessage = "Couldn't edit item. To edit an item, talk to me using the format:\n"
                + "edit TASK_NUMBER WHAT_TO_EDIT NEW_VALUE";

        // split around /start, /end or /date
        String[] argsSplit = ParsingHelper.splitAround(args, "\\s+/((start)|(end)|(date)|(description))\\s+",
                missingArgsMessage);
        int taskNumber = ParsingHelper
                .parseTaskNumber(argsSplit[0], "you want to edit", "edit 1 /description <new description>");
        String content = argsSplit[1];

        if (args.contains("/start") || args.contains("/end") || args.contains("/date")) {
            ParsingHelper.ensureArgsPresent(content, "I need to know the new date!");
            Date newDate = ParsingHelper.parseDate(content);
            if (args.contains("/start")) {
                return new EditCommand<>(taskNumber, new EventDateEdit(newDate, EventDateType.START));
            } else if (args.contains("/end")) {
                return new EditCommand<>(taskNumber, new EventDateEdit(newDate, EventDateType.END));
            } else if (args.contains("/date")) {
                return new EditCommand<>(taskNumber, new DeadlineDateEdit(newDate));
            }
        } else if (args.contains("/description")) {
            ParsingHelper.ensureArgsPresent(content, "I need to know the new task description!");
            return new EditCommand<>(taskNumber, new DescriptionEdit(content));
        }

        throw new DukeParsingException("You need to tell me what you want to edit!"); // TODO better help message
    }

    static Event parseEvent(String args) throws DukeParsingException {
        String missingArgsMessage = "Couldn't add event! To add an event, talk to me using the format "
                + "event DESCRIPTION /at START-END!";

        String[] argsSplit = ParsingHelper.splitAround(args, "\\s+/at\\s+", missingArgsMessage);
        String description = argsSplit[0];
        String[] dateStrings = ParsingHelper.splitAround(argsSplit[1], "\\s*-\\s*", missingArgsMessage);
        Date start = ParsingHelper.parseDate(dateStrings[0]);
        Date end = ParsingHelper.parseDate(dateStrings[1]);
        if (start.after(end)) {
            throw new DukeParsingException("Start date is after end date!");
        }
        return new Event(description, start, end);
    }

    static Deadline parseDeadline(String args) throws DukeParsingException {
        String[] argsSplit = ParsingHelper.splitAround(args, "\\s+/by\\s+", "Couldn't add deadline! "
                + "To add a deadline, talk to me using the format deadline DESCRIPTION /by DATE!");
        String description = argsSplit[0];
        Date date = ParsingHelper.parseDate(argsSplit[1]);
        return new Deadline(description, date);
    }


}
