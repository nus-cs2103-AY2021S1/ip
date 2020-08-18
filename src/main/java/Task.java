public class Task {
    private String item;
    private boolean completed;

    Task(String item) {
        this.item = item;
        this.completed = false;
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
