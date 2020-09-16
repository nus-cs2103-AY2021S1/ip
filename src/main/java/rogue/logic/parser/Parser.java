package rogue.logic.parser;

import rogue.logic.directives.Executable;
import rogue.logic.directives.ExitDirective;
import rogue.logic.directives.ListDirective;
import rogue.logic.parser.exceptions.IncorrectInputException;
import rogue.model.argument.Argument;

/**
 * Parses user inputs into an {@code Executable} that is understood
 * and can be executed by {@code Rogue}.
 */
public class Parser {
    /** Message for when user input is not a known {@code Action}. */
    private static final String ERROR_UNKNOWN_COMMAND = "Stop trying to fool me. %s is an unknown command.";

    /**
     * Creates an {@code Executable} to be run by {@code Rogue}.
     *
     * Splits the user input into separate arguments based on the whitespace
     * character. The first argument is checked against the list of possible
     * {@code Action}. A valid {@code Action} will result in the appropriate
     * {@code Executable} being created. Otherwise, an exception is thrown.
     *
     * @param input The user input.
     * @return An {@code Executable} corresponding to the {@code Action}
     * @throws IncorrectInputException if user input is an invalid {@code Action}
     */
    public static Executable createExe(String input) throws IncorrectInputException {
        Argument args = ArgumentParser.parseArgs(input);

        switch (args.getAction()) {
        case EXIT:
            return new ExitDirective();

        case LIST:
            return new ListDirective();

        case FIND:
            return new FindDirectiveParser().parse(args);

        case MARK_AS_DONE:
            return new DoneDirectiveParser().parse(args);

        case DELETE:
            return new DeleteDirectiveParser().parse(args);

        case ADD_TODO:
        case ADD_DEADLINE:
        case ADD_EVENT:
            return new AddDirectiveParser().parse(args);

        default:
            throw new IncorrectInputException(String.format(ERROR_UNKNOWN_COMMAND, input));
        }
    }
}
