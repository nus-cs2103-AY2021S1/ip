public class Todo extends Task {
    Todo(String name) {
        super(name);
    }
    
    Todo(String name, Boolean isDone) {
        super(name, isDone);
    }
    public String encode() {
        return isDone
                ? String.format("T | 1 | %s", name)
                : String.format("T | 0 | %s", name);
    }
    
    public String toString() {
        return "[T]" + super.toString();
    }
}