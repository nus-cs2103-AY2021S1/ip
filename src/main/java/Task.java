public class Task {
    String description;
    boolean isDone;

    Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return isDone ? "[✓]" : "[✗]" + " " + description;
    }
}
