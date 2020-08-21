public class DukeException extends Exception{
    protected DukeException(String msg) {
        super(String.format("☹OOPS!!! %s", msg));
    }
}
