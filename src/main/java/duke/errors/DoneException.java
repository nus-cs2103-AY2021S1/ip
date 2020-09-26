package duke.errors;

/**
 * This DoneException is used to print out exceptions when there is an incomplete input where the iD is absent or the iD
 * of that Task hasnt been defined yet.
 */
public class DoneException extends DukeException {
    /**
     * IDabsent tests shows whether the iD is present in the input of the user or not.
     * If ID is not present it is true, else it is false
     */
    private boolean isiDAbsent; //true if iD is not given by user, false otherwise
    private boolean isNotiDDefined; //true if iD is not defined yet, false otherwise
    private boolean isDone; //true if task is already completed, false otherwise

    /**
     * constructor that assigns tne 2 variables its respective values
     * @param isiDabsent input, depending on whether the iD is present or not in the input.txt file.
     * If present it is false else it is true.
     * @param isNOtiDDefined input, true if ID > number of tasks present, false otherwise.
     */
    public DoneException(boolean isiDabsent, boolean isNOtiDDefined, boolean isDone) {
        this.isiDAbsent = isiDabsent;
        this.isNotiDDefined = isNOtiDDefined;
        this.isDone = isDone;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     *
     * @return returns a string depending on the scenario. If the IDabsent is true,
     * then description that the description of
     * done cannot be empty.Else if isDefined is true then String returning that iD is not defined is returned. Else,
     *  default is returned which should not occur.
     */
    @Override
    public String toString() {
        if (isiDAbsent) {
            return iDAbsent(); //when ID is absent
        } else if (isNotiDDefined) {
            return iDNotDefined(); //when ID is not defined
        } else if (isDone) {
            return isDone();
        } else {
            return "default";
        }
    }

    /**
     * Returns when iD is not given by user
     *
     * @return String saying that description of done(a number) cannot be empty
     */
    private String iDAbsent() {
        return "  '\u2639' OOPS!!! The description of done cannot be empty.";
    }

    /**
     * Returns when ID input by user is not defined yet
     *
     * @return String saying that ID is not defined.
     */
    private String iDNotDefined() {
        return "  '\u2639' OOPS!!! The ID is not yet defined.";
    }

    private String isDone() {
        return "You have already completed this task";
    }
}
