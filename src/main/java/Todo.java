public class Todo extends Task{
    Todo(int ind, String description) {
        super(ind, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
