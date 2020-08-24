public class WriteToStorageException extends DukeException {
    @Override
    public String getMessage() {
        return "Error writing to Store";
    }
}
