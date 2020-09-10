package duke.exception;

/**
 * Exception that occurs when it is impossible to create a file on the desired directory. One possible
 * cause if the permission setting on that directory is not properly configured.
 **/
public class CannotCreateFileException extends StorageException {
    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return "  Cannot create storage file! List will not be saved until the "
            + "file is created";
    }
}
