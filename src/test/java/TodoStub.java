public class TodoStub extends Task {
    private String name;
    private boolean isCompleted;

    TodoStub(String name) {
        super(name);
        this.name = name;
        isCompleted = false;
    }

    @Override
    public void markDone() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        String symbol = isCompleted ? "\u2713" : "x";
        return String.format("[T][%s] %s", symbol, name);
    }
}
