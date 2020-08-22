public class Todo extends Task {
    public Todo(String description, String done) {
        super(description, done);
    }

    public String[] getStringArr() {
        String[] arr = {"T", super.done, super.description};
        return arr;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}