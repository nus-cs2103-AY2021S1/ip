package Duke.Errors;

import Duke.Errors.DukeException;

/**
 * This EventException is used to print out exceptions when there is an incomplete input where whether the description
 * or date is absent.
 */
public class EventException extends DukeException {
    /**
     * description tests shows whether the description is present in the input of the user or not.
     * If description is not present it is true, else it is false
     */
    private boolean description;
    private boolean oneDate;
    private boolean diff;
    private boolean invalid;
    /**
     * this assigns the variables above to its respective values.
     * @param description input, depending on whether the description is present or not in the input.txt file. If
     *                    present it is false else it is true.
     */
    public EventException(boolean description, boolean oneDate, boolean diff, boolean invalid){
        this.description = description;
        this.oneDate = oneDate;
        this.diff = diff;
        this.invalid = invalid;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     * @return returns a string informing that the description is empty if description is true. Else, if description is
     * false, it means that the date is absent and it informs accordingly.
     */
    public String toString(){
        if(this.description){
            return "  '\u2639' OOPS!!! The description of an event cannot be empty.";
        }
        if(this.oneDate){
            return "  '\u2639' OOPS!!! There should be 2 occurrences of date and/or time values.";
        }
        if(this.diff){
            return "  '\u2639' OOPS!!! Start should be less than end.";
        }
        if(this.invalid){
            return "  '\u2639' OOPS!!! Start and should be of the same format. The formats include " +
                    "yyyy MM dd/ yyyy MM dd, HH:mm/ HH:mm";
        }
        return "  '\u2639' OOPS!!! The specific date of an event cannot be empty." ;
    }
}