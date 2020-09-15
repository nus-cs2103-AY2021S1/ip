package duke.util;

import duke.DukeException;
import duke.commands.*;
import duke.tasks.TaskType;

/**
 * Class to parse user input and process it.
 */
public class Parser {

    static final String BYE = "bye";
    static final String LIST = "list";
    static final String TODO = "todo";
    static final String DDL = "deadline";
    static final String EVENT = "event";
    static final String DELETE = "delete";
    static final String DONE = "done";
    static final String FIND = "find";

    /**
     * Creates a Parser with the given classes.
     */
    public Parser() {}

    /**
     * Parses and processes the input string.
     *
     * @param input String input from the user
     * @return Command object to execute the respective process
     * @throws DukeException Duke-related exception due to erroneous inputs
     */
    public Command processInput(String input) throws DukeException {
        if (input.equals(BYE)) {
            return new ExitCommand();
        } else if (input.equals(LIST)) {
            return new ListCommand();
        } else {
            String[] inputSplits = input.split(" ", 2);
            String command = inputSplits[0];
            switch (command) {
            case TODO:
                if (inputSplits.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty");
                }
                return new AddCommand(inputSplits[1], TaskType.TODO);
            case DDL:
                if (inputSplits.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty");
                }
                return new AddCommand(inputSplits[1], TaskType.DEADLINE);
            case EVENT:
                if (inputSplits.length < 2) {
                    throw new DukeException("The description of an event cannot be empty");
                }
                return new AddCommand(inputSplits[1], TaskType.EVENT);
            case DONE:
                if (inputSplits.length < 2) {
                    throw new DukeException("Task number cannot be empty");
                }
                try {
                    return new DoneCommand(inputSplits[1]);
                } catch (NumberFormatException ex) {
                    throw new DukeException("Task number must be a valid integer");
                }
            case DELETE:
                if (inputSplits.length < 2) {
                    throw new DukeException("Task number cannot be empty");
                }
                try {
                    return new DeleteCommand(inputSplits[1]);
                } catch (NumberFormatException ex) {
                    throw new DukeException("Task number must be a valid integer");
                }
            case FIND:
                if (inputSplits.length < 2) {
                    throw new DukeException("Search key cannot be empty");
                }
                return new FindCommand(inputSplits[1]);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :(");
            }
        }
    }
}
