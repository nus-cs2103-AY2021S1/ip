public class SaveToStorageErrorException extends StorageException {
    SaveToStorageErrorException() {
        super("OOPS! Something went wrong when saving your tasklist :( \n--Save unsuccessful--");
    }
}
