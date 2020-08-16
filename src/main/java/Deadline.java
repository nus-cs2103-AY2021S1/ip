public class Deadline extends Task {
    String toDoBy;

    Deadline(int ind, String description, String toDoBy) {
        super(ind, description);
        this.toDoBy = toDoBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(FINISH by: " + toDoBy + ")";
    }
}
