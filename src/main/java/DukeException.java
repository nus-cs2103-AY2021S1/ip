public class DukeException {

    private final String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
