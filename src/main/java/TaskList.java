import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    /**
     * Prints itself in a readable form.
     */
    public void printList() {
        Ui.printWithLines(toString());
    }

    /**
     * Completes a specific task in itself.
     */
    public void completeTask(int i) {
        String prefix = "Roger roger! I'm gonna mark this task as done:\n";
        Task task = super.get(i);
        task.complete();
        Ui.printWithLines(String.format("  %s%s\n", prefix, task));
    }

    /**
     * Adds a task to itself.
     */
    public void addTask(Task newTask, boolean announce) {
        super.add(newTask);
        if (announce) {
            String prefix = "Okay! I shall add this task:\n";
            String suffix = String.format("Now you got a total of %s task%s in your list!\n", super.size(),
                    super.size() == 1 ? "" : "s");
            Ui.printWithLines(prefix + newTask + "\n" + suffix);
        }
    }

    /**
     * Deletes a specific task from itself.
     */
    public void deleteTask(int index) {
        Task task = super.get(index);
        super.remove(index);
        String prefix = "Very well! I shall delete this task:\n";
        Ui.printWithLines(prefix + task + "\n");
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        StringBuilder prefix = new StringBuilder("Here are the tasks in your list:\n");
        int l = super.size();
        for (int i = 0; i < l; i++) {
            list.append(i + 1).append(".").append(super.get(i).toString()).append("\n");
        }
        return prefix.append(list).toString();
    }

    public String toData() {
        StringBuilder list = new StringBuilder();
        for (Task task: this) {
            list.append(task.toData()).append("\n");
        }
        return list.toString();
    }
}