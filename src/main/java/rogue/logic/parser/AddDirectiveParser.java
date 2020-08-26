package rogue.logic.parser;

import rogue.logic.directives.Action;
import rogue.logic.directives.AddDirective;

import rogue.commons.exceptions.IncorrectArgumentException;
import rogue.commons.util.DateTimeUtil;
import rogue.commons.util.StringUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddDirectiveParser {
    private final String ERROR_MISSING_DESC = "sToP TrYiNg tO FoOl mE. "
            + "a tOdO MuSt bE FoLlOwEd bY A DesCrIpTiOn.";
    private final String ERROR_MISSING_DESC_OR_DATE = "sToP TrYiNg tO FoOl mE. "
            + "a%s MuSt bE FoLlOwEd bY A DeScRiPtIoN AnD dAtE.";
    private final String ERROR_MISSING_DATE = "sToP TrYiNg tO FoOl mE."
            + " tHe dAtE MuSt cOmE RiGhT AfTeR \"%s\".";
    private final String ERROR_INCORRECT_DATE_FORMAT = "sToP TrYiNg tO FoOl mE."
            + " dAtE MuSt bE In yEaR-MoNtH-DaY FoRmAt.";

    public AddDirective parse(Action action, String[] args) throws IncorrectArgumentException {
        switch (action) {
        case ADD_DEADLINE:
            return parseDeadline(action, args);

        case ADD_EVENT:
            return parseEvent(action, args);

        default:
            return parseTodo(action, args);
        }
    }

    private AddDirective parseTodo(Action action, String[] args) throws IncorrectArgumentException {
        if (args.length < 2) {
            throw new IncorrectArgumentException(ERROR_MISSING_DESC);
        }

        String description = StringUtil.strArrJoin(args, 1, args.length, " ");

        return new AddDirective(action, description);
    }

    private AddDirective parseDeadline(Action action, String[] args) throws IncorrectArgumentException {
        if (args.length < 4) {
            throw new IncorrectArgumentException(String.format(ERROR_MISSING_DESC_OR_DATE, " dEaDlInE"));
        }

        int index = StringUtil.findIndex(args, "/by");
        if (index == -1) {
            throw new IncorrectArgumentException(String.format(ERROR_MISSING_DATE, "/by"));
        }

        String description = StringUtil.strArrJoin(args, 1, index, " ");

        try {
            String dateString = StringUtil.strArrJoin(args, index + 1, args.length, " ");
            LocalDate by = DateTimeUtil.stringAsDate(dateString);

            return new AddDirective(action, description, by);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException(ERROR_INCORRECT_DATE_FORMAT);
        }
    }

    private AddDirective parseEvent(Action action, String[] args) throws IncorrectArgumentException {
        if (args.length < 4) {
            throw new IncorrectArgumentException(String.format(ERROR_MISSING_DESC_OR_DATE, "N EvEnT"));
        }

        int index = StringUtil.findIndex(args, "/at");
        if (index == -1) {
            throw new IncorrectArgumentException(String.format(ERROR_MISSING_DATE, "/at"));
        }

        String description = StringUtil.strArrJoin(args, 1, index, " ");

        try {
            String dateString = StringUtil.strArrJoin(args, index + 1, args.length, " ");
            LocalDate at = DateTimeUtil.stringAsDate(dateString);

            return new AddDirective(action, description, at);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException(ERROR_INCORRECT_DATE_FORMAT);
        }
    }
}
