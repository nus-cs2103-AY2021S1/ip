public class Task {

    private final String task;

    private Task(String task) {
        this.task = task;
    }

    protected static Task makeTask(String task) {
        return new Task(task);
    }
    
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return this.task;
    }
}
