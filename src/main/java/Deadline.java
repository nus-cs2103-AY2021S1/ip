public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String saveString() {
        if (this.isDone) {
            return "T , 1 , " + description + " , " + by;
        }
        else {
            return "T , 0 , " + description + " , " + by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
