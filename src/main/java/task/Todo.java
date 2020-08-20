package task;

public class Todo extends Task{
    public Todo(String item) throws EmptyStringException{
        super(item);
        taskType = "T";
    }
}
