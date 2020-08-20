public class Deadline extends Task {
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
        typeOfTask = TypeOfTask.DEADLINE;
    }

    @Override
    public String getDesc() {
        return desc + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString() + " (by: " + by + ")";
    }
}
