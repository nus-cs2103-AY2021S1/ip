public class Task {
    private final String DONE = "[✓]";
    private final String NOT_DONE = "[✗]";
    private final String name;
    private boolean doneState;


    public Task(String name) {
        this.name = name;
        doneState = false;
    }

    public void setDone() {
        doneState = true;
    }

    @Override
    public String toString() {
        return String.format("%s %s", doneState ? DONE : NOT_DONE, name);
    }
}
