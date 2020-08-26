package rogue.logic.parser;

import rogue.logic.directives.Action;
import rogue.logic.directives.Executable;
import rogue.logic.directives.ExitDirective;
import rogue.logic.directives.ListDirective;

import rogue.commons.exceptions.IncorrectArgumentException;
import rogue.logic.parser.exceptions.UnknownCommandException;

public class Parser {
    private static final String ERROR_INCORRECT_ARGUMENT = "i hAtE YoU! sToP MaKiNg mE Do sOmEtHiNg iMpOsSiBlE.";
    private static final String ERROR_UNKNOWN_COMMAND = "sToP TrYiNg tO FoOl mE. %s iS An uNkNoWn cOmMaNd.";

    public static Executable createExe(String fullCommand)
            throws UnknownCommandException, IncorrectArgumentException {
        String[] args = fullCommand.trim().split("\\s");
        if (args.length <= 0) {
            throw new IncorrectArgumentException(ERROR_INCORRECT_ARGUMENT);
        }

        Action action = Action.getAction(args[0]);

        switch (action) {
        case EXIT:
            return new ExitDirective();

        case LIST:
            return new ListDirective();

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
