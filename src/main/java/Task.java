class Task {

    protected String description;
    protected boolean isCompleted;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    protected String getStatusIcon() {
        if (this.isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    void completeTask() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
