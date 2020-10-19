package main.java.com.jacob.duke.io;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.command.ByeCommand;
import main.java.com.jacob.duke.command.Command;
import main.java.com.jacob.duke.command.DeadlineCommand;
import main.java.com.jacob.duke.command.DeleteCommand;
import main.java.com.jacob.duke.command.DeleteNoteCommand;
import main.java.com.jacob.duke.command.DoneCommand;
import main.java.com.jacob.duke.command.EventCommand;
import main.java.com.jacob.duke.command.FindCommand;
import main.java.com.jacob.duke.command.NoteCommand;
import main.java.com.jacob.duke.command.PrintFilteredListDateTimeCommand;
import main.java.com.jacob.duke.command.PrintListCommand;
import main.java.com.jacob.duke.command.PrintNoteListCommand;
import main.java.com.jacob.duke.command.TodoCommand;

public class Parser {
    /**
     * Parses the full command and decides which command object to create and return for execution.
     *
     * @param fullCommand the full user console input command.
     * @return Command for execution.
     * @throws DukeException thrown when invalid message is given.
     */
    public Command parse(String fullCommand) throws DukeException {
        String[] splitStrings = fullCommand.split(" ");

        assert(splitStrings.length >= 1);

        String firstInput = splitStrings[0];

        Command c;
        switch (firstInput) {
        case "todo":
            checkDescriptionNotEmpty(firstInput, fullCommand);
            c = new TodoCommand(fullCommand);
            break;
        case "deadline":
            checkDescriptionNotEmpty(firstInput, fullCommand);
            checkCorrectDateTime(splitStrings);
            checkBreakpointExists("/by", fullCommand, "deadline");
            c = new DeadlineCommand(fullCommand);
            break;
        case "event":
            checkDescriptionNotEmpty(firstInput, fullCommand);
            checkCorrectDateTime(splitStrings);
            checkBreakpointExists("/at", fullCommand, "event");
            c = new EventCommand(fullCommand);
            break;
        case "delete":
            checkCommandLength(2, splitStrings);
            c = new DeleteCommand(fullCommand);
            break;
        case "list":
            checkCommandLength(1, splitStrings);
            c = new PrintListCommand();
            break;
        case "list-due":
            checkCommandLength(3, splitStrings);
            checkCorrectDateTime(splitStrings);
            c = new PrintFilteredListDateTimeCommand(fullCommand);
            break;
        case "find":
            checkDescriptionNotEmpty(firstInput, fullCommand);
            c = new FindCommand(fullCommand);
            break;
        case "done":
            checkDescriptionNotEmpty(firstInput, fullCommand);
            checkCommandLength(2, splitStrings);
            c = new DoneCommand(fullCommand);
            break;
        case "bye":
            checkCommandLength(1, splitStrings);
            c = new ByeCommand();
            break;
        case "note":
            checkBreakpointExists("?", fullCommand, "note");
            checkDescriptionNotEmpty(firstInput, fullCommand);
            c = new NoteCommand(fullCommand);
            break;
        case "note-list":
            checkCommandLength(1, splitStrings);
            c = new PrintNoteListCommand();
            break;
        case "note-delete":
            checkCommandLength(2, splitStrings);
            c = new DeleteNoteCommand(fullCommand);
            break;
        default:
            throw new DukeException(" I don't recognise that. Type a valid request please.");
        }
        return c;
    }

    private void checkDescriptionNotEmpty(String commandType, String fullCommand) throws DukeException {
        if (commandType.length() + 1 >= fullCommand.length()) {
            throw new DukeException("Hey. A " + commandType + " request cannot be empty.");
        }
    }

    private void checkBreakpointExists(String breakpoint, String fullCommand, String commandType) throws DukeException {
        if (!fullCommand.contains(breakpoint)) {
            throw new DukeException("Your " + commandType + " request is incomplete. It does not contain: "
                    + breakpoint);
        }
    }

    private void checkCommandLength(int commandLength, String[] fullCommand) throws DukeException {
        if (commandLength != fullCommand.length) {
            throw new DukeException("I dont recognise this request, you might have added too much.");
        }
    }

    //parse date time exceptions - catches java.time.format.DateTimeParseException
    private void checkCorrectDateTime(String[] splitCommand) throws DukeException {
        //checks the last string parsed in full command
        String dateTime = splitCommand[splitCommand.length - 2] + " " + splitCommand[splitCommand.length - 1];
        try {
            LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("This is an invalid Date time format: use YYYY-MM-DD 2359");
        }
    }
}
