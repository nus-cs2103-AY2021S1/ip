package main.java;

public class Task {
    protected boolean done;
    protected String task;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return task;
    }

    public void setTaskDone(boolean boo){
        done = boo;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" + " " + task;
    }
}
