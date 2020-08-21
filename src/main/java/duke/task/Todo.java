public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int status) {
        super(description, status);
    }

    public String saveText(int status) {
        return "T | " + status + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
