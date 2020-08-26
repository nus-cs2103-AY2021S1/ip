package duke.task;

public class ToDos extends Task {

    protected String by;

    public ToDos(String description, String by) {
        super(description);
        this.by = by;
    }

    public ToDos(String description) {
        super(description);
        this.by = null;
    }


    @Override
    public String toString() {
        return by == null
                ? "[T]" + super.toString()
                : "[T]" + super.toString() + " (by: " + by + ")";
    }
}