public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public Task fromString(String taskString) {
        boolean isDone = taskString.split(" ")[0].equals("[Done]");
        String description = taskString.split(" ")[2];
        Task t = new ToDo(description);
        if (isDone) {
            t.setDone();
        }
        return t;
    }
}
