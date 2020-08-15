/**
 * Task is a class for each task specified from
 * the user commands.
 *
 */
public class Task {
    protected String name;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Get the name of the task.
     *
     * @return the name of the task.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}