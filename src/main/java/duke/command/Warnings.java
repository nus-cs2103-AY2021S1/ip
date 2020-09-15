package duke.command;

/**
 * Warning class to encapsulate all the error responses.
 */
public class Warnings {
    public static final String HORIZONTAL_LINE = "____________________________________\n";

    /**
     * Warns user that Deadline input is invalid.
     * @return A warning message
     */
    public static String invalidDeadlineWarning() {
        return HORIZONTAL_LINE + "Sorry hor, I can't understand your deadline input\n" + HORIZONTAL_LINE;
    }

    /**
     * Warns user that the number input is out of range.
     * @return A warning message
     */
    public static String inputOutOfRangeWarning() {
        return HORIZONTAL_LINE + "Sorry hor, the number you keyed in is out of range...\n" + HORIZONTAL_LINE;
    }

    /**
     * Warns user that the input is invalid.
     * @return A warning message
     */
    public static String invalidInputWarning() {
        return HORIZONTAL_LINE + "Sorry hor, the number you keyed in is invalid...\n" + HORIZONTAL_LINE;
    }

    /**
     * Warns user that Event input is invalid.
     * @return A warning message
     */
    public static String invalidEventWarning() {
        return HORIZONTAL_LINE + "Sorry hor, I can't understand your event input\n" + HORIZONTAL_LINE;
    }

    /**
     * Warns user that an invalid command has been registered.
     * @return A warning message
     */
    public static String invalidCommandWarning() {
        return HORIZONTAL_LINE + "AIYO!!! I don't understand what you want leh! Can try again?\n" + HORIZONTAL_LINE;
    }

    /**
     * Warns user that Todo input is invalid.
     * @return A warning message
     */
    public static String invalidTodoWarning() {
        return HORIZONTAL_LINE + "Sorry hor, I can't understand your todo input\n" + HORIZONTAL_LINE;
    }

    /**
     * Warns user to include '#' in front of tag.
     * @return A warning message
     */
    public static String invalidTagWarning() {
        return HORIZONTAL_LINE + "Sorry hor, please make sure you have '#' in front of your tag\n" + HORIZONTAL_LINE;
    }

    /**
     * Warns user of wrong findtag format.
     * @return A warning message
     */
    public static String invalidFindTagWarning() {
        return HORIZONTAL_LINE + "Sorry hor, I can't understand your findtag input\n" + HORIZONTAL_LINE;
    }

    /**
     * Warns user of wrong find format.
     * @return A warning message
     */
    public static String invalidFindWarning() {
        return HORIZONTAL_LINE + "Sorry hor, I can't understand your find input\n" + HORIZONTAL_LINE;
    }
}
