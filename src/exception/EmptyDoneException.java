package exception;

public class EmptyDoneException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The index of what is done cannot be empty.";
    }
}
