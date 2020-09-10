package duke.exception;

/** Exception that occurs when the data from the file from the hard disk has an invalid format. **/
public class InvalidFormatFromHardDiskException extends StorageException {
    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return "  Invalid format file from hard disk!";
    }
}
