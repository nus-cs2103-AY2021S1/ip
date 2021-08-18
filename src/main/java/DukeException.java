public class DukeException extends RuntimeException {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("Oops, %s :(", this.getMessage());
    }
}
