import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    /**
     * Prints itself in a readable form, to be displayed in the GUI.
     */
    public void printList() {
        Ui.printWithLines(toString());
    }

    /**
     * Completes a specific task in itself.
     * @param index Index of the taskList to find the Task that is to be completed.
     */
    public void completeTask(int index) {
        String prefix = "Roger roger! I'm gonna mark this task as done:\n";
        assert index >= 0;
        assert index < this.size();
        Task task = super.get(index);
        task.complete();
        Ui.printWithLines(String.format("  %s%s\n", prefix, task));
    }

    /**
     * Adds a task to itself.
     * @param newTask The task to be added.
     * @param announce A boolean representing if the GUI should announce the addition of this new task.
     */
    public void addTask(Task newTask, boolean announce) throws DukeDuplicateTaskException {
        if (this.hasDuplicate(newTask)) {
            throw new DukeDuplicateTaskException();
        } else {
            super.add(newTask);
            if (announce) {
                String prefix = "Okay! I shall add this task:\n";
                String suffix = String.format("Now you got a total of %s task%s in your list!\n", super.size(),
                        super.size() == 1 ? "" : "s");
                Ui.printWithLines(prefix + newTask + "\n" + suffix);
            }
        }
    }

    /**
     * Deletes a specific task from itself.
     * @param index Index of the taskList to find the Task that is to be deleted.
     */
    public void deleteTask(int index) {
        assert index >= 0;
        assert index < this.size();
        Task task = super.get(index);
        super.remove(index);
        String prefix = "Very well! I shall delete this task:\n";
        Ui.printWithLines(prefix + task + "\n");
    }

    /**
     * Deletes a specific task from itself.
     */
    public void lookFor(String query) {
        TaskList results = new TaskList();
        this.stream().filter(task -> task.hasKeyword(query)).forEach(task -> {
            try {
                results.addTask(task, false);
            } catch (DukeDuplicateTaskException e) {
                e.printStackTrace();
            }
        });
        StringBuilder prefix = new StringBuilder("Here are the matches in your list:\n");
        Ui.printWithLines(prefix.append(results.listOut()).toString());
    }

    private StringBuilder listOut() {
        StringBuilder list = new StringBuilder();
        int l = size();
        for (int i = 0; i < l; i++) {
            list.append(i + 1).append(".").append(super.get(i).toString()).append("\n");
        }
        return list;
    }

    @Override
    public String toString() {
        return "Here are the tasks in your list:\n" + listOut();
    }

    /**
     * Converts the taskList into a form of string that is readable by the Storage class to be kept into a save file.
     * @return String that is readable by the Storage class
     */
    public String toData() {
        StringBuilder list = new StringBuilder();
        for (Task task: this) {
            list.append(task.toString()).append("\n");
        }
        return list.toString();
    }

    private boolean hasDuplicate(Task newTask) {
        for (Task task : this) {
            if (newTask.equals(task)) {
                return true;
            }
        }
        return false;
    }

}
