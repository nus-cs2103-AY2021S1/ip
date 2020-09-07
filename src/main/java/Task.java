public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;

    }

    // Sets done to true
    public void completeTask() {
        this.done = true;
    }

    // Returns name of Task
    public String getName() {
        return this.name;
    }

    // Returns boolean value of done
    public boolean isDone() {
        return this.done;
    }

    // Returns a tick or cross (depending on done value) and name of task as String
    public String printTask() {
        StringBuilder out = new StringBuilder();

        if (this.isDone()) {
            out.append("[/] ");
        } else {
            out.append("[X] ");
        }
        out.append(this.getName());
        return out.toString();
    }
}