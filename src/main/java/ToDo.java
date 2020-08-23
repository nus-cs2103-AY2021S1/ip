public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    ToDo(String description, String completionStatus) {
        super(description, completionStatus);
    }

    @Override
    String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
