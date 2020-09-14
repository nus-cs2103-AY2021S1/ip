package duke.errors;

/**
 * This EventException is used to print out exceptions when there is an incomplete input where whether the description
 * or date is absent.
 */
public class EventException extends DukeException {
    /**
     * description tests shows whether the description is present in the input of the user or not.
     * If description is not present it is true, else it is false
     */
    private boolean isDescriptionAbsent; //true if the user input does not have description, else false
    private boolean isEndTimeAbsent; //true if end time is not given by user, false otherwise
    private boolean isStartAfterEnd; //true if start>end, false otherwise
    private boolean isDateTimeWrongFormat; //true if date and/ or time is in wrong format, false otherwise
    private boolean isStartDateTimeEmpty; //true if start date and/ or time is not given by user, false otherwise

    /**
     *
     * @param isDescriptionAbsent input, depending on whether the description is given by user.
     * @param isEndTimeAbsent is true when the user has no input for end time
     * @param isStartAfterEnd is true when the start time is after end time
     * @param isDateTimeWrongFormat is true when date and/or time is input in wrong format.
     * @param isStartDateTimeEmpty is true when date abd/or time is input in wrong format
     */
    public EventException(boolean isDescriptionAbsent, boolean isEndTimeAbsent, boolean isStartAfterEnd,
                          boolean isDateTimeWrongFormat, boolean isStartDateTimeEmpty) {
        this.isDescriptionAbsent = isDescriptionAbsent;
        this.isEndTimeAbsent = isEndTimeAbsent;
        this.isStartAfterEnd = isStartAfterEnd;
        this.isDateTimeWrongFormat = isDateTimeWrongFormat;
        this.isStartDateTimeEmpty = isStartDateTimeEmpty;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     * @return returns a string informing that the description is empty if description is true.
     * If isEndTimeAbsent is absent is true, end time is absent and then a
     * description mentioning this would be returned.
     * Else, if isStartAfterEnd is true, start would be more than end then a
     * description describing this would be printed
     * Else if ifDateWrongFormat is true, then the date is in wrong format and
     * a description describing it would be printed
     * Else if isDateEmpty is true, then String giving that is returned.
     * Else default is returned.
     */
    public String toString() {
        if (this.isDescriptionAbsent) {
            return descriptionAbsent(); //when description is not given by user
        } else if (this.isEndTimeAbsent) {
            return endTimeAbsent(); //when end time is not given by user
        } else if (this.isStartAfterEnd) {
            return startAfterEnd(); //when start is after end
        } else if (this.isDateTimeWrongFormat) {
            return dateTimeWrongFormat(); //when date is given in wrong format
        } else if (this.isStartDateTimeEmpty) {
            return startDateTimeEmpty(); //when start date and/or time is absent
        } else {
            return "default";
        }
    }

    /**
     * Returns when description of Event is absent
     *
     * @return String saying that description of Event is absent.
     */
    private String descriptionAbsent() {
        return "  '\u2639' OOPS!!! The description of an Event cannot be empty.";
    }

    /**
     * Returns when endTime is not given by user
     *
     * @return String saying end time is not given by user
     */
    private String endTimeAbsent() {
        return "  '\u2639' OOPS!!! There should be 2 occurrences of date and/or time values.";
    }

    /**
     * Returns when start time is more than end
     *
     * @return String saying that start < end
     */
    private String startAfterEnd() {
        return "  '\u2639' OOPS!!! Start should be less than end.";
    }

    /**
     * Returns when date and/or time is in wrong format
     *
     * @return String saying that date and/or time is in wrong format and the format it should be in
     */
    private String dateTimeWrongFormat() {
        return "  '\u2639' OOPS!!! Start and should be of the same format. The formats include "
                + "yyyy MM dd/ yyyy MM dd, HH:mm/ HH:mm";
    }

    /**
     * Returns when start date and/or time is empty
     *
     * @return String saying that start date and/or time is absent
     */
    private String startDateTimeEmpty() {
        return "  '\u2639' OOPS!!! The specific date and/or time of an Event cannot be empty.";
    }
}
