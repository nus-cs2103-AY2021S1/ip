package duke;

public class Task {
    protected String name;
    private boolean isDone;
    private final TaskType type;

    public Task(String name, TaskType type) {
        this.name = name;
        this.isDone = false;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskType getType() {
        return type;
    }

    public boolean containsKeyword(String keyword) {
        String[] nameParts = name.split(" ");
        for (int i = 0; i < nameParts.length; i++) {
            if (nameParts[i].length() == keyword.length()
                    && nameParts[i].compareTo(keyword) == 0) {
                        return true;
            }
        }
        return false;
    }

    public String getFileString() {
        String status = this.isDone() ? "T" : "F";
        return String.format("%s~%s", status, name);
    }

    public void done() {
        this.isDone = true;
    }
}


