/**
 * A general task to be added
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Task constructor
     * @param description details of the task
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * check whether a task is done
     * @return an icon to shown whether a task is done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * mark a task as done
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * task to be written as string
     * @return string to be presented in a list
     */
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * task to be written in file
     * @return string to be written in a file
     */
    public String write() {
        return this.description;
    }
}
