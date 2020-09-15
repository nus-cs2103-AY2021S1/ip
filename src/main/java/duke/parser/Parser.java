package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.NoteCommand;
import duke.command.NotesCommand;
import duke.command.RemoveNoteCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;

/**
 * Handles user inputs and make sense out of it.
 */
public class Parser {

    private static final String EXCEPTION_LIST_EXTRA_INPUTS =
            "Extra inputs detected! Please only input 'list'.";
    private static final String EXCEPTION_EMPTY_TODO =
            "OOPS!!! The description of a todo cannot be empty.";
    private static final String EXCEPTION_EMPTY_DEADLINE =
            "OOPS!!! The description of a deadline cannot be empty.";
    private static final String EXCEPTION_EMPTY_EVENT =
            "OOPS!!! The description of a event cannot be empty.";
    private static final String EXCEPTION_NO_ITEM_NUMBER =
            "Please specify item number!";
    private static final String EXCEPTION_NO_SEARCH_TERM =
            "Please specify a description to search!";
    private static final String EXCEPTION_DEADLINE_FORMAT =
            "Please input in the following format "
            + "'deadline <description> /by <yyyy-MM-dd HH:mm>' with a valid date & time";
    private static final String EXCEPTION_EVENT_FORMAT =
            "Please input in the following format "
            + "'event <description> /at <yyyy-MM-dd HH:mm>' with a valid date & time";
    private static final String EXCEPTION_INVALID_FORMAT =
            "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String EXCEPTION_EMPTY_NOTE =
            "OOPS!!! The description of a note cannot be empty.";
    private static final String EXCEPTION_NOTES_EXTRA_INPUTS =
            "Extra inputs detected! Please only input 'notes' to see your notes!";
    private static final String EXCEPTION_EMPTY_REMOVE =
            "OOPS!!! The please enter a note number to remove.";
    private static final String EXCEPTION_HELP_EXTRA_INPUTS =
            "Extra inputs detected! Please only input 'help'.";

    /**
     * Takes in a line of user input as a String and returns a relevant Command. Otherwise,
     * throw a DukeException when a command is invalid.
     *
     * @param fullCommand user input
     * @return Command to be executed
     * @throws DukeException when inputs are invalid or incomplete
     */
    public static Command parse(String fullCommand) throws DukeException {

        //remove leading and trailing white spaces
        String noWhiteSpace = fullCommand.strip();

        //get first word of command
        String[] instruction = noWhiteSpace.split(" ", 2);

        assert (instruction.length == 1 | instruction.length == 2)
                : "instruction array should be of length 1 or 2";

        if (instruction[0].equals("bye")) {
            return new ExitCommand();
        } else if (instruction[0].equals("list")) {
            if (instruction.length != 1) {
                throw new DukeException(EXCEPTION_LIST_EXTRA_INPUTS);
            }
            return new ListCommand();

        } else if (instruction[0].equals("todo")) {
            if (instruction.length == 1) {
                throw new DukeException(EXCEPTION_EMPTY_TODO);
            }
            return new TodoCommand(instruction[1]);

        } else if (instruction[0].equals("deadline")) {
            if (instruction.length == 1) {
                throw new DukeException(EXCEPTION_EMPTY_DEADLINE);
            }
            //check deadline format
            boolean correctDeadlineFormat = (instruction[1].contains(" /by "))
                    && (instruction[1].split(" /by ")[1]
                    .matches("^\\d{4}-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01]) "
                            + "([01]?[0-9]|2[0-3]):[0-5][0-9]"));
            if (!correctDeadlineFormat) {
                throw new DukeException(EXCEPTION_DEADLINE_FORMAT);
            }
            String description = instruction[1].split(" /by ")[0];
            String deadlineTime = instruction[1].split(" /by ")[1];
            return new DeadlineCommand(description, deadlineTime);

        } else if (instruction[0].equals("event")) {

            if (instruction.length == 1) {
                throw new DukeException(EXCEPTION_EMPTY_EVENT);
            }
            //check event format
            boolean correctEventFormat = (instruction[1].contains(" /at "))
                    && (instruction[1].split(" /at ")[1]
                    .matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) "
                            + "([01]?[0-9]|2[0-3]):[0-5][0-9]"));
            if (!correctEventFormat) {
                throw new DukeException(EXCEPTION_EVENT_FORMAT);
            }
            String description = instruction[1].split(" /at ")[0];
            String eventTime = instruction[1].split(" /at ")[1];
            return new EventCommand(description, eventTime);
        } else if (instruction[0].equals("done")) {
            //done with no other arguments
            if (instruction.length == 1) {
                throw new DukeException(EXCEPTION_NO_ITEM_NUMBER);
            } else if (instruction.length == 2) { //done with exactly 2 inputs
                return new DoneCommand(instruction[1]);
            }
        } else if (instruction[0].equals("delete")) {
            //check for other arguments
            if (instruction.length == 1) {
                throw new DukeException(EXCEPTION_NO_ITEM_NUMBER);
            } else if (instruction.length == 2) { //done with exactly 2 inputs
                return new DeleteCommand(instruction[1]);
            }
        } else if (instruction[0].equals("find")) {
            //find with no other arguments
            if (instruction.length == 1) {
                throw new DukeException(EXCEPTION_NO_SEARCH_TERM);
            }
            return new FindCommand(instruction[1]);
        } else if (instruction[0].equals("note")) {
            if (instruction.length == 1) {
                throw new DukeException(EXCEPTION_EMPTY_NOTE);
            }
            return new NoteCommand(instruction[1]);
        } else if (instruction[0].equals("notes")) {
            if (instruction.length != 1) {
                throw new DukeException(EXCEPTION_NOTES_EXTRA_INPUTS);
            }
            return new NotesCommand();
        } else if (instruction[0].equals("RemoveNote")) {
            if (instruction.length == 1) {
                throw new DukeException(EXCEPTION_EMPTY_REMOVE);
            }
            return new RemoveNoteCommand(instruction[1]);
        } else if (instruction[0].equals("help")) {
            if (instruction.length != 1) {
                throw new DukeException(EXCEPTION_HELP_EXTRA_INPUTS);
            }
            return new HelpCommand();
        } else {
            throw new DukeException(EXCEPTION_INVALID_FORMAT);
        }

        throw new DukeException(EXCEPTION_INVALID_FORMAT);
    }

}
