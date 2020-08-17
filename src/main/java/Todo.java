public class Todo extends Task {

    // Constructor
    public Todo(String description) throws EmptyBodyException {
        super(description);
    }

    // String Representation
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
