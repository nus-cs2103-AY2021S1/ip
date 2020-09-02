package rogue.logic.parser;

import rogue.logic.directives.Action;
import rogue.logic.directives.Executable;
import rogue.logic.directives.ExitDirective;
import rogue.logic.directives.ListDirective;
import rogue.logic.parser.exceptions.UnknownCommandException;

import rogue.logic.exceptions.IncorrectArgumentException;

/**
 * Parses user inputs into an {@code Executable} that is understood
 * and can be executed by {@code Rogue}.
 */
public class Parser {
    /** Message for when user input is not a known {@code Action}. */
    private static final String ERROR_UNKNOWN_COMMAND = "sToP TrYiNg tO FoOl mE. %s iS An uNkNoWn cOmMaNd.";

    /**
     * Creates an {@code Executable} to be run by {@code Rogue}.
     *
     * Splits the user input into separate arguments based on the whitespace
     * character. The first argument is checked against the list of possible
     * {@code Action}. A valid {@code Action} will result in the appropriate
     * {@code Executable} being created. Otherwise, an exception is thrown.
     *
     * @param fullCommand The user input.
     * @return An {@code Executable} corresponding to the {@code Action}
     * @throws UnknownCommandException if user input is an invalid {@code Action}
     * @throws IncorrectArgumentException if the arguments are not suitable for the particular {@code Action}
     */
    public static Executable createExe(String fullCommand)
            throws UnknownCommandException, IncorrectArgumentException {
        String[] args = fullCommand.trim().split("\\s"); // User input split by whitespace

        Action action = Action.getAction(args[0]);

        switch (action) {
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
            return new AddDirectiveParser().parse(action, args);

        default:
            throw new UnknownCommandException(String.format(ERROR_UNKNOWN_COMMAND, args[0]));
        }
    }
}
