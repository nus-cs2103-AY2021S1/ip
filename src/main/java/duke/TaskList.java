package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * TaskList constructor.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get the task list's size.
     *
     * @return the task list's size.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Add a task to the task list.
     *
     * @param task task to be added to the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Show all the tasks in the task list.
     */
    public void showTaskList() {
        Ui.showMessage("Here is your list of tasks:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Ui.showMessage((i + 1) + ". " + this.tasks.get(i) + "\n");
        }
    }

    /**
     * Mark the task with the given index as done.
     *
     * @param idx index of the task to be marked as done.
     * @throws DukeException index out of bound.
     */
    public void markTaskAsDone(int idx) throws DukeException {
        if (idx < 1 || idx > this.tasks.size()) {
            throw new DukeException("Sorry, but you inputted an invalid task index.");
        }
        this.tasks.get(idx - 1).markAsDone();
        Ui.showMessage("Great job!\nI have marked the task as done");
        Ui.showMessage(this.tasks.get(idx - 1).toString());
    }

    /**
     * Delete the task with the given index.
     *
     * @param idx index of the task to be deleted.
     * @throws DukeException index out of bound.
     */
    public void deleteTask(int idx) throws DukeException {
        if (idx < 1 || idx > this.tasks.size()) {
            throw new DukeException("Sorry, but you inputted an invalid task index.");
        }

        Ui.showMessage("Noted!\nI have deleted this task from your task list.");
        Ui.showMessage(this.tasks.get(idx - 1).toString());
        this.tasks.remove(idx - 1);
        Ui.showMessage(String.format(
                "Currently you have %d tasks in your list, don't forget to do them!\n",
                this.tasks.size()));
    }

    public void find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(0).getDescription().contains(keyword)) {
                matches.add(this.tasks.get(0));
            }
        }
        if (matches.size() == 0) {
            Ui.showMessage("Sorry, but there is no match : (");
            return;
        }

        Ui.showMessage("Here are the matching tasks in your list: ");
        for (int i = 0; i < matches.size(); i++) {
            Ui.showMessage((i + 1) + ". " + matches.get(i) + "\n");
        }
    }

    @Override
    public String toString() {
        String data = "";
        for (Task task : this.tasks) {
            data += task.writeToFile() + '\n';
        }

        return data;
    }
}
