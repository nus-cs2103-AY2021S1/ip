public class Task {

    public String description;
    public boolean isDone;

    public Task(String task, boolean isDone) {

        description = task;
        this.isDone = isDone;

    }

    public void addTask(String task) {
        System.out.println("added : " + task);
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

}
