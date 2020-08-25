public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStoringFormat() {
        return "T " + super.getStoringFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
