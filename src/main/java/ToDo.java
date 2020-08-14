public class ToDo extends Task {
    ToDo(String task) {
        super(task);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
