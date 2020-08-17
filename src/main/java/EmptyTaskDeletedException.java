public class EmptyTaskDeletedException extends IllegalArgumentException {

    public EmptyTaskDeletedException() {
        super("OOPS! Task deleted cannot be empty!");
    }
}
