public class Todo extends Task{
    public Todo(String name, boolean isCompleted) {
        super(name,isCompleted);
    }

    public String getTaskSymbol() {
        return "[T]";
    }
}
