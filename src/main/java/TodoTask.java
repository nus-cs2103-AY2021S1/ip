public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String fileString() {return "T | " + super.toString(); }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
