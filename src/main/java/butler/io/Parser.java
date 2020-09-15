package butler.io;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import butler.command.AddCommand;
import butler.command.Command;
import butler.command.CompleteCommand;
import butler.command.DeleteCommand;
import butler.command.ExitCommand;
import butler.command.FindCommand;
import butler.command.ListCommand;
import butler.command.UndoCommand;
import butler.exception.ButlerException;
import butler.task.DeadlineTask;
import butler.task.EventTask;
import butler.task.Task;
import butler.task.ToDoTask;

/**
 * Represents a parser to identify the content of the user input.
 * Throws <code>ButlerException</code> when an invalid input is processed.
 */
public class Parser {

    /**
     * Parses the given <code>input</code> to identify the command given.
     *
     * @param input User input to be parsed.
     * @return Command represented by the <code>input</code>.
     * @throws ButlerException if the user input is invalid.
     */
    public static Command parse(String input) throws ButlerException {
        String commandType = input.split(" ")[0];

        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }

        switch (commandType) {
        case "done":
            return parseCompleteCommand(input);
        case "delete":
            return parseDeleteCommand(input);
        case "todo":
            return parseTodoCommand(input);
        case "event":
            return parseEventCommand(input);
        case "deadline":
            return parseDeadlineCommand(input);
        case "find":
            return parseFindCommand(input);
        case "undo":
            return parseUndoCommand(input);
        default:
            throw new ButlerException("This is not a valid command type.\n"
                    + "Valid commands start with list, done, delete, "
                    + "todo, deadline, event, find, undo or bye.");
        }
    }

    /**
     * Parses a command to add a todo task.
     *
     * @param input User input to be parsed.
     * @return Command represented by the <code>input</code>.
     * @throws ButlerException if the user input does not have a description.
     */
    private static Command parseTodoCommand(String input) throws ButlerException {
        try {
            String taskDetails = input.split(" ", 2)[1];
            Task task = new ToDoTask(taskDetails);
            return new AddCommand(task);
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please add a description for the ToDo task.");
        }
    }

    /**
     * Parses a command to add a deadline task.
     *
     * @param input User input to be parsed.
     * @return Command represented by the <code>input</code>.
     * @throws ButlerException if the user input does not have a valid deadline
     *                         or is missing details.
     */
    private static Command parseDeadlineCommand(String input) throws ButlerException {
        try {
            String taskDetails = input.split(" ", 2)[1];

            String summary = taskDetails.split(" /by ", 2)[0];
            String deadline = taskDetails.split(" /by ", 2)[1];
            LocalDate formattedDeadline = LocalDate.parse(deadline);

            Task task = new DeadlineTask(summary, formattedDeadline);
            return new AddCommand(task);

        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please provide a summary and deadline.\n"
                    + "Separate the deadline from summary using \" /by \".");

        } catch (DateTimeParseException e) {
            throw new ButlerException("Please input a valid Date format.\n"
                    + "Valid Date format is YYYY-MM-DD.");
        }
    }

    /**
     * Parses a command to add an event task.
     *
     * @param input User input to be parsed.
     * @return Command represented by the <code>input</code>.
     * @throws ButlerException if the user input does not provide valid dates
     *      *                  or is missing details.
     */
    private static Command parseEventCommand(String input) throws ButlerException {
        try {
            String taskDetails = input.split(" ", 2)[1];

            String summary = taskDetails.split(" /at ", 2)[0];
            String date = taskDetails.split(" /at ", 2)[1];
            LocalDate startDate = LocalDate.parse(date.split(" ")[0]);
            LocalDate endDate = LocalDate.parse(date.split(" ")[1]);

            Task task = new EventTask(summary, startDate, endDate);
            return new AddCommand(task);

        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please provide a summary and date of event.\n"
                    + "Separate the dates from summary using \" /at \" and "
                    + "separate the two dates using a space.");

        } catch (DateTimeParseException e) {
            throw new ButlerException("Please input a valid Date format.\n"
                    + "Valid Date format is YYYY-MM-DD.");
        }
    }

    /**
     * Parses a command to mark some tasks as complete.
     *
     * @param input User input to be parsed.
     * @return Command represented by the <code>input</code>.
     * @throws ButlerException if the user input does not contain valid task indexes.
     */
    private static Command parseCompleteCommand(String input) throws ButlerException {
        String[] commandDetails = input.split(" ");
        int indexCount = commandDetails.length;
        String[] indexStringArray = Arrays.copyOfRange(commandDetails, 1, indexCount);
        ArrayList<Integer> indexList = new ArrayList<>();

        // Convert index from string to integer
        for (String stringIndex : indexStringArray) {
            try {
                Integer index = Integer.parseInt(stringIndex);
                indexList.add(index);
            } catch (NumberFormatException e) {
                throw new ButlerException("An invalid index was given.\n\""
                        + stringIndex + "\" is not an integer.");
            }
        }

        if (indexList.size() == 0) {
            throw new ButlerException("No index was given. Please provide a valid index.");
        }

        return new CompleteCommand(indexList);
    }

    /**
     * Parses a command to delete a task.
     *
     * @param input User input to be parsed.
     * @return Command represented by the <code>input</code>.
     * @throws ButlerException if the user input does not contain a valid task index.
     */
    private static Command parseDeleteCommand(String input) throws ButlerException {
        String stringIndex = "";
        try {
            stringIndex = input.split(" ")[1];
            int index = Integer.parseInt(stringIndex);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new ButlerException("An invalid index was given.\n\""
                    + stringIndex + "\" is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please add the index of the task to be deleted.");
        }
    }

    /**
     * Parses a command to find some tasks.
     *
     * @param input User input to be parsed.
     * @return Command represented by the <code>input</code>.
     * @throws ButlerException if the user input does not contain keywords to filter with.
     */
    private static Command parseFindCommand(String input) throws ButlerException {
        try {
            String keyword = input.split(" ", 2)[1];
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please add some keywords to filter with.");
        }
    }

    /**
     * Parses a command to undo previous commands.
     *
     * @param input User input to be parsed.
     * @return Command represented by the <code>input</code>.
     * @throws ButlerException if the user input does not contain a valid number.
     */
    private static Command parseUndoCommand(String input) throws ButlerException {
        String stringUndoCount = "";
        try {
            stringUndoCount = input.split(" ")[1];
            int undoCount = Integer.parseInt(stringUndoCount);
            return new UndoCommand(undoCount);
        } catch (NumberFormatException e) {
            throw new ButlerException("An invalid undo count was given.\n\""
                    + stringUndoCount + "\" is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please add the number of commands to undo.");
        }
    }
}
