public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    public String toFileStringFormat() {
        return String.format("T / %d / %s",isDone ? 1 : 0, this.desciption);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
