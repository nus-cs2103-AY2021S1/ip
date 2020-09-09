package alice.task.time;

import java.util.Arrays;

/**
 * Represents the natural days that are recognised by Alice.
 */
public enum NaturalDay {
    // Days of Week
    MONDAY("MON", "MONDAY"),
    TUESDAY("TUE", "TUESDAY", "TUES"),
    WEDNESDAY("WED", "WEDNESDAY"),
    THURSDAY("THU", "THURSDAY", "THURS"),
    FRIDAY("FRI", "FRIDAY"),
    SATURDAY("SAT", "SATURDAY"),
    SUNDAY("SUN", "SUNDAY"),

    // Other date language
    TODAY("TDY", "TODAY"),
    TOMORROW("TMR", "TOMORROW"),
    YESTERDAY("YESTERDAY"),

    // Special grammar that represents date and time
    NOW("NOW"),
    TONIGHT("TONIGHT"),

    // Time grammar
    MIDNIGHT("MIDNIGHT"),
    NOON("NOON", "AFTERNOON", "AFTER-NOON", "LUNCH"),
    MORNING("MORNING", "SUNRISE", "BREAKFAST"),
    EVENING("EVENING", "EVE", "DINNER"),
    NIGHT("NIGHT", "BEDTIME", "BED-TIME");

    /**
     * The string of words that is linked to the NaturalDay
     **/
    private final String[] words;

    NaturalDay(String... words) {
        this.words = words;
    }

    /**
     * Check if the word given indicate the respective {@code NaturalDay}
     *
     * @param wordToCheck the string to check
     * @return true if the word implies the corresponding {@code NaturalDay}, false otherwise
     */
    public boolean hasWord(String wordToCheck) {
        return Arrays.stream(words)
                .anyMatch(word -> word.equalsIgnoreCase(wordToCheck));
    }

    /**
     * Parse the date input for any natural date grammar that is used.
     *
     * @param dateString the date input string with a possible natural day reference.
     * @return the {@code NaturalDay} that corresponds to the date grammar, or null if no match is found.
     */
    public static NaturalDay parseDate(String dateString) {
        dateString = dateString.strip();
        NaturalDay[] days = NaturalDay.values();
        for (int i = 0; i < days.length; i++) {
            if (days[i].hasWord(dateString)) {
                return days[i];
            }

            // terminates once all date are checked
            if (days[i] == TONIGHT) {
                return null;
            }
        }
        return null;
    }

    /**
     * Parse the datetime input for any special natural datetime grammar that is used.
     *
     * @param dateTimeString the date input string with a possible natural datetime reference.
     * @return the {@code NaturalDay} that corresponds to the datetime grammar, or null if no match is found.
     */
    public static NaturalDay parseDateTime(String dateTimeString) {
        NaturalDay[] specials = new NaturalDay[]{NOW, TONIGHT};
        for (int i = 0; i < specials.length; i++) {
            if (specials[i].hasWord(dateTimeString)) {
                return specials[i];
            }
        }

        return null;
    }

    /**
     * Parse the time input for any natural time grammar that is used.
     *
     * @param dateString the date input string with a possible natural time reference.
     * @return the {@code NaturalDay} that corresponds to the time grammar, or null if no match is found.
     */
    public static NaturalDay parseTime(String dateString) {
        dateString = dateString.strip();
        NaturalDay[] times = NaturalDay.values();
        for (int i = MIDNIGHT.ordinal(); i < times.length; i++) {
            if (times[i].hasWord(dateString)) {
                return times[i];
            }
        }
        return null;
    }
}
