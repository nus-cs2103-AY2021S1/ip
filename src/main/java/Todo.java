public class Todo extends Task {

    public Todo(String description) {
        super(description);
        symbol = "[T]";
    }

    @Override
    public String toString() {
        return (symbol + super.toString());
    }
}
