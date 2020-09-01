package duke;

public class Task {
    protected String name;
    private boolean isDone;
    private final TaskType type;

    /**
     * Constructor for Task.
     * @param name: String that represents task
     * @param type: type of task
     * @return Task with default isDone = false
     */
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

    /**
     * containsKeyword: Finds out whether this task has a keyword in its name.
     * @param keyword: keyword to be found
     * @return boolean indicating found or not
     */
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

    /**
     * getFileString()
     * @return a String representing the task that will be
     * saved into hard-drive.
     */
    public String getFileString() {
        String status = this.isDone() ? "T" : "F";
        return String.format("%s~%s", status, name);
    }
    
    /**
     * done(): Set this task isDone = true
     */
    public void done() {
        this.isDone = true;
    }
}


