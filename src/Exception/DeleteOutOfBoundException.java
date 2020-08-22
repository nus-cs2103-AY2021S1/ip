package Exception;


public class DeleteOutOfBoundException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! There are no such task.\n";
        return s;
    }
}
