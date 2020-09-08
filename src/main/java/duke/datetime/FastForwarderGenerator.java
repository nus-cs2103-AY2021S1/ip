package duke.datetime;

import duke.DukeException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FastForwarderGenerator {

    private static final String INTERVAL_DAILY = "D";
    private static final String INTERVAL_WEEKLY = "W";
    private static final String INTERVAL_MONTHLY = "M";
    private static final String INTERVAL_YEARLY = "Y";

    private static final List<String> VALID_INTERVAL_IDENTIFIERS = new ArrayList<>();
    static {
        VALID_INTERVAL_IDENTIFIERS.add(INTERVAL_DAILY);
        VALID_INTERVAL_IDENTIFIERS.add(INTERVAL_WEEKLY);
        VALID_INTERVAL_IDENTIFIERS.add(INTERVAL_MONTHLY);
        VALID_INTERVAL_IDENTIFIERS.add(INTERVAL_YEARLY);
    }

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

        String recurringIdentifier = recurringDetails.substring(recurringIdentifierIndex);
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

    private static String getInvalidIdentifierExceptionMessage() {
        return "Invalid interval type!\nPlease use one of the following: "
                + String.join(", ", VALID_INTERVAL_IDENTIFIERS);
    }


}
