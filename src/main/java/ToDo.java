public class ToDo extends Task {
    ToDo(String taskDescription) {
        super(taskDescription);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
