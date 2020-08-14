public class DeletionEmptyException extends  Exception{
    DeletionEmptyException() {
        super("☹ OOPS!!! The deletion index cannot be empty.");
    }
}
