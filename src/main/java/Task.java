public class Task{
    private String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
    private String description;
    private boolean isDone;

    Task(String message) {
        this.description = message.stripLeading().stripTrailing();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public Task markAsDone() {
        if (!isDone) {
            this.isDone = true;
            System.out.println(lines + " Good Job!!! You cleared this task:\n   ["
                    + this.getStatusIcon() + "] " + this.description + "\n" + lines);
        } else {
            System.out.println(lines
                    + " You have already completed this task! *Woof woof*\n" + lines);
        }
        return this;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
