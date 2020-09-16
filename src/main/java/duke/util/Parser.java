package duke.util;

import duke.command.*;
import duke.exception.DukeException;

import java.util.Arrays;

/**
 * Parses user input as commands.
 */
public class Parser {

    /**
     * Gets appended strings from string array.
     * This facilitates parsing of input with many words.
     *
     * @param inputArr  Input string array.
     * @param indexFrom Index of array to start appending.
     * @param indexTo   Index of array to end appending.
     * @return Appended string.
     * @throws IndexOutOfBoundsException When command input is wrongly formatted.
     */
    public static String generateStringFromArray(String[] inputArr, int indexFrom, int indexTo)
            throws IndexOutOfBoundsException {
        try {
            String output = "";
            for (int i = indexFrom; i < indexTo; i++) {
                output += inputArr[i] + " ";
            }
            return output.substring(0, output.length() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    /**
     * Parses user input as commands.
     *
     * @param input User input.
     * @return Command to be executed.
     * @throws DukeException When command input is wrongly formatted.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputInformation = input.split(" ");
        if (inputInformation[0].equals(CommandType.BYE.getInput())) {
            return new ExitCommand();
        } else if (inputInformation[0].equals(CommandType.LIST.getInput())) {
            return new ListCommand();
        } else if (inputInformation[0].equals(CommandType.DONE.getInput())) {
            return generateDoneCommand(inputInformation);
        } else if (inputInformation[0].equals(CommandType.DELETE.getInput())) {
            return generateDeleteCommand(inputInformation);
        } else if (inputInformation[0].equals(CommandType.TODO.getInput())) {
            return generateToDoCommand(inputInformation);
        } else if (inputInformation[0].equals(CommandType.DEADLINE.getInput())) {
            return generateDeadlineCommand(inputInformation);
        } else if (inputInformation[0].equals(CommandType.EVENT.getInput())) {
            return generateEventCommand(inputInformation);
        } else if (inputInformation[0].equals(CommandType.FIND.getInput())) {
            return generateFindCommand(inputInformation);
        } else {
            throw new DukeException("My duck instincts tell me your input makes no sense...");
        }
    }

    /**
     * Generates a Done Command.
     *
     * @param inputInformation String array containing input information.
     * @return Done Command to be executed.
     * @throws DukeException When command input is wrongly formatted.
     */
    public static DoneCommand generateDoneCommand(String[] inputInformation) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(inputInformation[1]);
            return new DoneCommand(taskNumber);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("You need to include your task number to mark done...");
        }
    }

    /**
     * Generates a Delete Command.
     *
     * @param inputInformation String array containing input information.
     * @return Delete Command to be executed.
     * @throws DukeException When command input is wrongly formatted.
     */
    public static DeleteCommand generateDeleteCommand(String[] inputInformation) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(inputInformation[1]);
            return new DeleteCommand(taskNumber);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("You need to include your task number to delete...");
        }
    }

    /**
     * Generates an Add Command for ToDo task.
     *
     * @param inputInformation String array containing input information.
     * @return Add Command to be executed.
     * @throws DukeException When command input is wrongly formatted.
     */
    public static AddCommand generateToDoCommand(String[] inputInformation) throws DukeException {
        try {
            String description = generateStringFromArray(inputInformation, 1, inputInformation.length);
            return new AddCommand(CommandType.TODO, description);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Your todo description can't be empty...");
        }
    }

    /**
     * Generates an Add Command for Deadline task.
     *
     * @param inputInformation String array containing input information.
     * @return Add Command to be executed.
     * @throws DukeException When command input is wrongly formatted.
     */
    public static AddCommand generateDeadlineCommand(String[] inputInformation) throws DukeException {
        try {
            int indexOfBy = Arrays.asList(inputInformation).indexOf("/by");
            if (indexOfBy == -1) {
                throw new DukeException("Did you include /by?");
            } else if (indexOfBy == 1) {
                throw new DukeException("Did you include a description?");
            } else {
                String description = generateStringFromArray(inputInformation, 1, indexOfBy);
                String by = generateStringFromArray(inputInformation, indexOfBy + 1, inputInformation.length);
                return new AddCommand(CommandType.DEADLINE, description, by);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Your deadline description or deadline can't be empty...");
        }
    }

    /**
     * Generates an Add Command for Event task.
     *
     * @param inputInformation String array containing input information.
     * @return Add Command to be executed.
     * @throws DukeException When command input is wrongly formatted.
     */
    public static AddCommand generateEventCommand(String[] inputInformation) throws DukeException {
        try {
            int indexOfAt = Arrays.asList(inputInformation).indexOf("/at");
            if (indexOfAt == -1) {
                throw new DukeException("Did you include /at?");
            } else if (indexOfAt == 1) {
                throw new DukeException("Did you include a description?");
            } else {
                String description = generateStringFromArray(inputInformation, 1, indexOfAt);
                String at = generateStringFromArray(inputInformation, indexOfAt + 1, inputInformation.length);
                return new AddCommand(CommandType.EVENT, description, at);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Your event description or event period can't be empty...");
        }
    }

    /**
     * Generates a Find Command.
     *
     * @param inputInformation String array containing input information.
     * @return Find Command to be executed.
     * @throws DukeException When command input is wrongly formatted.
     */
    public static FindCommand generateFindCommand(String[] inputInformation) throws DukeException {
        try {
            String[] searchKeywords = Arrays.copyOfRange(inputInformation, 1, inputInformation.length);
            return new FindCommand(searchKeywords);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("You need to include your keyword...");
        }
    }
}
