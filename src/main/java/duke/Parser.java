package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import command.ByeCommand;
import command.Command;
import command.CommandTypes;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;
import command.UpdateCommand;
import command.ViewallCommand;
import exception.DeadlineInvalidUsageException;
import exception.EventInvalidUsageException;
import exception.InvalidUsageException;
import exception.TodoInvalidUsageException;
import exception.UnknownCommandException;
import exception.UpdateInvalidUsageException;
import exception.ViewallInvalidUsageException;
import task.Deadline;
import task.Event;

/**
 * Represents parser class to parse user commands
 */
public class Parser {

    /**
     * Main method to call to parse user commands
     *
     * @param input user commands
     * @return a {@code Command} object representing user actions
     * @throws InvalidUsageException   on malformed commands
     * @throws UnknownCommandException on unsupported command types
     */
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

        assert command != null : "command cannot be empty";

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
        case UPDATE:
            try {
                String[] options = parseInput(commands[1]);
                int taskNumber = Integer.parseInt(options[0]);
                options[1] = " " + options[1];
                int descriptionDelimiter = commands[1].indexOf(" /d ");
                int timeDelimiter = commands[1].indexOf(" /t ");
                String descRegex = "(\\s+)(/d)(\\s+)";
                String timeRegex = "(\\s+)(/t)(\\s+)";

                Optional<String> description;
                Optional<LocalDate> date;
                if (descriptionDelimiter < 0 && timeDelimiter < 0) {
                    description = Optional.empty();
                    date = Optional.empty();
                } else if (timeDelimiter < 0) {
                    String[] findDescription = commands[1].split(descRegex, 2);
                    description = Optional.of(findDescription[1]);
                    date = Optional.empty();
                } else if (descriptionDelimiter < 0) {
                    String[] findDate = commands[1].split(timeRegex, 2);
                    description = Optional.empty();
                    date = Optional.of(LocalDate.parse(findDate[1]));
                } else if (timeDelimiter < descriptionDelimiter) {
                    String[] findDate = commands[1].split(timeRegex, 2);
                    System.out.println(findDate[1]);
                    String[] findDescription = findDate[1].split(descRegex, 2);
                    description = Optional.of(findDescription[1]);
                    date = Optional.of(LocalDate.parse(findDescription[0]));
                } else {
                    String[] findDescription = commands[1].split(descRegex, 2);
                    System.out.println(findDescription[1]);
                    String[] findDate = findDescription[1].split(timeRegex, 2);
                    description = Optional.of(findDate[0]);
                    date = Optional.of(LocalDate.parse(findDate[1]));
                }
                return new UpdateCommand(taskNumber, description, date);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new UpdateInvalidUsageException("Please re-check your command format!");
            } catch (NumberFormatException ex) {
                throw new UpdateInvalidUsageException("Don't forget to key in the task number to be updated!");
            } catch (DateTimeException ex) {
                throw new UpdateInvalidUsageException("Date should be in yyyy-mm-dd format.");
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
