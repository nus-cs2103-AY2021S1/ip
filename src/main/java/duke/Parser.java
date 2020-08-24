package duke;

import duke.command.Command;
import duke.command.CommandInstruction;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.ViewCommand;
import duke.command.FindCommand;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    
    /** List of all the valid date inputs. */
    private static final List<String> formatStrings = Arrays.asList("d/M/y", "y-M-d");

    /**
     * Parses the input command from the user into a command that the Chatbot can understand.
     * @param fullCommand The command from the user.
     * @return The command that can be interpreted from the user.
     * @throws DukeException If there was some problems with understanding the user's commands.
     */
    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] splitCommand = fullCommand.trim().split(" ", 2);
            CommandInstruction instruction = CommandInstruction.valueOf(splitCommand[0].toUpperCase());
            switch (instruction) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case DONE:
                return new DoneCommand(Integer.parseInt(splitCommand[1]));
            case DELETE:
                return new DeleteCommand(Integer.parseInt(splitCommand[1]));
            case TODO:
                Todo todo = new Todo(splitCommand[1]);
                return new AddCommand(todo);
            case DEADLINE:
                String[] splitDeadline = splitCommand[1].split(" /by ", 2);
                Deadline deadline = new Deadline(splitDeadline[0], parseDate(splitDeadline[1]));
                return new AddCommand(deadline);
            case EVENT:
                String[] splitEvent = splitCommand[1].split(" /at ", 2);
                Event event = new Event(splitEvent[0], parseDate(splitEvent[1]));
                return new AddCommand(event);
            case VIEW:
                return new ViewCommand(splitCommand[1]);
            case FIND:
                return new FindCommand(splitCommand[1]);
            default:
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The task number is not valid.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description or date cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The date is not valid.");
        }
    }


    /**
     * Parses input dates in the correct format into the local date format.
     * @param string The date to be parsed.
     * @return Local date format of the input date.
     * @throws DateTimeParseException If the input date is not of an acceptable format.
     */
    public static LocalDate parseDate(String string) throws DateTimeParseException {
        for (int i = 0; i < formatStrings.size(); i++) {
            String formatString = formatStrings.get(i);
            try {
                return LocalDate.parse(string, DateTimeFormatter.ofPattern(formatString));
            } catch (DateTimeParseException e) {
                if (i == formatStrings.size() - 1) {
                    throw e;
                }
            }
        }
        return null;
    }
}
