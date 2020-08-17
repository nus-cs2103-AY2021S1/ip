public class DukeException extends Exception {
    private String description;

    DukeException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
