package duke;

/**
 * Handles making sense of user input.
 */

public class Parser {
    /**
     * Constructor for duke.Parser.
     */
    public Parser() {
    }

    /**
     * Converts user input into a command of the appropriate type depending on the leading word
     * of the user input.
     * @param input User input.
     * @return duke.Command object corresponding to input.
     * @throws DukeException When user input cannot be parsed.
     */
    public Command parse(String input) throws DukeException {
        String[] chunks = input.split(" ", 2);
        String action = chunks[0].toUpperCase();
        Constants.CommandType command;
        try {
            command = Constants.CommandType.valueOf(action);
        } catch (IllegalArgumentException e) {
            throw new DukeException(">> Oh no!!! I don't understand this input.");
        }
        switch(command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DELETE:
            if (chunks.length < 2) {
                throw new DukeException(">> Oh no!!! Delete must have the index of the task you're deleting!");
            }
            return new DeleteCommand(chunks);
        case DONE:
            if (chunks.length < 2) {
                throw new DukeException(">> Oh no!!! Done must have the index of the task you're completing!");
            }
            return new DoneCommand(chunks);
        case TODO:
            if (chunks.length < 2) {
                throw new DukeException(">> Oh no!!! A todo must have a description!");
            }
            return new AddTodoCommand(chunks);
        case DEADLINE:
            if (chunks.length < 2) {
                throw new DukeException(">> Oh no!!! A deadline must have a description and date!");
            }
            return new AddDeadlineCommand(chunks);
        case EVENT:
            if (chunks.length < 2) {
                throw new DukeException(">> Oh no!!! An event must have a description and date!");
            }
            return new AddEventCommand(chunks);
        case FIND:
            if (chunks.length < 2) {
                throw new DukeException(">> Oh no!!! Find needs a search word or phrase!");
            }
            return new FindCommand(chunks);
        default:
            throw new DukeException(">> Oh no!!! I don't understand this input.");
        }
    }
}
