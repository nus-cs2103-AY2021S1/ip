public class Deadline extends Task{

    public Deadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
