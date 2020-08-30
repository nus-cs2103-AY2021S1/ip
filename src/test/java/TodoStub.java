import tasks.Task;

public class TodoStub extends Task {
    private String name;
    private boolean isCompleted;

    TodoStub(String name) {
        super(name);
        this.name = name;
        isCompleted = false;
    }

    @Override
    public void setCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        String symbol = isCompleted ? "\u2713" : "x";
        return String.format("[T][%s] %s", symbol, name);
    }
}
