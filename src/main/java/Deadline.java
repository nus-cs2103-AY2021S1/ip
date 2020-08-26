import java.util.Optional;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    String getStringType() {
        return "D";
    }

    @Override
    Optional<String> getDate() {
        return Optional.of(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
