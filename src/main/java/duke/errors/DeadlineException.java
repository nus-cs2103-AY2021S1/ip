package duke.errors;

/**
 * This DeadlineException is used to print out exceptions when there is an incomplete input where wether the description
 * or date is absent.
 */

public class DeadlineException extends DukeException {
    /**
     * descriptionPresent tests shows whether the description is present in the input of the user or not.
     * If description is not present it is true, else it is false
     */
    private boolean isDescriptionAbsent; //true if description for deadline keyword is not given, false otherwise
    private boolean isDateTimeFormatWrong; //true if date and/ or time is given in wrong format by user, false otherwise
    private boolean isDateTimeAbsent; //true if date is not given by user, false otherwise

    /**
     * constructor for deadline exception that assigns description and format values
     *
     * @param isDescriptionAbsent true if description absent
     * @param isDateTimeFormatWrong true if format is wrong
     * @param isDateTimeAbsent true if date and time are absent.
     */
    public DeadlineException(boolean isDescriptionAbsent, boolean isDateTimeFormatWrong, boolean isDateTimeAbsent) {
        this.isDescriptionAbsent = isDescriptionAbsent;
        this.isDateTimeFormatWrong = isDateTimeFormatWrong;
        this.isDateTimeAbsent = isDateTimeAbsent;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     *
     * @return returns a string informing that the description is empty if descriptionAbsent is true. Else, it tests
     * whether the format is wrong, if it then a string describing it would be returned.
     * If isDateTimeAbsent is true then
     * date time for deadline is absent and a String describing it would be returned.
     * Else then default returns which
     * should not occur.
     */
    public String toString() {
        if (this.isDescriptionAbsent) {
            return descriptionAbsent(); //when descriptionAbsent
        } else if (this.isDateTimeFormatWrong) {
            return dateTimeFormatWrong(); //when dateTimeFormat is wrong
        } else if (this.isDateTimeAbsent) {
            return dateTimeAbsent(); //when dateTime is absent
        } else {
            return "default";
        }
    }

    /**
     * returns String on condition where description for Deadline is absent
     *
     * @return String informing user that the description for Deadline is absent
     */
    private String descriptionAbsent() {
        return "  '\u2639' OOPS!!! The description of a deadline cannot be empty.";
    }

    /**
     * returns String on condition where format for date and/or time format for Deadline is wrong
     *
     * @return String informing user format for date and/or time format for Deadline is wrong
     */
    private String dateTimeFormatWrong() {
        return "  '\u2639' OOPS!!! The formats of date and/ or include "
                + "yyyy MM dd/ yyyy MM dd, HH:mm/ HH:mm";
    }

    /**
     * returns String on condition where date and/or time for Deadline is absent
     *
     * @return String informing user that the date and/or time for Deadline is absent
     */
    private String dateTimeAbsent() {
        return "  '\u2639' OOPS!!! The specific date/time of a deadline cannot be empty.";
    }
}
