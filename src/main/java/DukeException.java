public class DukeException extends RuntimeException {

    DukeException(String message) {
        super(String.format("Gomen nasai~ %s\n", message));
    }
}
