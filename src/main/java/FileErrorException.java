public class FileErrorException extends DukeException{
    public FileErrorException() {
        super();
    }

    public String toString() {
        return "Sorry, I can't create duke.txt for you :( ";
    }
}
