package duke.storage;

public class FileWritingException extends Exception {
    private Exception originalException;
    public FileWritingException(Exception e) {
        originalException = e;
    }

    public Exception getOriginalException() {
        return originalException;
    }
}
