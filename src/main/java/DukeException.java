public class DukeException extends RuntimeException {

    public DukeException(String description) {
        super("☹ OOPS!!! " + description);
    }

}
