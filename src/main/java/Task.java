public class Task {
    private String name;
    private Boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDoneness(Boolean isDone) {
        this.isDone = isDone;
    }
    public String getName() {
        return this.name; // TODO: throw copy instead
    }

    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        return marked + this.name;
    }
}
