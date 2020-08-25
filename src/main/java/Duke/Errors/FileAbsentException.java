package Duke.Errors;

/**
 * This exception is thrown whenever there is a file absent at a specific path mentioned in the filePath
 */
public class FileAbsentException extends DukeException {
    private String filePath;
    /**
     * This assigns filePath variable to a value
     * @param filePath the value assigned to filePath
     */
    public FileAbsentException(String filePath){
        this.filePath = filePath;
    }

    /**
     * This overrides the toString() method
     * @return a String for the exception
     */
    @Override
    public String toString() {
        return "  The file in this directory " + this.filePath + " is absent!";
    }
}
