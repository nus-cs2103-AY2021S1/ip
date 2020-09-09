package duke.error;

public class DeleteListEmptyException extends Exception {
    public DeleteListEmptyException() {
        super("    List is empty you cannot delete that item");
    }
}
