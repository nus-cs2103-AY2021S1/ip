public class Task{
    private String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.";
    private int ind;
    private String description;
    private boolean isDone;

    Task(int ind, String message) {
        this.ind = ind;
        this.description = message;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public Task markAsDone() {
        if (!isDone) {
            this.isDone = true;
            System.out.println(lines + "\n Good Job!!! You cleared this task:\n  ["
                    + this.getStatusIcon() + "] " + this.description + "\n" + lines);
        } else {
            System.out.println(lines
                    + "\n You have already completed this task! *Woof woof*\n" + lines);
        }
        return this;
    }

    @Override
    public String toString() {
        return this.ind + ". [" + this.getStatusIcon() + "] " + this.description;
    }
}
