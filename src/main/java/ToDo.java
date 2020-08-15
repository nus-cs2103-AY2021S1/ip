public class ToDo extends Task {
    public ToDo(String details) {
        super(details);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
