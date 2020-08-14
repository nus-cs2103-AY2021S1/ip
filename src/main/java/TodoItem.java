public class TodoItem {
    String label;
    boolean done;

    TodoItem(String label) {
        this.label = label;
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String symbol = Character.toString(done ? 10003 : 2717);

        return String.format("[%s] %s", symbol, label);
    }
}
