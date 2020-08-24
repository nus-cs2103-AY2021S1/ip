public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String fileString() {return "T | " + super.fileString(); }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
