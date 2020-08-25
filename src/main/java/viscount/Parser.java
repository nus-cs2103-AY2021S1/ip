package viscount;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
    public static final DateTimeFormatter TASK_DATA_DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");


    public static Command parse(String rawCommand) throws ViscountException {
        List<String> arguments = Arrays.asList(rawCommand.split(" "));
        String baseCommand = arguments.get(0);
        
        if (baseCommand.equals("list")) {
            int onArgumentIndex = arguments.indexOf("/on");
            String modifier = "";
            String dateString = "";

            if (onArgumentIndex == -1) {
                modifier = String.join(" ", arguments.subList(1, arguments.size()));
            } else {
                modifier = String.join(" ", arguments.subList(1, onArgumentIndex));
                dateString = String.join(" ", arguments.subList(onArgumentIndex + 1, arguments.size()));

                if (modifier.equals("todo")) {
                    throw new ViscountUnsupportedOperationException("/on");
                } else if (dateString.isEmpty()) {
                    throw new ViscountMissingArgumentDescriptionException("/on");
                }
            }

            if (!modifier.isEmpty()) {
                try {
                    TaskType.valueOf(modifier.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new ViscountUnknownCommandException(modifier);
                }
            }
            
            return new ListCommand(modifier, dateString);
        } else if (baseCommand.equals("todo")) {
            String description = String.join(" ", arguments.subList(1, arguments.size()));

            if (description.isEmpty()) {
                throw new ViscountMissingDescriptionException("todo");
            }
            
            return new AddCommand(TaskType.TODO, description, null);
        } else if (baseCommand.equals("deadline")) {
            int dueDateIndex = arguments.indexOf("/by");

            if (dueDateIndex == -1) {
                throw new ViscountMissingArgumentException("/by");
            }
            
            String description = String.join(" ", arguments.subList(1, dueDateIndex));
            String dueDateString = String.join(" ", arguments.subList(dueDateIndex + 1, arguments.size()));

            if (description.isEmpty()) {
                throw new ViscountMissingDescriptionException("deadline");
            } else if (dueDateString.isEmpty()) {
                throw new ViscountMissingArgumentDescriptionException("/by");
            } else {
                try {
                    LocalDateTime dueDate = Parser.parseDateTime(dueDateString, INPUT_DATE_TIME_FORMATTER);
                    return new AddCommand(TaskType.DEADLINE, description, dueDate);
                } catch (DateTimeParseException e) {
                    throw new ViscountDateTimeParseException("due date");
                }
            }
        } else if (baseCommand.equals("event")) {
            int eventTimeIndex = arguments.indexOf("/at");

            if (eventTimeIndex == -1) {
                throw new ViscountMissingArgumentException("/at");
            }
            
            String description = String.join(" ", arguments.subList(1, eventTimeIndex));
            String eventTimeString = String.join(" ", arguments.subList(eventTimeIndex + 1, arguments.size()));

            if (description.isEmpty()) {
                throw new ViscountMissingDescriptionException("event");
            } else if (eventTimeString.isEmpty()) {
                throw new ViscountMissingArgumentDescriptionException("/at");
            } else {
                try {
                    LocalDateTime eventTime = Parser.parseDateTime(eventTimeString, INPUT_DATE_TIME_FORMATTER);
                    return new AddCommand(TaskType.EVENT, description, eventTime);
                } catch (DateTimeParseException e) {
                    throw new ViscountDateTimeParseException("event date");
                }
            }
        } else if (baseCommand.equals("done")) {
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
        } else if (baseCommand.equals("delete")) {
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
        } else if (baseCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new ViscountUnknownCommandException(baseCommand);
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
