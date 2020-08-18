public class Task {
    private final String description;
    private Boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCheckBox() {
        if (this.done) {
            return "[\u2713]";
        } else {
            return "[\u2718]";
        }
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return getCheckBox() + " " + this.getDescription();
    }
}
