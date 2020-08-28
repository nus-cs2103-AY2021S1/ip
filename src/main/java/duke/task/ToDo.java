package duke.task;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }
    
    public ToDo(String task, boolean done) {
        super(task, done);
    }

    @Override
    public String toDataString() {
        return "T // " + (done ? "1" : "0") + " // " + task;
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
