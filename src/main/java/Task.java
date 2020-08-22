public class Task {
    private String item;
    private boolean completed;

    Task(String item, boolean completed) {
        this.item = item;
        this.completed = completed;
    }

    public void completeTask() {
        this.completed = true;
    }

    public String getItem() {
        if (completed) {
            return "[O]" + this.item;
        } else {
            return "[X]" + this.item;
        }
    }
}
