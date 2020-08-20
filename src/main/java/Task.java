public class Task {
    protected final String name;
    protected Boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDoneness(Boolean isDone) {
        this.isDone = isDone;
    }
    public String getName() {
        return String.valueOf(this.name);
    }

    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        return marked + this.name;
    }
}
