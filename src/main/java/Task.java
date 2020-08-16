package main.java;

public class Task {
    String taskName;
    Boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void checkOff() {
        this.done = true;
    }

    @Override
    public String toString(){
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[" + finished + "]" + taskName;
        return toReturn;
    }
}
