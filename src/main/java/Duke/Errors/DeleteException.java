package Duke.Errors;

/**
 * This DeleteException is used to print out exceptions when there is an incomplete input where the ID is absent or the ID
 * of that Task hasn't been defined yet or it has previously been deleted.
 */
public class DeleteException extends DukeException {
    /**
     * IDabsent tests shows whether the ID is present in the input of the user or not.
     * If ID is not present it is true, else it is false
     * deleted checks whether the task was previously deleted, if deleted it is true else false.
     */
    private boolean isIDAbsent;
    private boolean isNotIDDefined;

    /**
     * constructor that assigns tne 2 variables its respective values
     *
     * @param isIDabsent     input, true if ID is input my reader, false otherwise.
     * @param isNOtIDDefined input, true if ID > number of tasks present, false otherwise.
     */
    public DeleteException(boolean isIDabsent, boolean isNOtIDDefined) {
        this.isIDAbsent = isIDabsent;
        this.isNotIDDefined = isNOtIDDefined;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method, for printing in getMessage().
     *
     * @return returns a string depending on the scenario. If the IDAbsent is true, then description that the description of
     * delete cannot be empty. Else if isDefined is true then String returning that ID is not defined is returned. Else,
     * default is returned which should not occur.
     */
    @Override
    public String toString() {
        if (isIDAbsent) {
            return iDAbsent(); //when ID is not input by user
        } else if (isNotIDDefined) {
            return iDNotDefined(); //when ID is not defined yet
        } else {
            return "default";
        }
    }

    /**
     * returns on condition when user didn't mention the task ID to delete
     *
     * @return String informing user that ID of task to be deleted is not mentioned
     */
    private String iDAbsent() {
        return "  '\u2639' OOPS!!! The description of delete cannot be empty.";
    }

    /**
     * returns on condition when the ID is more than the number of tasks present
     *
     * @return String informing user that ID of task to be deleted is not defined yet.
     */
    private String iDNotDefined() {
        return "  '\u2639' OOPS!!! The ID is not yet defined.";
    }
}