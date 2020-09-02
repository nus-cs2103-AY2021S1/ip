package rogue.logic.parser;

import rogue.logic.directives.DeleteDirective;
import rogue.logic.exceptions.IncorrectArgumentException;

public class DeleteDirectiveParser {
    private static final String ERROR_INCORRECT_INDEX = "sToP TrYiNg tO FoOl mE. tHe \"delete\" ComMand"
            + " mUsT Be FolLoWed bY tHe InDEx Of THe TAsK.";

    public DeleteDirective parse(String[] args) throws IncorrectArgumentException {
        try {
            // The index provided by the user is off by one
            int index = Integer.parseInt(args[1]) - 1;

            return new DeleteDirective(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IncorrectArgumentException(ERROR_INCORRECT_INDEX);
        }
    }
}
