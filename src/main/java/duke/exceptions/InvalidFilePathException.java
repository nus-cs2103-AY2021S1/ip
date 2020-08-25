package duke.exceptions;

public class InvalidFilePathException extends DukeException {
    public InvalidFilePathException(){
        super("Invalid file path! Please make sure that the file path ends with a .txt");
    }
}
