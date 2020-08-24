package tasks;

public class Todo extends Task {
    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeString() {
        return "T # " + super.writeString();
    }
}
