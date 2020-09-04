package viscount;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;

import viscount.command.AddCommand;
import viscount.command.Command;
import viscount.command.DeleteCommand;
import viscount.command.DoneCommand;
import viscount.command.ListCommand;
import viscount.exception.ViscountDateTimeParseException;
import viscount.exception.ViscountException;
import viscount.exception.ViscountMissingArgumentDescriptionException;
import viscount.exception.ViscountMissingArgumentException;
import viscount.exception.ViscountMissingDescriptionException;
import viscount.exception.ViscountNumberFormatException;
import viscount.exception.ViscountUnknownCommandException;
import viscount.exception.ViscountUnsupportedOperationException;
import viscount.task.Deadline;
import viscount.task.Event;
import viscount.task.Task;
import viscount.task.TaskType;
import viscount.task.Todo;

/**
 * Represent's Viscount's parser.
 *
 * Handles making sense of the user's input commands.
 */
public class Parser {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
    public static final DateTimeFormatter TASK_DATA_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    //@@author sc-arecrow-reused
    //Reused from https://stackoverflow.com/a/48281350 with minor modifications

    /**
     * Parses a String representing a date and time using the given formatter.
     *
     * @param dateTimeString Date and time string parsed.
     * @param formatter Formatter used.
     * @return LocalDateTime object representing the date and time in the String.
     * @throws DateTimeParseException If string parsed is formatted wrongly.
     */
    public static LocalDateTime parseDateTime(String dateTimeString, DateTimeFormatter formatter)
            throws DateTimeParseException {
        LocalDateTime dateTime;

        TemporalAccessor ta = formatter.parseBest(dateTimeString, LocalDateTime::from, LocalDate::from);

        if (ta instanceof LocalDateTime) {
            dateTime = (LocalDateTime) ta;
        } else {
            dateTime = ((LocalDate) ta).atStartOfDay();
        }

        return dateTime;
    }
    //@@author

    /**
     * Parses a String representing raw task data.
     *
     * @param rawData Raw task data parsed.
     * @return Task represented by the raw task data.
     */
    public static Task parseTaskData(String rawData) throws IOException {
        List<String> taskData = Arrays.asList(rawData.split("\\|"));

        TaskType taskType = TaskType.valueOf(taskData.get(0));
        boolean isDone = !taskData.get(1).equals("0");
        String taskDescription = taskData.get(2);

        switch (taskType) {
        case TODO:
            return new Todo(taskDescription, isDone);
        case DEADLINE:
            LocalDateTime dueDate = Parser.parseDateTime(
                    taskData.get(3), Parser.TASK_DATA_DATE_TIME_FORMATTER);
            return new Deadline(taskDescription, isDone, dueDate);
        case EVENT:
            LocalDateTime eventTime = Parser.parseDateTime(
                    taskData.get(3), Parser.TASK_DATA_DATE_TIME_FORMATTER);
            return new Event(taskDescription, isDone, eventTime);
        default:
            throw new IOException("Data file corrupted.");
        }
    }

    /**
     * Parses a raw command.
     *
     * @param rawCommand Raw command in String form parsed.
     * @return Command object representing the raw command.
     * @throws ViscountException If command is unsupported or used wrongly.
     */
    public static Command parse(String rawCommand) throws ViscountException {
        List<String> arguments = Arrays.asList(rawCommand.split(" "));
        String baseCommand = arguments.get(0);

        switch (baseCommand) {
        case "list":
            return parseListCommand(arguments);
        case "add":
            return parseAddCommand(arguments);
        case "done":
            return parseDoneCommand(arguments);
        case "delete":
            return parseDeleteCommand(arguments);
        default:
            throw new ViscountUnknownCommandException(baseCommand);
        }
    }

    /**
     * Parses a list command.
     *
     * @param arguments Arguments from user input.
     * @return List command representing input from user.
     * @throws ViscountException If command contains unknown arguments or was used wrongly.
     */
    private static ListCommand parseListCommand(List<String> arguments) throws ViscountException {
        int onArgumentIndex = arguments.indexOf("/on");
        int findArgumentIndex = arguments.indexOf("/find");
        String taskTypeModifier = "";
        String dateString = "";
        String findString = "";

        if (onArgumentIndex == -1) {
            if (findArgumentIndex == -1) {
                taskTypeModifier = String.join(" ", arguments.subList(1, arguments.size()));
            } else {
                taskTypeModifier = String.join(" ", arguments.subList(1, findArgumentIndex));
                findString = String.join(" ", arguments.subList(findArgumentIndex + 1, arguments.size()));
            }
        } else {
            if (findArgumentIndex == -1) {
                taskTypeModifier = String.join(" ", arguments.subList(1, onArgumentIndex));
                dateString = String.join(" ", arguments.subList(onArgumentIndex + 1, arguments.size()));
            } else if (onArgumentIndex < findArgumentIndex) {
                taskTypeModifier = String.join(" ", arguments.subList(1, onArgumentIndex));
                dateString = String.join(" ", arguments.subList(onArgumentIndex + 1, findArgumentIndex));
                findString = String.join(" ", arguments.subList(findArgumentIndex + 1, arguments.size()));
            } else {
                taskTypeModifier = String.join(" ", arguments.subList(1, findArgumentIndex));
                findString = String.join(" ", arguments.subList(findArgumentIndex + 1, onArgumentIndex));
                dateString = String.join(" ", arguments.subList(onArgumentIndex + 1, arguments.size()));
            }

            if (taskTypeModifier.equals("todo")) {
                throw new ViscountUnsupportedOperationException("/on");
            } else if (dateString.isEmpty()) {
                throw new ViscountMissingArgumentDescriptionException("/on");
            }
        }

        if (!taskTypeModifier.isEmpty()) {
            try {
                TaskType.valueOf(taskTypeModifier.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ViscountUnknownCommandException(taskTypeModifier);
            }
        }

        return new ListCommand(taskTypeModifier, dateString, findString);
    }

    /**
     * Parses an add command.
     *
     * @param arguments Arguments from user input.
     * @return Add command representing input from user.
     * @throws ViscountException If command contains unknown arguments or was used wrongly.
     */
    private static AddCommand parseAddCommand(List<String> arguments) throws ViscountException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task type");
        }

        TaskType taskType = TaskType.valueOf(arguments.get(1).toUpperCase());

        switch (taskType) {
        case TODO:
            return parseAddTodoCommand(arguments);
        case DEADLINE:
            return parseAddDeadlineCommand(arguments);
        case EVENT:
            return parseAddEventCommand(arguments);
        default:
            throw new ViscountUnsupportedOperationException("task type " + taskType.name());
        }
    }

    /**
     * Parses an add todo command.
     *
     * @param arguments Arguments from user input.
     * @return Add todo command representing input from user.
     * @throws ViscountException If command contains unknown arguments or was used wrongly.
     */
    private static AddCommand parseAddTodoCommand(List<String> arguments) throws ViscountException {
        String description = String.join(" ", arguments.subList(2, arguments.size()));

        if (description.isEmpty()) {
            throw new ViscountMissingDescriptionException("todo");
        }

        return new AddCommand(TaskType.TODO, description, null);
    }

    /**
     * Parses an add deadline command.
     *
     * @param arguments Arguments from user input.
     * @return Add deadline command representing input from user.
     * @throws ViscountException If command contains unknown arguments or was used wrongly.
     */
    private static AddCommand parseAddDeadlineCommand(List<String> arguments) throws ViscountException {
        int dueDateIndex = arguments.indexOf("/by");

        if (dueDateIndex == -1) {
            throw new ViscountMissingArgumentException("/by");
        }

        String description = String.join(" ", arguments.subList(2, dueDateIndex));
        String dueDateString = String.join(" ", arguments.subList(dueDateIndex + 1, arguments.size()));

        if (description.isEmpty()) {
            throw new ViscountMissingDescriptionException("deadline");
        }

        if (dueDateString.isEmpty()) {
            throw new ViscountMissingArgumentDescriptionException("/by");
        }

        try {
            LocalDateTime dueDate = Parser.parseDateTime(dueDateString, INPUT_DATE_TIME_FORMATTER);
            return new AddCommand(TaskType.DEADLINE, description, dueDate);
        } catch (DateTimeParseException e) {
            throw new ViscountDateTimeParseException("due date");
        }
    }

    /**
     * Parses an add event command.
     *
     * @param arguments Arguments from user input.
     * @return Add event command representing input from user.
     * @throws ViscountException If command contains unknown arguments or was used wrongly.
     */
    private static AddCommand parseAddEventCommand(List<String> arguments) throws ViscountException {
        int eventTimeIndex = arguments.indexOf("/at");

        if (eventTimeIndex == -1) {
            throw new ViscountMissingArgumentException("/at");
        }

        String description = String.join(" ", arguments.subList(2, eventTimeIndex));
        String eventTimeString = String.join(" ", arguments.subList(eventTimeIndex + 1, arguments.size()));

        if (description.isEmpty()) {
            throw new ViscountMissingDescriptionException("event");
        }

        if (eventTimeString.isEmpty()) {
            throw new ViscountMissingArgumentDescriptionException("/at");
        }

        try {
            LocalDateTime eventTime = Parser.parseDateTime(eventTimeString, INPUT_DATE_TIME_FORMATTER);
            return new AddCommand(TaskType.EVENT, description, eventTime);
        } catch (DateTimeParseException e) {
            throw new ViscountDateTimeParseException("event date");
        }
    }

    /**
     * Parses a done command.
     *
     * @param arguments Arguments from user input.
     * @return Done command representing input from user.
     * @throws ViscountException If command contains unknown arguments or was used wrongly.
     */
    private static DoneCommand parseDoneCommand(List<String> arguments) throws ViscountException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task number");
        }

        try {
            int taskIndex = Integer.parseInt(arguments.get(1)) - 1;
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ViscountNumberFormatException(arguments.get(1));
        }
    }

    /**
     * Parses a delete command.
     *
     * @param arguments Arguments from user input.
     * @return Delete command representing input from user.
     * @throws ViscountException If command contains unknown arguments or was used wrongly.
     */
    private static DeleteCommand parseDeleteCommand(List<String> arguments) throws ViscountException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task number");
        }

        try {
            int taskIndex = Integer.parseInt(arguments.get(1)) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ViscountNumberFormatException(arguments.get(1));
        }
    }
}
