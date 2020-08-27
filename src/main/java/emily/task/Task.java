package main.java.emily.task;

/**
 * Represents the basic structure of a sample task from the user input.
 * A task will have a string of description detail.
 */
public class Task {
    protected String description;
    protected boolean finished;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.finished = false;
    }

    public void setFinished(boolean b){
        this.finished = b;
    }

    public boolean isFinished(){
        return this.finished;
    }

    public String getDescription(){
        return this.description;
    }

    public String getStatusIcon() {
        return (finished ? "\u2713" : "\u2718");
    }

    public char getType() { return this.type; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
