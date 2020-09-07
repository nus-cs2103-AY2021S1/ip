package duke;

import java.time.LocalDate;
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
     * Gets the task list's size.
     *
     * @return the task list's size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task task to be added to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        assert tasks.size() != 0;
    }

    /**
     * Shows all the tasks in the task list.
     *
     * @param ui ui where the task list is shown.
     */
    public void showTaskList(Ui ui) {
        ui.addMessage("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.addMessage((i + 1) + ". " + tasks.get(i) + "\n");
        }
    }

    /**
     * Marks the task with the given index as done.
     *
     * @param idx index of the task to be marked as done.
     * @param ui  ui where the message is shown.
     * @throws DukeException index out of bound.
     */
    public void markTaskAsDone(int idx, Ui ui) throws DukeException {
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeException("Sorry, but you inputted an invalid task index.");
        }
        tasks.get(idx - 1).markAsDone();
        ui.addMessage("Great job!\nI have marked the task as done");
        ui.addMessage(tasks.get(idx - 1).toString());
    }

    /**
     * Deletes the task with the given index.
     *
     * @param idx index of the task to be deleted.
     * @param ui  ui where the message is shown.
     * @throws DukeException index out of bound.
     */
    public void deleteTask(int idx, Ui ui) throws DukeException {
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeException("Sorry, but you inputted an invalid task index.");
        }

        ui.addMessage("Noted!\nI have deleted this task from your task list.");
        ui.addMessage(tasks.get(idx - 1).toString());
        tasks.remove(idx - 1);
        ui.addMessage(String.format(
                "Currently you have %d tasks in your list, don't forget to do them!\n",
                tasks.size()));
    }

    /**
     * Finds tasks that contain given keyword.
     *
     * @param keyword keyword to be find.
     * @param ui      ui where the message is shown.
     */
    public void find(String keyword, Ui ui) {
        ArrayList<Task> matches = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(0).getDescription().contains(keyword)) {
                matches.add(tasks.get(0));
            }
        }
        if (matches.size() == 0) {
            ui.addMessage("Sorry, but there is no match : (");
            return;
        }

        ui.addMessage("Here are the matching tasks in your list: ");
        for (int i = 0; i < matches.size(); i++) {
            ui.addMessage((i + 1) + ". " + matches.get(i) + "\n");
        }
    }

    /**
     * Updates task's description.
     *
     * @param idx         idx of the task.
     * @param description updated description.
     * @param ui          ui where the message is shown.
     * @throws DukeException index out of bound.
     */
    public void updateDescription(int idx, String description, Ui ui) throws DukeException {
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeException("Sorry, but you inputted an invalid task index.");
        }
        tasks.get(idx - 1).updateDescription(description);
        ui.addMessage("Here's the updated task\n" + tasks.get(idx - 1).toString());
    }

    /**
     * Updates task's time.
     *
     * @param idx  idx of the task.
     * @param time updated time.
     * @param ui   ui where the message is shown.
     * @throws DukeException index out of bound.
     */
    public void updateTime(int idx, LocalDate time, Ui ui) throws DukeException {
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeException("Sorry, but you inputted an invalid task index.");
        }
        tasks.get(idx - 1).updateTime(time);
        ui.addMessage("Here's the updated task\n" + tasks.get(idx - 1).toString());
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task.writeToFile()).append('\n');
        }

        return data.toString();
    }
}
