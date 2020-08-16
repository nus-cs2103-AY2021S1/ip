enum TaskType {
    ToDo,
    Deadline,
    Event
}

public class Task {
    protected String name;
    private boolean done;
    private TaskType type;

    public Task(String name, TaskType type) {
        this.name = name;
        this.done = false;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public TaskType getType() {
        return type;
    }
    
    public void done() {
        this.done = true;
    }    
}


