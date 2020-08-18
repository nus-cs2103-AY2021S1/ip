package main.java;

public class Task {
    private boolean status; //false for Not done; true for done
    private String content;

    public Task(String content) {
        this.status = false;
        this.content = content;
    }

    public void done() {
        this.status = true;
    }

    @Override
    public String toString() {
        return "[" + (status ? "√" : "×") + "] " + content;
    }
}
