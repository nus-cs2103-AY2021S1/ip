package duke;
public class Todo extends Task{
    public Todo(String name, boolean isCompleted) {
        super(name,isCompleted);
    }

    public String getTaskSymbol() {
        return "[T]";
    }

    public String storeFormat() {
        return String.format("%s %s %s",this.getTaskSymbol(),this.isDone(),this.description);
    }
}
