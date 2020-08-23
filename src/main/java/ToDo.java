public class ToDo extends Task {
    public ToDo(String contents) {
        super(contents);
        super.inputString = this.getClass().getSimpleName().toLowerCase() + " " + contents;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
