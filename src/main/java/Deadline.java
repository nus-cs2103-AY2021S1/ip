public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        tag = "D";
    }

    public Deadline(String description) {
        super(description);
        this.by = null;
        tag = "D";
    }

    @Override
    public String getTaskType() {
        return tag;
    }

    public String toPrint(){
        return by == null
                ? super.toPrint()
                : super.toPrint() + "|" + by;
    }

    @Override
    public String toString() {
        return by == null ? "[D]" + super.toString() : "[D]" + super.toString() + " (by: " + by + ")" ;
    }
}