package main.java;

public class Task {
    private String title;
    private boolean isDone = false;

    Task(String title){
        this.title = title;
    }

    private String doneToString(){
        return isDone ? "[✓]": "[✗]";
    }

    public void markDone(){
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return doneToString() + " " + title;
    }
}
