public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String type;

    Task(String name) {
        this.name = name;
        this.isDone = false;
        this.type = " ";
    }

    Task(String name, boolean isDone, String type) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    public void complete() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[✓] %s", name);
        } else {
            return String.format("[✗] %s", name);
        }
    }

    public String getAbbreviatedString() {
        int isDoneRep = this.isDone ? 1 : 0;
        return String.format("%s | %d | %s", this.type, isDoneRep, this.name);
    }

    public boolean checkIfDone() {
        return this.isDone;
    }
}