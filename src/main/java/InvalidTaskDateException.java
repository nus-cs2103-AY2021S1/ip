public class InvalidTaskDateException extends DukeException{

    public InvalidTaskDateException() {
        super("OOPS!!! Please use the format \"dd/MM/yyyy\" to indicate the date.");
    }
}
