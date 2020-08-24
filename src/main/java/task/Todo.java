package task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String status = String.format("[T][%s] ", (super.done ? "✓" : "✗"));
        return status + this.getName();
    }


}
