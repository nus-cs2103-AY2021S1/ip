package exception;

public class EmptyDeleteException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The index of what to be deleted cannot be empty.";
    }
}
