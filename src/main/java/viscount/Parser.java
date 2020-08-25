package viscount;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import java.util.Arrays;
import java.util.List;

import viscount.command.*;

import viscount.exception.ViscountDateTimeParseException;
import viscount.exception.ViscountException;
import viscount.exception.ViscountMissingArgumentDescriptionException;
import viscount.exception.ViscountMissingArgumentException;
import viscount.exception.ViscountMissingDescriptionException;
import viscount.exception.ViscountNumberFormatException;
import viscount.exception.ViscountUnknownCommandException;
import viscount.exception.ViscountUnsupportedOperationException;

import viscount.task.TaskType;

public class Parser {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
    public static final DateTimeFormatter TASK_DATA_DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER
            = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");


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
        case "bye":
            return new ExitCommand();
        default:
            throw new ViscountUnknownCommandException(baseCommand);
        }
    }
    
    private static ListCommand parseListCommand(List<String> arguments) throws ViscountException {
        int onArgumentIndex = arguments.indexOf("/on");
        String taskTypeModifier = "";
        String dateString = "";

        if (onArgumentIndex == -1) {
            taskTypeModifier = String.join(" ", arguments.subList(1, arguments.size()));
        } else {
            taskTypeModifier = String.join(" ", arguments.subList(1, onArgumentIndex));
            dateString = String.join(" ", arguments.subList(onArgumentIndex + 1, arguments.size()));

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

        return new ListCommand(taskTypeModifier, dateString);
    }

    private static AddCommand parseAddCommand(List<String> arguments) throws ViscountException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task type");
        }
        
        String taskTypeArgument = arguments.get(1);
        
        if (taskTypeArgument.equals("todo")) {
            String description = String.join(" ", arguments.subList(2, arguments.size()));

            if (description.isEmpty()) {
                throw new ViscountMissingDescriptionException("todo");
            }

            return new AddCommand(TaskType.Todo, description, null);
        } else if (taskTypeArgument.equals("deadline")) {
            int dueDateIndex = arguments.indexOf("/by");

            if (dueDateIndex == -1) {
                throw new ViscountMissingArgumentException("/by");
            }

            String description = String.join(" ", arguments.subList(2, dueDateIndex));
            String dueDateString = String.join(" ", arguments.subList(dueDateIndex + 1, arguments.size()));

            if (description.isEmpty()) {
                throw new ViscountMissingDescriptionException("deadline");
            } else if (dueDateString.isEmpty()) {
                throw new ViscountMissingArgumentDescriptionException("/by");
            } else {
                try {
                    LocalDateTime dueDate = Parser.parseDateTime(dueDateString, INPUT_DATE_TIME_FORMATTER);
                    return new AddCommand(TaskType.Deadline, description, dueDate);
                } catch (DateTimeParseException e) {
                    throw new ViscountDateTimeParseException("due date");
                }
            }
        } else if (taskTypeArgument.equals("event")) {
            int eventTimeIndex = arguments.indexOf("/at");

            if (eventTimeIndex == -1) {
                throw new ViscountMissingArgumentException("/at");
            }

            String description = String.join(" ", arguments.subList(2, eventTimeIndex));
            String eventTimeString = String.join(" ", arguments.subList(eventTimeIndex + 1, arguments.size()));

            if (description.isEmpty()) {
                throw new ViscountMissingDescriptionException("event");
            } else if (eventTimeString.isEmpty()) {
                throw new ViscountMissingArgumentDescriptionException("/at");
            } else {
                try {
                    LocalDateTime eventTime = Parser.parseDateTime(eventTimeString, INPUT_DATE_TIME_FORMATTER);
                    return new AddCommand(TaskType.Event, description, eventTime);
                } catch (DateTimeParseException e) {
                    throw new ViscountDateTimeParseException("event date");
                }
            }
        } else {
            throw new ViscountUnsupportedOperationException("task type " + taskTypeArgument);
        }
    }
    
    private static DoneCommand parseDoneCommand(List<String> arguments) throws ViscountException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task number");
        }

        int taskIndex = -1;

        try {
            taskIndex = Integer.parseInt(arguments.get(1)) - 1;
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ViscountNumberFormatException(arguments.get(1));
        }
    }
    private static DeleteCommand parseDeleteCommand(List<String> arguments) throws ViscountException {
        if (arguments.size() < 2) {
            throw new ViscountMissingArgumentException("task number");
        } else {
            int taskIndex = -1;

            try {
                taskIndex = Integer.parseInt(arguments.get(1)) - 1;
                return new DeleteCommand(taskIndex);
            } catch (NumberFormatException e) {
                throw new ViscountNumberFormatException(arguments.get(1));
            }
        }
    }

    //@@author sc-arecrow-reused
    //Reused from https://stackoverflow.com/a/48281350 with minor modifications
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
}
