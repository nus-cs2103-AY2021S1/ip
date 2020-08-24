package duke.task;

public class Task {

    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[o]" : "[x]"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public int ifDone() {
        if(this.isDone){
            return 1;
        }
        else return 0;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public String splitToString() {
        return  this.getStatusIcon() + "|" + this.description;
    }

}