package rogue.logic.parser;

import rogue.commons.util.StringUtil;
import rogue.logic.directives.FindDirective;
import rogue.logic.exceptions.IncorrectArgumentException;

/**
 * Parses user inputs into a {@code FindDirective}.
 */
public class FindDirectiveParser {
    /** Message when no search terms are provided. */
    private final String ERROR_MISSING_SEARCH_TERM = "sToP TrYiNg tO FoOl mE. "
            + "fInD MuSt bE FoLlOwEd bY ThE SeArCh tErM.";

    public FindDirective parse(String[] args) throws IncorrectArgumentException {
        if (args.length < 2) {
            throw new IncorrectArgumentException(ERROR_MISSING_SEARCH_TERM);
        }

        String searchTerm = StringUtil.strArrJoin(args, 1, args.length, " ");

        return new FindDirective(searchTerm);
    }
}
