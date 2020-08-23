package chatterbox.task;

public class ToDo extends Task {
    public ToDo(String contents) {
        inputString = this.getClass().getSimpleName().toLowerCase() + " " + contents;
        setContents(contents);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
