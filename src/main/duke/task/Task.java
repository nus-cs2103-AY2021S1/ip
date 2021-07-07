package duke.task;

public class Task {

    protected String name;
    protected Boolean completed;

    Task(String name) {
        this.name = name;
        this.completed = false;
    }

    Task(String name, String completed) {
        this.name = name;
        this.completed = completed.equals("1");
    }

    @Override
    public String toString() {
        if (completed) {
            return String.format("[\u2713] %s", name);
        } else {
            return String.format("[\u2717] %s", name);
        }
    }

    public void setCompleted() {
        completed = !completed;
    }

    /**
     * Converts the task into an array of the type, completion status, and name in Strings.
     *
     * @return Array of Task details.
     */
    public String[] toSaveFormatArray() {
        String[] strings = new String[3];
        strings[0] = "[T]";
        strings[1] = completed ? "1" : "0";
        strings[2] = name;
        return strings;
    }

    public String getName() {
        return name;
    }

}
