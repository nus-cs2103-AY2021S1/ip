public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String write() {
        if (isDone) {
            return 1 + "," + description;
        }
        return 0 + "," + description;
    }

//    public static void main(String[] args) {
//        Task task = new Task("read book");
//        task.markAsDone();
//        System.out.println(task);
//    }
}
