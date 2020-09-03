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
    private static final String ERROR_INCORRECT_INDEX = "sToP TrYiNg tO FoOl mE. tHe \"delete\" ComMand"
            + " mUsT Be FolLoWed bY tHe InDEx Of THe TAsK.";

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
