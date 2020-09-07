package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidEventException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Helps parse user input.
 */
public class Parser {
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String HELP = "help";
    private static final List<String> commands = Arrays.asList(BYE, LIST, TODO,
                                                    DEADLINE, EVENT, DONE, DELETE, FIND, HELP);
    /**
     * Parses an input string and returns a Command based on the input.
     *
     * @param input User input that is provided.
     * @return Command.
     * @throws InvalidDescriptionException If description format for new task is wrong.
     * @throws InvalidCommandException     If no such command exists.
     * @throws InvalidEventException       If event command format is wrong.
     * @throws InvalidDeadlineException    If deadline command format is wrong.
     * @throws InvalidDateFormatException  If date is not given in correct format.
     */
    public static Command parse(String input) throws InvalidDescriptionException, InvalidCommandException,
            InvalidEventException, InvalidDeadlineException, InvalidDateFormatException {
        String[] separatedInput = input.split(" ", 2);
        String command = separatedInput[0];
        int num;
        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case TODO:
        case DEADLINE:
        case EVENT:
            if (separatedInput.length == 1) {
                throw new InvalidDescriptionException();
            }
            return parseTask(command, separatedInput[1].trim());
        case DONE:
            num = Integer.parseInt(separatedInput[1]);
            return new DoneCommand(num);
        case DELETE:
            num = Integer.parseInt(separatedInput[1]);
            return new DeleteCommand(num);
        case FIND:
            String matchString = separatedInput[1].trim();
            return new FindCommand(matchString);
        case HELP:
            if (separatedInput.length == 1) {
                return new HelpCommand();
            } else {
                String targetCommand = separatedInput[1].trim().toLowerCase();
                if (commands.contains(targetCommand)) {
                    return new HelpCommand(targetCommand);
                } else {
                    throw new InvalidCommandException();
                }
            }
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Helps parse input related to adding tasks.
     *
     * @param command     The command string that was input.
     * @param description The remaining part of the command string.
     * @return AddCommand.
     * @throws InvalidEventException      If event command format is wrong.
     * @throws InvalidDeadlineException   If deadline command format is wrong.
     * @throws InvalidCommandException    If no such command exists.
     * @throws InvalidDateFormatException If date is not given in correct format.
     */
    private static Command parseTask(String command, String description)
            throws InvalidEventException, InvalidDeadlineException,
            InvalidCommandException, InvalidDateFormatException {
        String[] separatedDescription;
        LocalDate date;
        try {
            switch (command) {
            case TODO:
                return new AddCommand(new Todo(description));
            case DEADLINE:
                separatedDescription = description.split(" /by ", 2);
                if (separatedDescription.length == 1) {
                    throw new InvalidDeadlineException();
                }
                date = LocalDate.parse(separatedDescription[1]);
                return new AddCommand(new Deadline(separatedDescription[0], date));
            case EVENT:
                separatedDescription = description.split(" /at ", 2);
                if (separatedDescription.length == 1) {
                    throw new InvalidEventException();
                }
                date = LocalDate.parse(separatedDescription[1]);
                return new AddCommand(new Event(separatedDescription[0], date));
            default:
                throw new InvalidCommandException();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}
