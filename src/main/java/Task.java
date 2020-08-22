package main.java;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String writeToFile() {
        String result = "T # ";
        if(isDone) {
            result += "1 # ";
        } else {
            result += "0 # ";
        }
        result += description;
        return result;
    }

    public void changeIsDone() {
        this.isDone = !this.isDone;
    }
}