package main.java;

public class DeadlineTask extends Task {
    private String date;
    DeadlineTask(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
