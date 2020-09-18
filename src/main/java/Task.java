public class Task {
    protected final String name;
    protected Boolean isDone;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDoneness(Boolean isDone) {
        this.isDone = isDone;
    }
    public String getName() {
        return String.valueOf(this.name);
    }

    public String returnMissingNameError() {
        return "The description of a task cannot be empty.";
    }

    public String getSaveDataString() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " A " + this.name;
        return saveData;
    }

    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        return marked + this.name;
    }
}
