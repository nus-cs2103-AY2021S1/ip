public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        String sign = done ? "✓" : "✗";
        return "[T][" + sign + "] " + description;
    }
}
