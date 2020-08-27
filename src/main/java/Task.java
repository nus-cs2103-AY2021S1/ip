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

    public String missingNameError() {
        return "The description of a task cannot be empty.";
    }

    public Boolean getDoneness() {
        return Boolean.valueOf(this.isDone);
    }

    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        return marked + this.name;
    }
}
