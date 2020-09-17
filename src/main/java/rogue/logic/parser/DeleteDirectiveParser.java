package rogue.logic.parser;

import rogue.logic.directives.DeleteDirective;
import rogue.logic.parser.exceptions.IncorrectInputException;
import rogue.model.argument.Argument;

/**
 * Parses user inputs into a {@code DeleteDirective}.
 */
public class DeleteDirectiveParser {
    /** Option to select the index of a {@code Task} from the {@code TaskList}. */
    private static final String OPTION_INDEX = "/i";

    /** Message for when the index is missing. */
    private static final String ERROR_INCORRECT_INDEX = "Stop trying to fool me. The \"delete\" command"
            + " must be followed by the index of the task.";

    /**
     * Creates a {@code DeleteDirective} for a {@code Task}.
     *
     * OPTION_INDEX: the index of the {@code Task} to delete.
     *
     * @param args The action and options to be parsed.
     * @return A {@code FindDirective} to search for matching {@code Task}
     * @throws IncorrectInputException if the provided index is not an integer.
     */
    public DeleteDirective parse(Argument args) throws IncorrectInputException {
        try {
            // The index provided by the user is off by one
            int index = Integer.parseInt(args.getOptionValue(OPTION_INDEX)) - 1;

            return new DeleteDirective(index);
        } catch (NumberFormatException e) {
            throw new IncorrectInputException(ERROR_INCORRECT_INDEX);
        }
    }
}
