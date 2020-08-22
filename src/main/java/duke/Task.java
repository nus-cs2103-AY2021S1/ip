package duke;

<<<<<<< HEAD
public abstract class Task {
=======
public class Task {
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

<<<<<<< HEAD
    public int getStatusCode() {
        return this.isDone ? 1 : 0;
    }

=======
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

<<<<<<< HEAD
    public abstract String serialize();

=======
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
