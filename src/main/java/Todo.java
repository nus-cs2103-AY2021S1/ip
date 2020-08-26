public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }
    
    @Override
    public String writeMessage() {
        String done = "";
        if (this.done) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("T | %s | %s", done, this.task);
    }

    @Override
    public String toString() {
        return "[T][" + (this.done ? "✓" : "✗") + "] " + this.task;
    }
}
