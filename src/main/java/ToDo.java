public class ToDo extends Task {

    public ToDo(String in) {
        super(in);
    }

    public String toString() {
        String doneStatus;
        if (isDone) {
            doneStatus = "✓";
        } else {
            doneStatus = "✗";
        }
        return "[T] [" + doneStatus + "] " + task;
    }

    public String saveText() {
        return "T | " + super.saveText();
    }
}
