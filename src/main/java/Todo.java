public class Todo extends Task {

    // Constructor
    public Todo(String description) {
        super(description);
    }

    // String Representation
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
