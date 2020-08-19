public class ToDo extends Task {
    public ToDo(String contents) {
        super(contents);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
