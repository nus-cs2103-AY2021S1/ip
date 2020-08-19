public class ToDo extends Task {
    static final String TYPE = "todo";

    ToDo(String description) {
        super(description, TYPE);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
