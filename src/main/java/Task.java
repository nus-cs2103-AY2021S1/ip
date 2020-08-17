public class Task {
    private String description;
    private Boolean done;

     public Task(String name) {
        this.description = name;
        this.done = false;
    }

    public String getStatusIcon() {
         return done ? "\u2713" : "\u2718";
    }

    public void markAsDone() {
         this.done = true;
    }
    @Override
    public String toString() {
         return "[" + this.getStatusIcon() + "] " + description;
    }
}
