public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    
    @Override
    public String writeMessage() {
        String done = "";
        if (this.isDone) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("T | %s | %s", done, this.description);
    }

    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "✓" : "✗") + "] " + this.description;
    }
}
