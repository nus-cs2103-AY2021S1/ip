package rogue.logic.parser;

import rogue.logic.directives.FindDirective;

import rogue.commons.util.StringUtil;

import rogue.commons.exceptions.IncorrectArgumentException;

public class FindDirectiveParser {
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
