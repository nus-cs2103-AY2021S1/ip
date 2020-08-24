public class Todo extends Task{
    public Todo(String description, boolean isDone) {
        super(description, isDone);
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

    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "T | " + doneOrNot + " | " + this.name;
    }
}
