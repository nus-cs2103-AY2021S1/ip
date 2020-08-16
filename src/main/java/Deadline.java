package main.java;

public class Deadline extends Task{
    String date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[D]" + "[" + finished + "] " + taskName + " (by: " + date +")";
        return toReturn;
    }
}
