package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandInstruction;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ViewCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with making sense of user inputs.
 */
public class Parser {
    /** List of all the valid date inputs */
    private static final List<String> DATE_FORMATS = Arrays.asList("d/M/y", "y-M-d");

    /**
     * Parses the input command from the user into a command that the chat bot can understand.
     *
     * @param fullCommand The command from the user.
     * @return The command that can be interpreted from the user.
     * @throws DukeException If there was some problems with understanding the user's commands.
     */
    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] splitCommand = fullCommand.trim().split(" ", 2);
            CommandInstruction instruction = parseCommandInstruction(splitCommand[0]);
            switch (instruction) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case DONE:
                int taskNumberDone = Integer.parseInt(splitCommand[1]);
                return new DoneCommand(taskNumberDone);
            case DELETE:
                int taskNumberDelete = Integer.parseInt(splitCommand[1]);
                return new DeleteCommand(taskNumberDelete);
            case TODO:
                Todo todo = new Todo(splitCommand[1]);
                return new AddCommand(todo);
            case DEADLINE:
                String[] splitDeadline = splitCommand[1].split(" /by ", 2);
                String deadlineDescription = splitDeadline[0];
                LocalDate deadlineDate = parseDate(splitDeadline[1]);
                Deadline deadline = new Deadline(deadlineDescription, deadlineDate);
                return new AddCommand(deadline);
            case EVENT:
                String[] splitEvent = splitCommand[1].split(" /at ", 2);
                String eventDescription = splitEvent[0];
                LocalDate eventDate = parseDate(splitEvent[1]);
                Event event = new Event(eventDescription, eventDate);
                return new AddCommand(event);
            case VIEW:
                LocalDate viewDate = parseDate(splitCommand[1]);
                return new ViewCommand(viewDate);
            case FIND:
                return new FindCommand(splitCommand[1]);
            default:
                assert false : instruction;
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The task number is not valid.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description or date cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The date is not valid.");
        }
    }

    /**
     * Parses input dates in the valid date formats of d/M/y and y-M-d into the local date format.
     *
     * @param string The date to be parsed.
     * @return Local date format of the input date.
     * @throws DateTimeParseException If the input date is not of an acceptable format.
     */
    public static LocalDate parseDate(String string) throws DateTimeParseException {
        for (int i = 0; i < DATE_FORMATS.size(); i++) {
            String formatString = DATE_FORMATS.get(i);
            try {
                return LocalDate.parse(string, DateTimeFormatter.ofPattern(formatString));
            } catch (DateTimeParseException e) {
                if (i == DATE_FORMATS.size() - 1) {
                    throw e;
                }
            }
        }
        return null;
    }

    /**
     * Parses the input command instruction from the user to a command instruction this chat bot can understand.
     *
     * @param userInstruction The command instruction in string from the user.
     * @return Command instruction this chat bot can understand.
     */
    private static CommandInstruction parseCommandInstruction(String userInstruction) {
        return CommandInstruction.valueOf(userInstruction.toUpperCase());
    }
}
