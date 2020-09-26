package duke.errors;

/**
 * This TodoException is used to print out exceptions when there is an incomplete input where whether the description
 * or date is absent.
 */
public class TodoException extends DukeException {
    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     *
     * @return returns a string informing that the description of the ToDo is absent as it cant be.
     */
    public String toString() {
        return descriptionAbsent(); //when description is absent
    }

    /**
     * Returns when description is absent
     *
     * @return String informign user that the description of ToDo cannot be empty.
     */
    private String descriptionAbsent() {
        return "  '\u2639' OOPS!!! The description of a ToDo cannot be empty";
    }
}

