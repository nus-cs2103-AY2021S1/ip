public abstract class Task {
    private static final String COMPLETE = "\u2713";
    private static final String NOT_COMPLETE = "\u2718";

    protected String description;
    protected boolean isComplete = false;

    protected Task(String description) {
        if (description.equals("")) {
            throw new TaskException("Did you provide any description for this todo task?");
        }
        this.description = description;
    }

    public Task completeTask() {
        isComplete = true;
        return this;
    }

    public Task resetTask() {
        isComplete = false;
        return this;
    }

    protected String printCompletionFlag() {
        String completionFlag = isComplete ? COMPLETE : NOT_COMPLETE;
        return "[" + completionFlag + "]";
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | " + description;
    }
}
