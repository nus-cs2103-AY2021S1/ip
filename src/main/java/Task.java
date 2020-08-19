public class Task {
    public static int quantity = 0;
    // Variables
    protected String name;
    protected boolean isDone;

    // Constructors
    public Task() {

    }
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    // Gets Status of task
    public boolean getStatus() {
        return this.isDone;
    }

    // Sets Status of task
    public void markedDone(boolean status) {
        this.isDone = status;
    }

    // Gets name of task
    public String getDescription() {
        return this.name;
    }

    @Override
    public String toString() {
        // By default print task name and status
        if(isDone) {
            return "[✓] " + this.name;
        } else {
            return "[✗] " + this.name;
        }
    }
}
