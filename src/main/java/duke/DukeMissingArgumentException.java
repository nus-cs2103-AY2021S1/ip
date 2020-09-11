package duke;

/** Exception for missing arguments */
public class DukeMissingArgumentException extends ArrayIndexOutOfBoundsException {
    private final String description;

    DukeMissingArgumentException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\t____________________________________________________\n"
                + "\tOOPS!!! The description of " + description + " cannot be empty.\n"
                + "\t________________________________________________________";
    }
}
