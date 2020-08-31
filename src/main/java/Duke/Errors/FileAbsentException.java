package Duke.Errors;

/**
 * This exception is thrown whenever there is a file absent at a specific path mentioned in the filePath
 */
public class FileAbsentException extends DukeException {
    private String filePathAbsent;
    /**
     * This assigns filePath variable to a value
     * @param filePathAbsent the value assigned to filePath
     */
    public FileAbsentException(String filePathAbsent){
        this.filePathAbsent = filePathAbsent;
    }

    /**
     * This overrides the toString() method
     * @return a String for the exception is printed.
     */
    @Override
    public String toString() {
        return "  The file in this directory " + this.filePathAbsent + " is absent!";
    }
}
