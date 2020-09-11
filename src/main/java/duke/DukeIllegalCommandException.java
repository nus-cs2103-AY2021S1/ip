package duke;

/** Exception for unrecognised commands */
public class DukeIllegalCommandException extends IllegalArgumentException {
    @Override
    public String toString() {
        return "\t________________________________________________________\n"
                + "\tOOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "\t________________________________________________________";
    }
}
