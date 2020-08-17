public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
        System.out.println(" ____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println(" ____________________________________________________________");

    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString(){
            return "[" + getStatusIcon()+ "]" + " " + description;
    }

}
