public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String writeSaveFormat() {
        return String.format("T | %d | %s | %s", isDone ? 1 : 0, description, hasTag ? tagName: "");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
