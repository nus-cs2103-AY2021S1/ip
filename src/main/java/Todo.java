public class Todo extends Task {

    Todo(String name) {
        super(name, Duke.TaskType.TODO);
    }

    Todo(String name, boolean done) {
        super(name, Duke.TaskType.TODO, done);
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        return "[T]" + "[" + doneString + "] " + this.name;
    }
}
