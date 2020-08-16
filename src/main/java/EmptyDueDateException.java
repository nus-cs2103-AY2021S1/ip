public class EmptyDueDateException extends IllegalArgumentException {

    @Override
    public String toString() {
        return "OOPS! The due date of deadline cannot be empty!";
    }
}
