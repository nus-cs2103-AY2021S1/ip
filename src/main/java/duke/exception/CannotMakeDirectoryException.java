package duke.exception;

/**
 * Exception that occurs when it is impossible to create the desired directory. One possible
 * cause if the permission setting on that directory is not properly configured.
 */
public class CannotMakeDirectoryException extends StorageException {
    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return "  Cannot create directory for storage file! List will not" + "\n"
            + "be saved until the directory and the file are created.";
    }
}
