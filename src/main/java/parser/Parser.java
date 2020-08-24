package parser;

import commands.Command;
import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.DoneCommand;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.IncorrectCommand;
import utils.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_TODO_FORMAT = Pattern.compile("(?<description>.*)");
    public static final Pattern ADD_DEADLINE_FORMAT =
            Pattern.compile("(?<description>[^/]+)" +
                    "/by(?<deadline>(?<day>[1-31])/(?<month>[1-12])/(?<year>[1-9999]{4}) " +
                    "(?<hour>[0-23]{2})(?<minutes>[0-59]{2}))");
    public static final Pattern ADD_EVENT_FORMAT =
            Pattern.compile("(?<description>[^/]+)" +
                    "/at(?<dateTime>(?<day>[1-31])/(?<month>[1-12])/(?<year>[1-9999]{4}) " +
                    "(?<hour>[0-23]{2})(?<minutes>[0-59]{2}))");

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>[0-9]+)");

    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {

            case AddTodoCommand.COMMAND_WORD:
                return prepareAddTodo(arguments);

            case AddDeadlineCommand.COMMAND_WORD:
                return prepareAddDeadline(arguments);

            case AddEventCommand.COMMAND_WORD:
                return prepareAddEvent(arguments);

            case DoneCommand.COMMAND_WORD:
                return prepareDone(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD: // Fallthrough

            default:
                return new HelpCommand();
        }
    }

    private Command prepareAddTodo(String args) {
        final Matcher matcher = ADD_TODO_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTodoCommand.MESSAGE_USAGE));
        }
        return new AddTodoCommand(matcher.group("description"));
    }

    private Command prepareAddDeadline(String args) {
        final Matcher matcher = ADD_DEADLINE_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDeadlineCommand.MESSAGE_USAGE));
        }
        return new AddDeadlineCommand(matcher.group("description"),
                getLocalDateTime(matcher.group("deadline").trim()));
    }

    private Command prepareAddEvent(String args) {
        final Matcher matcher = ADD_EVENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEventCommand.MESSAGE_USAGE));
        }
        return new AddEventCommand(matcher.group("description"),
                getLocalDateTime(matcher.group("dateTime").trim()));
    }

    private LocalDateTime getLocalDateTime(String dateTimeStr) {
        String newDateTimeStr = dateTimeStr.replace('/','-');
        if (dateTimeStr.length() < 11) { // no timing specified
            newDateTimeStr += " 23:59";
        } else {
            StringBuilder sb = new StringBuilder(newDateTimeStr);
            sb.insert(newDateTimeStr.length() - 2, ":");
            newDateTimeStr = sb.toString();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(newDateTimeStr, formatter);
    }

    private Command prepareDone(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args.trim());
            return new DoneCommand(targetIndex);
        } catch (ParseException e) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args.trim());
            return new DeleteCommand(targetIndex);
        } catch (ParseException e) {
            return new IncorrectCommand(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse.");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }
}
