package main.java;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() throws TaskDoneException{
        if (isDone == true) {
            throw new TaskDoneException();
        }
        this.isDone = true;
    }

    @Override
    public String toString() {
        String icon = isDone ? "\u2713" : "\u2718";
        return "[" + icon + "] " + name;
    }
}
