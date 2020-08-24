package Task;

import Task.Task;

public class Deadline extends Task {
    public Deadline(int position, String taskDescription) {
        super(position, taskDescription);
    }

    public void setTime(String givenDate) {
        date = givenDate;
    }

    @Override
    public String toString() {
        String base = "[D] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription + "(by:" + date + ")";
        return base;
    }
}
