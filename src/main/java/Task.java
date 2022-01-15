public class Task {
    private boolean completed;
    private String name;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }


    public void setDone() {
        this.completed = true;
    }

    public void setUndone() {
        this.completed = false;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

}
