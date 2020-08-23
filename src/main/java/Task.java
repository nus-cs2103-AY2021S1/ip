public class Task {
    private final String contents;
    private boolean isDone = false;
    protected String inputString;

    public Task(String contents) {
        this.contents = contents;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getInputString() {
        return inputString;
    }

    public String toString() {
        return (isDone ? "[✓]" : "[✗]") + " " + contents;
    }
}
