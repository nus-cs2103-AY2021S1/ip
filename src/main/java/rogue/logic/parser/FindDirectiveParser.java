package rogue.logic.parser;

import rogue.logic.directives.FindDirective;
import rogue.logic.parser.exceptions.IncorrectInputException;
import rogue.model.argument.Argument;

/**
 * Parses user inputs into a {@code FindDirective}.
 */
public class FindDirectiveParser {
    /** Option to search for a {@code Task} by its description. */
    private static final String OPTION_SEARCH_DESCRIPTION = "/d";

    /** Message when no search terms are provided. */
    private static final String ERROR_MISSING_SEARCH_TERM = "sToP TrYiNg tO FoOl mE. "
            + "fInD MuSt bE FoLlOwEd bY ThE SeArCh tErM.";

    /**
     * Creates a {@code FindDirective} for a {@code Task}.
     *
     * OPTION_SEARCH_DESCRIPTION: the search term for the description of a {@code Task}.
     *
     * @param args The action and options to be parsed.
     * @return A {@code DoneDirective} for the requested {@code Task}
     * @throws IncorrectInputException if the search term is missing.
     */
    public FindDirective parse(Argument args) throws IncorrectInputException {
        String descriptionSearchTerm = args.getOptionValue(OPTION_SEARCH_DESCRIPTION);

        if (descriptionSearchTerm.equals("")) {
            throw new IncorrectInputException(ERROR_MISSING_SEARCH_TERM);
        }

        return new FindDirective(descriptionSearchTerm);
    }
}
