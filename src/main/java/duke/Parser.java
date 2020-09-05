package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;

/**
 * Encapsulates a parser that breaks down user inputs to generate commands to be executed by Dude.
 */
public class Parser {
    private final static String FIND_SYNTAX_ERROR_MESSAGE = "Error! Note the syntax: find [keyword]";
    private final static String DONE_SYNTAX_ERROR_MESSAGE = "Error! Note the syntax: done [task number]";
    private final static String TODO_SYNTAX_ERROR_MESSAGE = "Error! Note the syntax: todo [description]";
    private final static String DEADLINE_SYNTAX_ERROR_MESSAGE = "Error! Note the syntax: deadline [description] /by [date]";
    private final static String EVENT_SYNTAX_ERROR_MESSAGE = "Error! Note the syntax: event [description] /at [date]";
    private final static String DELETE_SYNTAX_ERROR_MESSAGE = "Error! Note the syntax: delete [task number]";
    /**
     * Parses user inputs to generate commands.
     * @param input Input by user.
     * @return A command object that will be executed by Duke.
     * @throws DukeException
     */
    public Command parse(String input) throws DukeException {
        String[] inputBreakdown = input.split(" ", 2);
        String command = inputBreakdown[0];
        switch (command) {
        case ("list"):
            return new ListCommand();
        case ("find"):
            if (inputBreakdown.length < 2) {
                throw new DukeException(FIND_SYNTAX_ERROR_MESSAGE);
            }
            String keyword = inputBreakdown[1];
            return new FindCommand(keyword);
        case ("done"):
            if (inputBreakdown.length < 2) {
                throw new DukeException(DONE_SYNTAX_ERROR_MESSAGE);
            }
            String doneTaskNumber = inputBreakdown[1];
            return new DoneCommand(doneTaskNumber);
        case ("todo"):
            if (inputBreakdown.length < 2) {
                throw new DukeException(TODO_SYNTAX_ERROR_MESSAGE);
            }
            String todoDescription = inputBreakdown[1];
            return new TodoCommand(todoDescription);
        case ("deadline"):
            if (inputBreakdown.length < 2) {
                throw new DukeException(DEADLINE_SYNTAX_ERROR_MESSAGE);
            }
            String[] deadlineBreakdown = inputBreakdown[1].split(" /by ", 2);
            if (deadlineBreakdown.length < 2) {
                throw new DukeException(DEADLINE_SYNTAX_ERROR_MESSAGE);
            }
            String deadlineDescription = deadlineBreakdown[0];
            String by = deadlineBreakdown[1];
            return new DeadlineCommand(deadlineDescription, parseDateTime(by));
        case ("event"):
            if (inputBreakdown.length < 2) {
                throw new DukeException(EVENT_SYNTAX_ERROR_MESSAGE);
            }
            String[] eventBreakdown = inputBreakdown[1].split(" /at ", 2);
            if (eventBreakdown.length < 2) {
                throw new DukeException(EVENT_SYNTAX_ERROR_MESSAGE);
            }
            String eventDescription = eventBreakdown[0];
            String at = eventBreakdown[1];
            return new EventCommand(eventDescription, parseDateTime(at));
        case ("delete"):
            if (inputBreakdown.length < 2) {
                throw new DukeException(DELETE_SYNTAX_ERROR_MESSAGE);
            }
            String deleteTaskNumber = inputBreakdown[1];
            return new DeleteCommand(deleteTaskNumber);
        case ("bye"):
            return new ExitCommand();
        default:
            return new UnknownCommand();
        }
    }

    private LocalDateTime parseDateTime(String input) throws DukeException {
        String pattern = "d/M/yyyy HHmm";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            throw new DukeException("Error! Note the date format: " + pattern);
        }
    }
}
