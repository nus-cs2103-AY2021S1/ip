public abstract class Task {
    protected String name;
    protected boolean done;
    protected String type;

    Task(String name) {
        this.name = name;
        this.done = false;
        this.type = " ";
    }

    Task(String name, String type) {
        this.name = name;
        this.done = false;
        this.type = type;
    }

    public void complete() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[✓] %s", name);
        } else {
            return String.format("[✗] %s", name);
        }
    }

    public String getAbbreviatedString() {
        int isDoneRep = this.done ? 1 : 0;
        return String.format("%s | %d | %s", this.type, isDoneRep, this.name);
    }
}