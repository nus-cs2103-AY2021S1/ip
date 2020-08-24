public class Task {
    protected String description;
    protected boolean done;

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public void completeTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        String symbol = (this.done ? "[\u2713] " : "[\u2718] ");
        return symbol + this.description;
    }
    
    public String saveToHardDisk() {
        int isDone = this.done ? 1 : 0;
        return " | " + isDone + " | " + this.description;
    }
}
