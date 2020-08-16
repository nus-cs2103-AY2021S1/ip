public class EmptyTaskDeletedException extends IllegalArgumentException {

    @Override
    public String toString() {
        return "OOPS! Task deleted cannot be empty!";
    }
}
