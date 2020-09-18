package duke;

import exception.EmptyDescriptionException;
import exception.EmptyTimeException;
import exception.InvalidCommandException;
import exception.InvalidIndexException;

/**
 * A parser for user's input command.
 */
public class Parser {
    private static void checkCommands(String commandType) throws InvalidCommandException {
        try {
            Commands.valueOf(commandType);
        } catch (IllegalArgumentException ex) {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the user input to the available command in Duke.
     * @param fullCommand the command from the user input.
     * @return the Commands enum correspond to the user input.
     * @throws InvalidCommandException if the command user input not available in Duke's command.
     */
    public static Commands parse(String fullCommand) throws InvalidCommandException {
        String[] inputs = fullCommand.split("\\s+", 2);
        String commandType = inputs[0].trim().toUpperCase();
        checkCommands(commandType);
        return Commands.valueOf(commandType);
    }

    /**
     * Checks whether the index from user input is within the range of current tasks.
     * @param inputs the user input.
     * @param numberOfTask number of current tasks.
     * @throws InvalidIndexException if the user inputs an invalid index (out of bound) or wrong data type.
     */
    public static void checkIndex(String[] inputs, int numberOfTask) throws InvalidIndexException {
        String usage = (numberOfTask > 1 ? "\nInput a number between 1 - " + numberOfTask : "");
        if (inputs.length < 2 || inputs[1].trim().equals("")) {
            throw new InvalidIndexException("Please input a valid index of task" + usage);
        }
        try {
            int index = Integer.parseInt(inputs[1]);
            if (index < 1 || index > numberOfTask) {
                throw new InvalidIndexException("Please input a valid index of task" + usage);
            }
        } catch (NumberFormatException ex) {
            throw new InvalidIndexException("Please input a valid index of task" + usage);
        }
    }

    /**
     * Checks whether the user input contains task description.
     * @param task the user task input.
     * @param commandType the type of the task.
     * @throws EmptyDescriptionException if the user input does not contain task description.
     */
    public static void checkDescription(String[] task, Commands commandType) throws EmptyDescriptionException {
        String usage = "Please input using the format: " + (commandType.equals(Commands.TODO)
            ? "todo <todo_desc>"
            : commandType.equals(Commands.DEADLINE)
            ? "deadline <deadline_desc> /by <time>"
            : "event <event_desc> /at <time>");
        if (task.length < 2 || task[1].trim().equals("")) {
            throw new EmptyDescriptionException("Please specify the task description\n" + usage);
        }
    }

    /**
     * Checks whether the user input contains the time for the task.
     * @param desc the user input description.
     * @param type the type of the task.
     * @throws EmptyTimeException if the user input does not contain the time for the task.
     */
    public static void checkTime(String[] desc, Commands type) throws EmptyTimeException {
        String usage = "Please input using the format: " + (type.equals(Commands.TODO)
            ? "todo <todo_desc>"
            : type.equals(Commands.DEADLINE)
            ? "deadline <deadline_desc> /by <time>"
            : "event <event_desc> /at <time>");
        if (desc.length < 2 || desc[1].trim().equals("")) {
            throw new EmptyTimeException("Please specify the time for the task\n" + usage);
        }
    }
}
