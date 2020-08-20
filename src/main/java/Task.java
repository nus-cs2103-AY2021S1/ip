public class Task {
    protected String summary;
    protected boolean isComplete;

    public Task(String summary) {
        this.summary = summary;
        this.isComplete = false;
    }

    public String getStatus() {
        return (isComplete ? "[Y]" : "[N]");
    }

    public String getSummary() {
        return summary;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.getSummary();
    }
}
