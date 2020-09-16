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

    /** Option to search for a {@code Task} by its date. */
    private static final String OPTION_SEARCH_DAYS = "/days";

    /** Message when no search terms are provided. */
    private static final String ERROR_MISSING_SEARCH_TERM = "Stop trying to fool me. "
            + "The \"find\" command must be followed by a search condition.";

    /** Message when the number of days provided is not an integer. */
    private static final String ERROR_INCORRECT_DAYS = "Stop trying to fool me. "
            + " The number of days should be a number.";

    /**
     * Creates a {@code FindDirective} for a {@code Task}.
     *
     * At least one search condition has to be provided. Multiple search conditions
     * will result in a combined (AND) search.
     *
     * OPTION_SEARCH_DESCRIPTION: all tasks with description containing the search term.
     *
     * OPTION_SEARCH_DAYS: all tasks that are not overdue and due to happen in the
     * specified number of days (from system date).
     *
     * @param args The action and options to be parsed.
     * @return A {@code DoneDirective} for the requested {@code Task}
     * @throws IncorrectInputException if no search condition is provided.
     */
    public FindDirective parse(Argument args) throws IncorrectInputException {
        String descriptionSearchTerm = args.getOptionValue(OPTION_SEARCH_DESCRIPTION);
        String daySearchTerm = args.getOptionValue(OPTION_SEARCH_DAYS);

        boolean hasDescription = !descriptionSearchTerm.equals("");
        boolean hasDay = !daySearchTerm.equals("");

        if (!hasDescription && !hasDay) {
            throw new IncorrectInputException(ERROR_MISSING_SEARCH_TERM);
        }

        if (hasDescription && hasDay) {
            return new FindDirective(descriptionSearchTerm, parseDay(daySearchTerm));
        } else if (hasDay) {
            return new FindDirective(parseDay(daySearchTerm));
        } else {
            return new FindDirective(descriptionSearchTerm);
        }
    }

    /**
     * Parses a string (the number of days) into an integer.
     *
     * @param daySearchTerm The string to parse.
     * @return The number of days
     * @throws IncorrectInputException if the string is not an integer.
     */
    private int parseDay(String daySearchTerm) throws IncorrectInputException {
        try {
            return Integer.parseInt(daySearchTerm);
        } catch (NumberFormatException e) {
            throw new IncorrectInputException(ERROR_INCORRECT_DAYS);
        }
    }
}
