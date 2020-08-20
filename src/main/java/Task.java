public class Task {
    protected boolean isComplete;
    protected int index;
    protected String instructions;

    public Task(boolean isComplete, int index, String instructions) {
        this.isComplete = isComplete;
        this.index = index;
        this.instructions = instructions;
    }

    // mark a task as complete
    public Task markDone() {
        return new Task(true, this.index, this.instructions);
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[✓] " +  this.instructions;
        } else {
            return "[✗] " + this.instructions;
        }
    }
}
