public class FolderErrorException extends DukeException {

    public FolderErrorException() {
        super();
    }

    public String toString() {
        return "Sorry, I can't create data folder for you :( ";
    }

}
