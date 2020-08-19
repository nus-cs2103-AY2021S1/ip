public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
