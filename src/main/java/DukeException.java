import java.util.NoSuchElementException;

public class DukeException extends NoSuchElementException {
    String description;

    DukeException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
