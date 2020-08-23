public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return(isDone ? "\u2713" : "\u2718"); //returns tick or X symbol accordingly
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
