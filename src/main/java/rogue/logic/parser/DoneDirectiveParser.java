package rogue.logic.parser;

import rogue.logic.directives.DoneDirective;
import rogue.logic.parser.exceptions.IncorrectInputException;
import rogue.model.argument.Argument;

/**
 * Parses user inputs into a {@code DoneDirective}.
 */
public class DoneDirectiveParser {
    /** Option to select the index of a {@code Task} from the {@code TaskList}. */
    private static final String OPTION_INDEX = "/i";

    /** Message for when the index is missing. */
    private static final String ERROR_INCORRECT_INDEX = "Stop trying to fool me. The \"done\" command"
            + " must be followed by the index of the task.";

    /**
     * Creates a {@code DoneDirective} for a {@code Task}.
     *
     * OPTION_INDEX: the index of the {@code Task} to mark as done.
     *
     * @param args The action and options to be parsed.
     * @return A {@code DoneDirective} for the requested {@code Task}
     * @throws IncorrectInputException if the provided index is not an integer.
     */
    public DoneDirective parse(Argument args) throws IncorrectInputException {
        try {
            // The index provided by the user is off by one
            int index = Integer.parseInt(args.getOptionValue(OPTION_INDEX)) - 1;

            return new DoneDirective(index);
        } catch (NumberFormatException e) {
            throw new IncorrectInputException(ERROR_INCORRECT_INDEX);
        }
    }
}
