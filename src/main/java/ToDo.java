public class ToDo extends Task {
    public ToDo(String contents) {
        setContents(contents);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
