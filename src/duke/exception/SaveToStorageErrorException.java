package duke.exception;

public class SaveToStorageErrorException extends StorageException {
    public SaveToStorageErrorException() {
        super("OOPS! Something went wrong when saving your tasklist :( \n--Save unsuccessful--");
    }
}
