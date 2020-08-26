package duke;

public class DukeMissingArgumentException extends ArrayIndexOutOfBoundsException {
    private final String DESCRIPTION;

    DukeMissingArgumentException(String description) {
        this.DESCRIPTION = description;
    }

    @Override
    public String toString() {
        return "\t____________________________________________________________\n" +
                "\t☹ OOPS!!! The description of " + DESCRIPTION +" cannot be empty.\n" +
                "\t____________________________________________________________";
    }
}
