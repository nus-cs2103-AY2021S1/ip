package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void updateTask(int check) {
        if (check == 1) {
            isDone = true;
        } else {
            isDone = false;
        }
    }

    @Override
    public String toString() {
        return (isDone ? "[" + "\u2713" + "]" + " " + this.description
                : "[" + "\u2718" + "]" + " " + this.description);
    }
}
