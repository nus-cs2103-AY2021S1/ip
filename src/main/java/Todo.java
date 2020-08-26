public class Todo extends Task {
    Todo(String name) {
        super(name);
        taskType = "T";
    }
    
    Todo(String name, Boolean isDone) {
        super(name, isDone);
        taskType = "T";
    }
    public String encode() {
        return isDone
                ? String.format("T | 1 | %s", name)
                : String.format("T | 0 | %s", name);
    }
    
    @Override
    public String toString() {
        return String.format("[%s]%s", taskType, super.toString());
    }
}