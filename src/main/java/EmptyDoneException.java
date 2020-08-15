public class EmptyDoneException extends InvalidCommandException {
    public EmptyDoneException() {
        super("\u2639 OOPS!!! The task to mark as done cannot be empty.");
    }
}
