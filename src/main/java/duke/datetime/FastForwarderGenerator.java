package duke.datetime;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * A Generator class that handles the logic of parsing strings to create the appropriate FastForwarder.
 */
public class FastForwarderGenerator {

    /** Identifier for a day-unit interval */
    private static final String INTERVAL_DAILY = "D";
    /** Identifier for a week-unit interval */
    private static final String INTERVAL_WEEKLY = "W";
    /** Identifier for a month-unit interval */
    private static final String INTERVAL_MONTHLY = "M";
    /** Identifier for a year-unit interval */
    private static final String INTERVAL_YEARLY = "Y";

    /** List containing all possible valid interval identifiers */
    private static final List<String> VALID_INTERVAL_IDENTIFIERS = new ArrayList<>();
    static {
        VALID_INTERVAL_IDENTIFIERS.add(INTERVAL_DAILY);
        VALID_INTERVAL_IDENTIFIERS.add(INTERVAL_WEEKLY);
        VALID_INTERVAL_IDENTIFIERS.add(INTERVAL_MONTHLY);
        VALID_INTERVAL_IDENTIFIERS.add(INTERVAL_YEARLY);
    }

    /**
     * Returns a FastForwarder that takes in a LocalDateTime and moves it forward by the specified amount of time.
     * Recurring details should be in the form of <code>OX</code> where
     * <code>O</code> is the unit multiplier for the unit interval
     * <code>X</code> is the unit interval used for this fastforwarder. 'D' for day, 'W' for week, 'M' and 'Y'.
     *
     * @param recurringDetails details of the recurring interval
     * @return FastForwarder that takes in a LocalDateTime and returns a new LocalDateTime that has been moved forward.
     * @throws DukeException if the recurring details provided is of the wrong format or invalid.
     */
    public static FastForwarder generateFastForwarder(String recurringDetails) throws DukeException {
        int recurringIdentifierIndex = recurringDetails.length() - 1;
        int multiplier;
        if (recurringIdentifierIndex == 0) {
            multiplier = 1;
        } else {
            String multiplierString = recurringDetails.substring(0, recurringIdentifierIndex);
            try {
                multiplier = Integer.parseInt(multiplierString);
            } catch (NumberFormatException e) {
                throw new DukeException(multiplierString + " is not a valid number!");
            }
        }
        if (multiplier <= 0) {
            throw new DukeException("Please specify an integer larger than 0!");
        }

        String recurringIdentifier = recurringDetails.substring(recurringIdentifierIndex).toUpperCase();
        switch (recurringIdentifier) {
        case INTERVAL_DAILY:
            return localDateTime -> localDateTime.plusDays(multiplier);
        case INTERVAL_WEEKLY:
            return localDateTime -> localDateTime.plusWeeks(multiplier);
        case INTERVAL_MONTHLY:
            return localDateTime -> localDateTime.plusMonths(multiplier);
        case INTERVAL_YEARLY:
            return localDateTime -> localDateTime.plusYears(multiplier);
        default:
            throw new DukeException(getInvalidIdentifierExceptionMessage());
        }

    }

    /** Formats the invalid identifier message */
    private static String getInvalidIdentifierExceptionMessage() {
        return "Invalid interval type!\nPlease use one of the following: "
                + String.join(", ", VALID_INTERVAL_IDENTIFIERS);
    }


}
