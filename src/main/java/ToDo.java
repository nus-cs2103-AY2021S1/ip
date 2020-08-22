public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getData() {
        return "t/" + super.getData();
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
