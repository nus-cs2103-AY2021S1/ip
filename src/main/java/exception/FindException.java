package exception;

public class FindException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! The keyword of a find cannot be empty.\n";
        return s;
    }
}
