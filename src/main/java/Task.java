public class Task {
    String label;
    boolean done;
    protected final String separator = "|";

    Task(String label, boolean done) {
        this.label = label;
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    protected String getInfo() {
        StringBuilder str = new StringBuilder();
        str.append(separator);
        str.append(done ? "1" : "0");
        str.append(separator);
        str.append(label);
        return str.toString();
    }

    public boolean containsWord(String word) {
        return label.contains(word);
    }

    @Override
    public String toString() {
        String symbol = Character.toString(done ? 10003 : 2717);

        return String.format("[%s] %s", symbol, label);
    }
}
