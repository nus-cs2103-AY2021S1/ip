package main.java.emily.task;

public class Task {
    protected String description;
    protected boolean finished;
    protected String type;

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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
