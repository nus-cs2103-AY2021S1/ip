package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEmptyDescriptionException;

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
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String SPACE = " ";
    private static final String TODO = "todo";

    /**
     * Parses the user input to determine what command the user intended to run.
     *
     * @param input String representing user input.
     * @return A Command object.
     * @throws DukeEmptyArgumentException If argument of done and delete commands are empty.
     */
    public static Command parse(String input)
            throws DukeEmptyArgumentException,
            DukeEmptyDescriptionException {
        assert input != null : "String object cannot be null";
        String command = input.strip();
        String[] commandWordArray = command.split(SPACE);
        String firstWord = commandWordArray[0].toLowerCase();
        boolean isSingleWordCommand = command.substring(firstWord.length())
                .isBlank();
        switch (firstWord) {
        case BYE:
            if (commandWordArray.length != 1) {
                return new UnknownCommand(firstWord);
            }
            return new ByeCommand(firstWord);
        case LIST:
            if (commandWordArray.length != 1) {
                return new UnknownCommand(firstWord);
            }
            return new ListCommand(firstWord);
        case DONE:
            if (isSingleWordCommand) {
                throw new DukeEmptyArgumentException(DONE);
            }
            return new DoneCommand(firstWord,
                    commandWordArray[1]);
        case DELETE:
            if (isSingleWordCommand) {
                throw new DukeEmptyArgumentException(DELETE);
            }
            return new DeleteCommand(firstWord,
                    commandWordArray[1]);
        case TODO:
        case DEADLINE:
        case EVENT:
            if (isSingleWordCommand) {
                throw new DukeEmptyDescriptionException(firstWord);
            }
            return new AddCommand(firstWord,
                    command.substring(firstWord.length()
                            + SLASH_INDEX));
        case FIND:
            if (isSingleWordCommand) {
                throw new DukeEmptyArgumentException(FIND);
            }
            return new FindCommand(firstWord,
                    commandWordArray[1]);
        case HELP:
            if (commandWordArray.length != 1) {
                return new UnknownCommand(command);
            }
            return new HelpCommand(firstWord);
        default:
            return new UnknownCommand(command);
        }
    }
}
