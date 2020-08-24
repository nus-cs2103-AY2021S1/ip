package Duke.Errors;

import Duke.Errors.DukeException;

public class FileAbsentException extends DukeException {
    String filePath;
    public FileAbsentException(String filePath){
        this.filePath = filePath;
    }
    @Override
    public String toString() {
        return "  The file in this directory " + this.filePath + " is absent!";
    }
}
