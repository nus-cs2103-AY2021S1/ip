package duke.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ViewCommand;
import duke.task.TaskType;

/**
 * Deals with parsing and making sense of the user command.
 */
public class Parser {

    /** Input string of the user. */
    private final String inputString;

    /**
     * Initialises the parser with the user's input string.
     *
     * @param inputString Input string of the user.
     */
    public Parser(String inputString) {
        this.inputString = inputString;
    }

    /**
     * Checks if an array of items contains a specified input string.
     *
     * @param inputString A specified input string to check.
     * @param items The array of elements to be compared with the input string.
     * @return True if the array of elements contains the input string.
     */
    public boolean containsString(String inputString, String[] items) {
        return Arrays.stream(items).anyMatch(inputString::contains);
    }

    /**
     * Parses the user's inputs into the correct command to be executed.
     *
     * @return The correct command object to be executed.
     * @throws DukeException If the input string is empty or unrecognized.
     */
    public Command parse() throws DukeException {
        List<String> addCommands = Arrays.stream(TaskType.values()).map(x -> x.name().toLowerCase())
                .collect(Collectors.toList());
        String[] splitInput = getString().trim().split("\\s+");
        String commandType = splitInput[0];
        if (commandType.equals("")) {
            throw new DukeException("Your Input String cannot be Empty!");
        } else if (commandType.equals("done")) {
            return new DoneCommand(splitInput);
        } else if (commandType.equals("delete")) {
            return new DeleteCommand(splitInput);
        } else if (commandType.equals("list")) {
            return new ListCommand(splitInput);
        } else if (commandType.equals("find")) {
            return new FindCommand(splitInput);
        } else if (commandType.equals("view")) {
            return new ViewCommand(splitInput);
        } else if (commandType.equals("bye")) {
            return new ExitCommand(splitInput);
        } else if (addCommands.contains(commandType)) {
            return new AddCommand(splitInput);
        } else {
            throw new DukeException("Your Input Command is not Recognized!");
        }
    }

    /**
     * Parses the user's inputs from a local file into the correct command to be executed.
     *
     * @param isDone Indicates if the string encapsulates a task that should be marked done.
     * @return AddCommand to add tasks from the file to a task list.
     * @throws DukeException If the input string from the file is empty or unrecognized.
     */
    public AddCommand parseFromFile(boolean isDone) throws DukeException {
        //Assuming that written file will only contain events to be added
        List<String> addCommands = retrieveAddCommands();
        String[] splitInput = getString().trim().split("\\s+");
        String commandType = splitInput[0];
        if (commandType.equals("")) {
            throw new DukeException("File has empty input String!");
        } else if (addCommands.contains(commandType)) {
            return new AddCommand(splitInput, isDone);
        } else {
            throw new DukeException("Your Input Command from the file is not Recognized!");
        }
    }

    private List<String> retrieveAddCommands() {
        return Arrays.stream(TaskType.values()).map(x -> x.name().toLowerCase())
                .collect(Collectors.toList());
    }

    /**
     * Gets the input string of the user.
     *
     * @return The input string of the user.
     */
    public String getString() {
        return inputString;
    }

}
