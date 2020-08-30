public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }
    public TodoTask(String description, boolean isCompleted) {
        super(description);
        this.isCompleted = isCompleted;
    }

    @Override
    public String fileString() {return "T | " + super.fileString(); }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
