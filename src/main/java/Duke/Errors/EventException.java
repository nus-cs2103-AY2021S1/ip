package Duke.Errors;

/**
 * This EventException is used to print out exceptions when there is an incomplete input where whether the description
 * or date is absent.
 */
public class EventException extends DukeException {
    /**
     * description tests shows whether the description is present in the input of the user or not.
     * If description is not present it is true, else it is false
     */
    private boolean descriptionAbsent;
    private boolean endTimeAbsent;
    private boolean startAfterEnd;
    private boolean dateWrongFormat;

    /**
     *
     * @param descriptionAbsent input, depending on whether the description is present or not in the input.txt file. If
     *                          present it is false else it is true.
     * @param endTimeAbsent is true when the user has no input for end time
     * @param startAfterEnd is true when the start time is after end time
     * @param dateWrongFormat is true when date is not input by the user.
     */
    public EventException(boolean descriptionAbsent, boolean endTimeAbsent, boolean startAfterEnd, boolean dateWrongFormat){
        this.descriptionAbsent = descriptionAbsent;
        this.endTimeAbsent = endTimeAbsent;
        this.startAfterEnd = startAfterEnd;
        this.dateWrongFormat = dateWrongFormat;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     * @return returns a string informing that the description is empty if description is true.
     * If endTime is absent is true, end time is absent and then a description mentioning this would be returned.
     * Else, if startAfterEnd is true, start would be more than end then a description describing this would be printed
     * Else if dateWrongFormat is true, then the date is in wrong format and a description describing it would be printed
     * Else then the date is absent and then the description describing it would be printed.
     */
    public String toString(){
        if(this.descriptionAbsent){
            return "  '\u2639' OOPS!!! The description of an Event cannot be empty.";
        }
        if(this.endTimeAbsent){
            return "  '\u2639' OOPS!!! There should be 2 occurrences of date and/or time values.";
        }
        if(this.startAfterEnd){
            return "  '\u2639' OOPS!!! Start should be less than end.";
        }
        if(this.dateWrongFormat){
            return "  '\u2639' OOPS!!! Start and should be of the same format. The formats include " +
                    "yyyy MM dd/ yyyy MM dd, HH:mm/ HH:mm";
        }
        return "  '\u2639' OOPS!!! The specific date of an Event cannot be empty." ;
    }
}