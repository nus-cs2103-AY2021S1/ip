public class Task {

    public String description;
    public boolean isDone;
    public String type;

    public Task(String task, boolean isDone) {

        description = task;
        this.isDone = isDone;
        type = null;

    }

    public void addTask(String task) {
        System.out.println("added : " + task);
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

}
