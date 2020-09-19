// partial solution copied from iP
public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description.trim(), isDone);
    }

    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }
}
