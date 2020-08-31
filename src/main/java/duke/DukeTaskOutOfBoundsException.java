package duke;

/** Exception for accessing uninitialised Tasks */
public class DukeTaskOutOfBoundsException extends IndexOutOfBoundsException {
    private final String description;

    DukeTaskOutOfBoundsException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\t____________________________________________________________\n"
                + "\t☹ OOPS!!! The task to mark as " + description + " does not exist.\n"
                + "\t____________________________________________________________";
    }
}
