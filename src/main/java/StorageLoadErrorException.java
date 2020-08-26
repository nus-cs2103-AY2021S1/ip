public class StorageLoadErrorException extends StorageException {
    StorageLoadErrorException() {
        super("Sorry but I am unable to load the saved tasklist. " 
            + "\nAny changes to your tasklist will not be saved :(");
    }
}
