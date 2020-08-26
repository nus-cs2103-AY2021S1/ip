package duke.Tasks;

//Adapted from CS2103T website's duke.Tasks.Task class
public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone){
        this.isDone = isDone;
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getTask() {
        return this.description;
    }

    public boolean getStatus(){
        return isDone;
    }
    public String getOriginal(){
        return "task " + getTask();
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + getTask();
    }
}
