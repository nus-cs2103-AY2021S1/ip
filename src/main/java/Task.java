public class Task {
    protected boolean completed = false;
    protected String name;

    Task (String name) {
        this.name = name;
    }

    public void markDone() {
        completed = true;
    }

    @Override
    public String toString() {
        String completionStatus = completed ? "[✓]" : "[✗]";
        return completionStatus + " " + name;
    }
}
