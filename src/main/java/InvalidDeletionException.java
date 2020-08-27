public class InvalidDeletionException extends Exception {
    InvalidDeletionException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Deletion index is invalid.";
    }
}