
public class Task {
    private final String name;
    private boolean doneState;

    public Task(String name) {
        this.name = name;
        doneState = false;
    }

    private String doneTag() {
        return doneState ? "[\u2713]" : "[\u2718]";
    }

    public void setDone() {
        doneState = true;
    }

    @Override
    public String toString() {
        return String.format("%s %s", doneTag(), name);
    }
}
