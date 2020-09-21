package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidRequestException;
import duke.exception.InvalidTodoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a Parser class and consists of methods related to parsing user commands.
 */
public class Parser {
    private static final String EMPTY_DELETE_MESSAGE = "Please tell me which task you want "
            + "to delete!";
    private static final String EMPTY_DONE_MESSAGE = "Please tell me which task you want "
            + "to be marked as done.";
    private static final String EMPTY_FIND_MESSAGE = "Please tell me the word you want "
            + "to find!";
    private static final String EMPTY_INPUT_MESSAGE = "I don't know what you need "
            + "me to do since the command is empty!";
    private static final String EMPTY_TODO_NAME_MESSAGE = "Please tell me the name "
            + "of the todo task! Or else I cannot add it into the list.";
    private static final String EXCESSIVE_DELETE_MESSAGE = "I can only handle one request to "
            + "delete a task at once! Please check your command.";
    private static final String EXCESSIVE_DONE_MESSAGE = "I can only handle one request to "
            + "mark a task as DONE once! Please check your command.";
    private static final String EXCESSIVE_FIND_MESSAGE = "I can only handle one keyword at a time.";
    private static final String INSUFFICIENT_DEADLINE_INFO_MESSAGE = "Please tell me the name "
            + "and the time due of the deadline task! Or else I "
            + "cannot add it into the list.";
    private static final String INSUFFICIENT_EVENT_INFO_MESSAGE = "Please tell me the name and time period"
            + " of the event task! Or else I cannot add it into the list.";
    private static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Please tell me both the name and"
            + " the time due of the deadline task in the correct form! "
            + "Don't forget to include the time by using /by.";
    private static final String INVALID_DEADLINE_TIME_MESSAGE = "Sorry, I cannot process the command"
            + " since the time due of the deadline task is not in the correct form! "
            + "Please input the time in the form of yyyy-mm-dd.";
    private static final String INVALID_DELETE_INDEX_MESSAGE = "You should enter a valid index "
            + "after the first word 'delete'! The correct form is 'delete INDEX'."
            + "Please try again.";
    private static final String INVALID_DONE_INDEX_MESSAGE = "You should enter a valid index "
            + "after the first word 'done'! The correct form is 'done INDEX'."
            + "Please try again.";
    private static final String INVALID_EVENT_FORMAT_MESSAGE = "Please tell me both the name and "
            + "the time period of the event task in the correct form! "
            + "Don't forget to include the time by using /at.";
    private static final String INVALID_EVENT_TIME_MESSAGE = "Sorry, I cannot process the command"
            + " since the time of the event task is not in the correct form! "
            + "Please input the time in the form of yyyy-mm-dd.";
    private static final String INVALID_INPUT_MESSAGE = "I cannot understand your command! "
            + "Please ensure your command follows the rules.";
    private static final String TIME_FORMAT = "yyyy-MM-dd";

    /**
     * Returns a command object by parsing the user command.
     *
     * @param userCommand The user command.
     * @return The command for Duke to execute.
     */
    public static Command parse(String userCommand) {
        try {
            String[] wordArray = userCommand.split(" ");
            int noOfWords = wordArray.length;
            if (noOfWords == 0) {
                throw new InvalidInputException(EMPTY_INPUT_MESSAGE);
            }
            if (userCommand.equals("bye")) {
                return new ExitCommand();
            }
            if (userCommand.equals("list")) {
                return new ListCommand();
            }
            if (userCommand.equals("help")) {
                return new HelpCommand();
            }
            switch (wordArray[0]) {
            case "done":
                return parseDone(wordArray);
            case "delete":
                return parseDeletion(wordArray);
            case "find":
                return parseFind(wordArray);
            case "todo":
                return parseTodo(userCommand);
            case "deadline":
                return parseDeadline(userCommand);
            case "event":
                return parseEvent(userCommand);
            default:
                throw new InvalidInputException(INVALID_INPUT_MESSAGE);
            }
        } catch (InvalidInputException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a user command that starts with "done."
     *
     * @param wordArray The word Array of user command.
     * @return The command object that Duke will execute.
     */
    public static Command parseDone(String[] wordArray) {
        int noOfWords = wordArray.length;
        try {
            if (noOfWords == 1) {
                throw new InvalidRequestException(EMPTY_DONE_MESSAGE);
            }
            if (noOfWords > 2) {
                throw new InvalidRequestException(EXCESSIVE_DONE_MESSAGE);
            }
            try {
                Integer index = Integer.parseInt(wordArray[1]);
                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                return new ErrorCommand(INVALID_DONE_INDEX_MESSAGE);
            }
        } catch (InvalidInputException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a user command that starts with "delete."
     *
     * @param wordArray The word Array of user command.
     * @return The command object that Duke will execute.
     */
    public static Command parseDeletion(String[] wordArray) {
        int noOfWords = wordArray.length;
        try {
            if (noOfWords == 1) {
                throw new InvalidRequestException(EMPTY_DELETE_MESSAGE);
            }
            if (noOfWords > 2) {
                throw new InvalidRequestException(EXCESSIVE_DELETE_MESSAGE);
            }
            try {
                Integer index = Integer.parseInt(wordArray[1]);
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                return new ErrorCommand(INVALID_DELETE_INDEX_MESSAGE);
            }
        } catch (InvalidInputException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a user command that starts with "find."
     *
     * @param wordArray The word Array of user command.
     * @return The command object that Duke will execute.
     */
    public static Command parseFind(String[] wordArray) {
        int noOfWords = wordArray.length;
        try {
            if (noOfWords == 1) {
                throw new InvalidRequestException(EMPTY_FIND_MESSAGE);
            }
            if (noOfWords > 2) {
                throw new InvalidRequestException(EXCESSIVE_FIND_MESSAGE);
            }
            return new FindCommand(wordArray[1]);
        } catch (InvalidInputException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a user command that starts with "todo."
     *
     * @param userCommand The string of user command.
     * @return The command object that Duke will execute.
     */
    public static Command parseTodo(String userCommand) {
        String[] wordArray = userCommand.split(" ");
        int noOfWords = wordArray.length;
        try {
            if (noOfWords == 1) {
                throw new InvalidTodoException(EMPTY_TODO_NAME_MESSAGE);
            }
            String taskName = userCommand.split(" ", 2)[1];
            Task newTask = new Todo(taskName);
            return new AddCommand(newTask);
        } catch (InvalidInputException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a user command that starts with "deadline."
     *
     * @param userCommand The string of user command.
     * @return The command object that Duke will execute.
     */
    public static Command parseDeadline(String userCommand) {
        String[] wordArray = userCommand.split(" ");
        int noOfWords = wordArray.length;
        try {
            if (noOfWords == 1) {
                throw new InvalidDeadlineException(INSUFFICIENT_DEADLINE_INFO_MESSAGE);
            }
            String body = userCommand.split(" ", 2)[1];
            if (body.split(" /by ").length < 2) {
                throw new InvalidDeadlineException(INVALID_DEADLINE_FORMAT_MESSAGE);
            }
            String taskName = body.split(" /by ")[0];
            String time = body.split(" /by ")[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
            try {
                LocalDate localDate = LocalDate.parse(time, formatter);
                String parsedTime = localDate.format(formatter);
                if (!parsedTime.equals(time)) {
                    throw new InvalidDeadlineException(INVALID_DEADLINE_TIME_MESSAGE);
                }
            } catch (DateTimeParseException e) {
                return new ErrorCommand(INVALID_DEADLINE_TIME_MESSAGE);
            }
            Task newTask = new Deadline(taskName, time);
            return new AddCommand(newTask);
        } catch (InvalidInputException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a user command that starts with "event."
     *
     * @param userCommand The string of user command.
     * @return The command object that Duke will execute.
     */
    public static Command parseEvent(String userCommand) {
        String[] wordArray = userCommand.split(" ");
        int noOfWords = wordArray.length;
        try {
            if (noOfWords == 1) {
                throw new InvalidEventException(INSUFFICIENT_EVENT_INFO_MESSAGE);
            }
            String body = userCommand.split(" ", 2)[1];
            if (body.split(" /at ").length < 2) {
                throw new InvalidEventException(INVALID_EVENT_FORMAT_MESSAGE);
            }
            String taskName = body.split(" /at ")[0];
            String time = body.split(" /at ")[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
            try {
                LocalDate localDate = LocalDate.parse(time, formatter);
                String parsedTime = localDate.format(formatter);
                if (!parsedTime.equals(time)) {
                    throw new InvalidEventException(INVALID_EVENT_TIME_MESSAGE);
                }
            } catch (DateTimeParseException e) {
                return new ErrorCommand(INVALID_EVENT_TIME_MESSAGE);
            }
            Task newTask = new Event(taskName, time);
            return new AddCommand(newTask);
        } catch (InvalidInputException e) {
            return new ErrorCommand(e.getMessage());
        }
    }
}

