package duke;

import duke.command.*;
import duke.exception.*;

/**
 * Parse user inputs and translate them into commands to be executed by the chat bot.
 */
public class Parser {

    private static final int SLASH_INDEX = 1;

    private static final String BYE = "bye";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String EVENT = "event";
    private static final String FIND = "find";
    private static final String LIST = "list";
    private static final String SPACE = " ";
    private static final String TODO = "todo";

    /**
     * Parses the user input to determine what command the user intended to run.
     * @param input String representing user input.
     * @return A Command object.
     * @throws DukeEmptyArgumentException If argument of done and delete commands are empty.
     */
    public static Command parse(String input)
            throws DukeEmptyArgumentException {
        String command = input.strip();
        String[] commandWordArray = command.split(SPACE);
        if (command.equals(BYE)) {
            return new ByeCommand(command, true);
        }
        if (command.equals(LIST)) {
            return new ListCommand(command);
        }
        if (commandWordArray[0].equals(DONE)) {
            if (command.substring(commandWordArray[0].length())
                    .isBlank()) {
                throw new DukeEmptyArgumentException(DONE);
            }
            return new DoneCommand(commandWordArray[0],
                    commandWordArray[1]);
        }
        if (commandWordArray[0].equals(DELETE)) {
            if (command.substring(commandWordArray[0].length())
                    .isBlank()) {
                throw new DukeEmptyArgumentException(DELETE);
            }
            return new DeleteCommand(commandWordArray[0],
                    commandWordArray[1]);
        }
        if (commandWordArray[0].equals(TODO)
                || commandWordArray[0].equals(DEADLINE)
                || commandWordArray[0].equals(EVENT)) {
            return new AddCommand(commandWordArray[0],
                    command.substring(commandWordArray[0].length()
                            + SLASH_INDEX));
        }
        if (commandWordArray[0].equals(FIND)) {
            return new FindCommand(commandWordArray[0], commandWordArray[1]);
        }
        return new UnknownCommand(command);
    }
}
