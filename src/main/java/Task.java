public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }

    public void markAsDone(){
        System.out.println("Nice! I've marked this task as done: " + "\n" + "[\u2713]" + this.description);
        this.isDone = true;
    }
}
