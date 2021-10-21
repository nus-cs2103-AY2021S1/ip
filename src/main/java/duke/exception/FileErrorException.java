package duke.exception;

public class FileErrorException extends DukeException {

    public FileErrorException() {
        super("Sorry, I can't create txt file for you :( ");
    }

}
