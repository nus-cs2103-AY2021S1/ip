package duke;

/** Exception for accessing uninitialised Tasks */
public class DukeTaskOutOfBoundsException extends IndexOutOfBoundsException {
    private final String DESCRIPTION;

    DukeTaskOutOfBoundsException(String description) {
        this.DESCRIPTION = description;
    }

    @Override
    public String toString() {
        return "\t____________________________________________________________\n" +
                "\t☹ OOPS!!! The task to mark as " + DESCRIPTION + " does not exist.\n" +
                "\t____________________________________________________________";
    }
}
