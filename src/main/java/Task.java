public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    public static Task generate(String cmd) {
        if (cmd.startsWith("todo ")) {
            return new ToDo(cmd.substring(5));
        } else if (cmd.startsWith("deadline ")) {
            String description = cmd.substring(9);
            int s = description.indexOf("/by");
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Deadline(description, time);
        } else if (cmd.startsWith("event ")) {
            String description = cmd.substring(6);
            int s = description.indexOf("/at");
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Event(description, time);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
