package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Class for parsing user commands.
 */
public class Parser {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String FIND_COMMAND = "find";
    private static final String DELETE_COMMAND = "delete";
    private static final String EXIT_COMMAND = "bye";
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses user command as a String and returns the corresponding UserCommandType.
     * @param command String of user command
     * @return UserCommandType
     * @throws InvalidCommandException if user command is not recognised
     */
    public static UserCommandType parseUserCommand(String command) throws InvalidCommandException {
        assert command != null : "Command is null";
        if (command.equals(LIST_COMMAND)) {
            return UserCommandType.LIST;
        } else if (command.equals(EXIT_COMMAND)) {
            return UserCommandType.EXIT;
        } else if (command.startsWith(DONE_COMMAND)) {
            return UserCommandType.DONE;
        } else if (command.startsWith(DELETE_COMMAND)) {
            return UserCommandType.DELETE;
        } else if (command.startsWith(TODO_COMMAND)) {
            return UserCommandType.TODO;
        } else if (command.startsWith(DEADLINE_COMMAND)) {
            return UserCommandType.DEADLINE;
        } else if (command.startsWith(EVENT_COMMAND)) {
            return UserCommandType.EVENT;
        } else if (command.startsWith(FIND_COMMAND)) {
            return UserCommandType.FIND;
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses a task command and return the String components.
     * @param command user command String
     * @return String array of the command components as Task name, dateTimeString
     * @throws InvalidCommandException if command syntax is unrecognised
     */
    public static String[] parseTask(String command) throws InvalidCommandException {
        assert command != null : "Command is null";
        String[] components = command.split(" ");
        String taskType = components[0];

        if (taskType.equals(TODO_COMMAND)) {
            if (components.length < 2) {
                throw new InvalidCommandException("The description of a todo cannot be empty.");
            }
            return new String[]{
                String.join(" ", Arrays.copyOfRange(components, 1, components.length))
            };
        } else if (taskType.equals(DEADLINE_COMMAND) || taskType.equals(EVENT_COMMAND)) {
            for (int i = 0; i < components.length - 1; i++) {
                if (components[i].equals("/at") || components[i].equals("/by")) {
                    String dateTimeString = i + 2 == components.length
                            ? components[i + 1] + " 0000"
                            : String.join(" ",
                            Arrays.copyOfRange(components, i + 1, components.length));
                    // Task name, Datetime
                    return new String[]{
                        String.join(" ", Arrays.copyOfRange(components, 1, i)),
                        dateTimeString
                    };
                }
            }
            throw new InvalidCommandException("No date provided");
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses datetime String and return LocalDateTime.
     * @param dateString datetime String
     * @return LocalDateTime
     * @throws InvalidCommandException if cannot parse dateString
     */
    public static LocalDateTime parseDateTime(String dateString) throws InvalidCommandException {
        assert dateString != null : "dateString is null";
        try {
            return LocalDateTime.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException exception) {
            throw new InvalidCommandException("Invalid date time format, "
                    + "please specify your date in dd/MM/yyyy HHmm format");
        }
    }

    /**
     * Gets Task index of done user command.
     * @param command User command String
     * @return task index
     * @throws InvalidCommandException if command syntax is invalid
     */
    public static int getTaskIndex(String command) throws InvalidCommandException {
        assert command != null : "Command is null";
        String[] components = command.split(" ");
        if (components.length != 2) {
            throw new InvalidCommandException("Done command should have 2 components");
        }
        return Integer.parseInt(components[1]) - 1;
    }

    /**
     * Gets Keyword String from find command.
     * @param command user command
     * @return keyword String
     * @throws InvalidCommandException if keyword string not provided
     */
    public static String getFindKeyWordString(String command) throws InvalidCommandException {
        assert command != null : "Command is null";
        String[] components = command.split(" ");
        if (components.length < 2) {
            throw new InvalidCommandException("Done command should have 2 components");
        }
        return String.join(" ", Arrays.copyOfRange(components, 1, components.length));
    }

    /**
     * An exception for invalid user command.
     */
    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String errorMessage) {
            super(errorMessage);
        }

        public InvalidCommandException() {
            super("Unrecognised command");
        }
    }
}
