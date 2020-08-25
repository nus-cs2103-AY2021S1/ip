public class Task {
    private static final String tick = "\u2713";
    private static final String cross = "\u2718";
    private String description;
    private String taskMarker = "";
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    protected Task(String description, String taskMarker) {
        this.description = description;
        this.taskMarker = taskMarker;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String status = isDone ? tick : cross;
        return boxFormat(taskMarker) + boxFormat(status) + " " + description;
    }

    public String generateSaveFormat() {
        int isDoneInt = isDone ? 1 : 0;
        return taskMarker + Duke.line + isDoneInt
                + Duke.line + description;
    }

    protected String boxFormat(String symbol) {
        return String.format("[%s]", symbol);
    }
}
