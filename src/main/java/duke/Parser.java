package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandTypes;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.ViewallCommand;
import duke.task.Deadline;
import duke.task.Event;
import exception.DeadlineInvalidUsageException;
import exception.EventInvalidUsageException;
import exception.InvalidUsageException;
import exception.TodoInvalidUsageException;
import exception.UnknownCommandException;
import exception.ViewallInvalidUsageException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseCommand(String input) throws InvalidUsageException, UnknownCommandException {
        String[] commands = parseInput(input);
        CommandTypes command;

        try {
            // modify parsed[0] to uppercase to ensure that comparison with the commands enum is standardized
            command = CommandTypes.valueOf(commands[0].toUpperCase());
        } catch (IllegalArgumentException ex) {
            // if parsed[0] not amongst valid commands, will throw an IllegalArgumentException
            throw new UnknownCommandException();
        }

        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            // we ignore the argument after `list`.
            return new ListCommand();
        case DONE:
            try {
                int taskNumber = Integer.parseInt(commands[1]) - 1;
                return new DoneCommand(taskNumber);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                throw new InvalidUsageException("Usage: done <task number>");
            }
        case DELETE:
            try {
                int taskNumber = Integer.parseInt(commands[1]) - 1;
                return new DeleteCommand(taskNumber);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                throw new InvalidUsageException("Usage: delete <task number>");
            }
        case TODO:
            try {
                return new TodoCommand(commands[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new TodoInvalidUsageException("Todo description cannot be empty.");
            }
        case DEADLINE:
            try {
                String[] parsedDeadline = commands[1].split("\\s*/by\\s*", 2);

                if (parsedDeadline.length < 2) {
                    throw new DeadlineInvalidUsageException("Please re-check your command format!");
                }
                if (parsedDeadline[0].equals("")) {
                    throw new DeadlineInvalidUsageException("Deadline description cannot be empty.");
                }

                try {
                    Deadline deadline = Deadline.create(parsedDeadline[0], parsedDeadline[1]);
                    return new DeadlineCommand(deadline);
                } catch (DateTimeParseException ex) {
                    throw new DeadlineInvalidUsageException("Deadline date must be of the form yyyy-mm-dd.");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new DeadlineInvalidUsageException("Deadline description cannot be empty.");
            }
        case EVENT:
            try {
                String[] parsedEvent = commands[1].split("\\s*/at\\s*", 2);

                if (parsedEvent.length < 2) {
                    throw new EventInvalidUsageException("Please re-check your command format!");
                }
                if (parsedEvent[0].equals("")) {
                    throw new EventInvalidUsageException("Event description cannot be empty.");
                }

                try {
                    Event event = Event.create(parsedEvent[0], parsedEvent[1]);
                    return new EventCommand(event);
                } catch (DateTimeParseException ex) {
                    throw new EventInvalidUsageException("Deadline date must be of the form yyyy-mm-dd.");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new EventInvalidUsageException("Event description cannot be empty.");
            }
        case VIEWALL:
            try {
                LocalDate date = LocalDate.parse(commands[1]);
                return new ViewallCommand(date);
            } catch (ArrayIndexOutOfBoundsException | DateTimeException ex) {
                throw new ViewallInvalidUsageException("Date should be in yyyy-mm-dd format.");
            }
        case FIND:
            try {
                return new FindCommand(commands[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new InvalidUsageException("Usage: find <keyword>");
            }
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Split the input string into first word (command) and others
     *
     * @param input user input
     * @return an array, first element is command, second element is input
     */
    private static String[] parseInput(String input) {
        return input.split("\\s+", 2);
    }
}
