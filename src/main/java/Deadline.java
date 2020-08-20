public class Deadline extends Task {

    protected String by;

    public Deadline(String description) throws DukeException {
        super(description.substring(8),"deadline");
        this.by = description.substring(description.indexOf("/")+4);
        this.setType("deadline");

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}