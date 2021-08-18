/**
 * Task are work that has a name (that's a string) and a boolean which is true when a task is completed.
 */
public class Task {
    private String name;
    private boolean completed;

    private String COMPLETED = "[COMPLETED]";
    private String UNCOMPLETED = "[UNCOMPLETED]";


    public Task(String name) {
        this.name = name;
        completed = false;
    }

    public String printName() {
        String completionStatus = completed? COMPLETED: UNCOMPLETED;
        return completionStatus + this.name;
    }

    public void toggleComplete() {
        completed = !completed;
    }
}
