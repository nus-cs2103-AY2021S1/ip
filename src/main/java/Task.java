abstract public class Task {
    private String description;
    private boolean done;

    public Task(String d) {
        this.description = d;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.done;
    }

    public void markDone() {
        this.done = true;
    }

    public String getStatus() {
        String check = this.done ? "\u2713" : "\u2718";
        String status = "[" + check + "] " + this.getDescription();
        return this.done ?
                Colour.Green(status)
                :
                Colour.Red(status);
    }
}
