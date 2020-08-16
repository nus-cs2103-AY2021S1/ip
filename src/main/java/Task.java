public class Task {
    private int number;
    private String description;
    private boolean isDone;

    Task(int number, String description) {
        this.number = number;
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return isDone ? "[\u2713] " : "[\u2718] ";
    }

    @Override
    public String toString() {
        return String.format("%d.", number) + getStatus() + description;
    }
}
