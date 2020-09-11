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
import viscount.command.DeleteAllCommand;
import viscount.command.DeleteAllDoneCommand;
import viscount.command.DeleteCommand;
import viscount.command.DoneAllCommand;
import viscount.command.DoneCommand;
import viscount.command.EditCommand;
import viscount.command.EditDateTimeCommand;
import viscount.command.EditDescriptionAndDateTimeCommand;
import viscount.command.EditDescriptionCommand;
import viscount.command.ListCommand;
import viscount.exception.ViscountDateTimeParseException;
import viscount.exception.ViscountException;
import viscount.exception.ViscountMissingArgumentException;
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
        assert taskData.size() > 0 : "List of task data should be non-empty";

        try {
            TaskType taskType = TaskType.valueOf(taskData.get(0));
            String isDoneString = taskData.get(1);
            String taskDescription = taskData.get(2);

            boolean isValidDoneString = isDoneString.equals("0") || isDoneString.equals("1");
            boolean isValidTaskDescription = !taskDescription.isEmpty();

            if (!isValidDoneString || !isValidTaskDescription) {
                throw new IOException("Data file corrupted.");
            }

            boolean isDone = isDoneString.equals("1");

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
                /* Since all possible TaskTypes have been enumerated,
                 * and exception for incorrect task type input has been handled,
                 * program will never reach the default block of this switch statement.
                 */
                return null;
            }
        } catch (IllegalArgumentException e) {
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
        case "edit":
            return parseEditCommand(arguments);
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
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     * @throws ViscountUnsupportedOperationException If /on argument is used with todo filter.
     * @throws ViscountUnknownCommandException If task type filter is invalid.
     */
    private static ListCommand parseListCommand(List<String> arguments)
            throws ViscountMissingArgumentException, ViscountUnsupportedOperationException,
            ViscountUnknownCommandException {
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
                throw new ViscountUnsupportedOperationException("todo /on");
            } else if (dateString.isEmpty()) {
                throw new ViscountMissingArgumentException("/on parameter");
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
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     * @throws ViscountDateTimeParseException If date time input is formatted wrongly.
     * @throws ViscountUnknownCommandException If task type is invalid.
     */
    private static AddCommand parseAddCommand(List<String> arguments) throws ViscountMissingArgumentException,
            ViscountDateTimeParseException, ViscountUnknownCommandException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task type");
        }

        try {
            TaskType taskType = TaskType.valueOf(arguments.get(1).toUpperCase());

            switch (taskType) {
            case TODO:
                return parseAddTodoCommand(arguments);
            case DEADLINE:
                return parseAddDeadlineCommand(arguments);
            case EVENT:
                return parseAddEventCommand(arguments);
            default:
                /* Since all possible TaskTypes have been enumerated,
                 * and exception for incorrect task type input has been handled,
                 * program will never reach the default block of this switch statement.
                 */
                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new ViscountUnknownCommandException(arguments.get(1));
        }
    }

    /**
     * Parses an add todo command.
     *
     * @param arguments Arguments from user input.
     * @return Add todo command representing input from user.
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     */
    private static AddCommand parseAddTodoCommand(List<String> arguments) throws ViscountMissingArgumentException {
        String description = String.join(" ", arguments.subList(2, arguments.size()));

        if (description.isEmpty()) {
            throw new ViscountMissingArgumentException("todo description");
        }

        return new AddCommand(TaskType.TODO, description, null);
    }

    /**
     * Parses an add deadline command.
     *
     * @param arguments Arguments from user input.
     * @return Add deadline command representing input from user.
     * @throws ViscountDateTimeParseException If date time input is formatted wrongly.
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     */
    private static AddCommand parseAddDeadlineCommand(List<String> arguments)
            throws ViscountDateTimeParseException, ViscountMissingArgumentException {
        int dueDateIndex = arguments.indexOf("/by");

        if (dueDateIndex == -1) {
            throw new ViscountMissingArgumentException("/by argument");
        }

        String description = String.join(" ", arguments.subList(2, dueDateIndex));
        String dueDateString = String.join(" ", arguments.subList(dueDateIndex + 1, arguments.size()));

        if (description.isEmpty()) {
            throw new ViscountMissingArgumentException("deadline description");
        }

        if (dueDateString.isEmpty()) {
            throw new ViscountMissingArgumentException("/by parameter");
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
     * @throws ViscountDateTimeParseException If date time input is formatted wrongly.
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     */
    private static AddCommand parseAddEventCommand(List<String> arguments)
            throws ViscountDateTimeParseException, ViscountMissingArgumentException {
        int eventTimeIndex = arguments.indexOf("/at");

        if (eventTimeIndex == -1) {
            throw new ViscountMissingArgumentException("/at argument");
        }

        String description = String.join(" ", arguments.subList(2, eventTimeIndex));
        String eventTimeString = String.join(" ", arguments.subList(eventTimeIndex + 1, arguments.size()));

        if (description.isEmpty()) {
            throw new ViscountMissingArgumentException("event description");
        }

        if (eventTimeString.isEmpty()) {
            throw new ViscountMissingArgumentException("/at parameter");
        }

        try {
            LocalDateTime eventTime = Parser.parseDateTime(eventTimeString, INPUT_DATE_TIME_FORMATTER);
            return new AddCommand(TaskType.EVENT, description, eventTime);
        } catch (DateTimeParseException e) {
            throw new ViscountDateTimeParseException("event date");
        }
    }

    /**
     * Parses an edit command.
     *
     * @param arguments Arguments from user input.
     * @return Edit command representing input from user.
     * @throws ViscountDateTimeParseException If date time input is formatted wrongly.
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     * @throws ViscountNumberFormatException If task index input is not a number.
     */
    private static EditCommand parseEditCommand(List<String> arguments)
            throws ViscountDateTimeParseException, ViscountMissingArgumentException, ViscountNumberFormatException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task number");
        }

        int descriptionArgumentIndex = arguments.indexOf("/desc");
        int dateTimeArgumentIndex = arguments.indexOf("/date");

        if (descriptionArgumentIndex == -1 && dateTimeArgumentIndex == -1) {
            throw new ViscountMissingArgumentException("/desc or /date argument");
        }

        try {
            int taskIndex = Integer.parseInt(arguments.get(1)) - 1;

            if (descriptionArgumentIndex != -1 && dateTimeArgumentIndex != -1) {
                return parseEditDescriptionAndDateTimeCommand(
                        arguments, taskIndex, descriptionArgumentIndex, dateTimeArgumentIndex);
            } else if (descriptionArgumentIndex != -1) {
                return parseEditDescriptionCommand(arguments, taskIndex);
            } else {
                return parseEditDateTimeCommand(arguments, taskIndex);
            }
        } catch (NumberFormatException e) {
            throw new ViscountNumberFormatException(arguments.get(1));
        }
    }

    /**
     * Parses an edit description and date time command.
     *
     * @param arguments Arguments from user input.
     * @param taskIndex Index of task edited.
     * @param descriptionArgumentIndex Index of /desc argument.
     * @param dateTimeArgumentIndex Index of /date argument.
     * @return Edit description and date time command representing input from user.
     * @throws ViscountDateTimeParseException If date time input is formatted wrongly.
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     */
    private static EditCommand parseEditDescriptionAndDateTimeCommand(List<String> arguments, int taskIndex,
                                                                      int descriptionArgumentIndex,
                                                                      int dateTimeArgumentIndex)
            throws ViscountDateTimeParseException, ViscountMissingArgumentException {
        String newDescription = "";
        String newDateTimeString = "";

        if (descriptionArgumentIndex < dateTimeArgumentIndex) {
            newDescription = String.join(
                    " ", arguments.subList(descriptionArgumentIndex + 1, dateTimeArgumentIndex));
            newDateTimeString = String.join(
                    " ", arguments.subList(dateTimeArgumentIndex + 1, arguments.size()));
        } else {
            newDateTimeString = String.join(
                    " ", arguments.subList(dateTimeArgumentIndex + 1, descriptionArgumentIndex));
            newDescription = String.join(
                    " ", arguments.subList(descriptionArgumentIndex + 1, arguments.size()));
        }

        if (newDescription.isEmpty()) {
            throw new ViscountMissingArgumentException("new description");
        }

        try {
            LocalDateTime newDateTime = Parser.parseDateTime(newDateTimeString, INPUT_DATE_TIME_FORMATTER);

            EditDescriptionCommand editDescriptionCommand = new EditDescriptionCommand(taskIndex, newDescription);
            EditDateTimeCommand editDateTimeCommand = new EditDateTimeCommand(taskIndex, newDateTime);
            return new EditDescriptionAndDateTimeCommand(taskIndex, editDescriptionCommand, editDateTimeCommand);
        } catch (DateTimeParseException e) {
            throw new ViscountDateTimeParseException("new date time");
        }
    }

    /**
     * Parses an edit description command.
     *
     * @param arguments Arguments from user input.
     * @param taskIndex Index of task edited.
     * @return Edit description command representing input from user.
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     */
    private static EditCommand parseEditDescriptionCommand(List<String> arguments, int taskIndex)
        throws ViscountMissingArgumentException {
        String newDescription = String.join(" ", arguments.subList(3, arguments.size()));

        if (newDescription.isEmpty()) {
            throw new ViscountMissingArgumentException("new description");
        }

        return new EditDescriptionCommand(taskIndex, newDescription);
    }

    /**
     * Parses an edit date time command.
     * @param arguments Arguments from user input.
     * @param taskIndex Index of task edited.
     * @return Edit date time command representing input from user.
     * @throws ViscountDateTimeParseException If date time input is formatted wrongly.
     */
    private static EditCommand parseEditDateTimeCommand(List<String> arguments, int taskIndex)
            throws ViscountDateTimeParseException {
        try {
            String newDateTimeString = String.join(" ", arguments.subList(3, arguments.size()));

            LocalDateTime newDateTime = Parser.parseDateTime(newDateTimeString, INPUT_DATE_TIME_FORMATTER);
            return new EditDateTimeCommand(taskIndex, newDateTime);
        } catch (DateTimeParseException e) {
            throw new ViscountDateTimeParseException("new date time");
        }
    }

    /**
     * Parses a done command.
     *
     * @param arguments Arguments from user input.
     * @return Done command representing input from user.
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     * @throws ViscountNumberFormatException If task index input is not a number.
     */
    private static DoneCommand parseDoneCommand(List<String> arguments)
            throws ViscountMissingArgumentException, ViscountNumberFormatException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task number");
        }

        if (arguments.get(1).equals("all")) {
            return new DoneAllCommand();
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
     * @throws ViscountMissingArgumentException If input command is missing an argument.
     * @throws ViscountNumberFormatException If task index input is not a number.
     */
    private static DeleteCommand parseDeleteCommand(List<String> arguments)
            throws ViscountMissingArgumentException, ViscountNumberFormatException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task number");
        }

        if (arguments.get(1).equals("all")) {
            return new DeleteAllCommand();
        }

        if (arguments.get(1).equals("done")) {
            return new DeleteAllDoneCommand();
        }

        try {
            int taskIndex = Integer.parseInt(arguments.get(1)) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ViscountNumberFormatException(arguments.get(1));
        }
    }
}
