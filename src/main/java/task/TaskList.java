package task;

import java.util.ArrayList;

import exceptions.DukeException;

public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * Creates TaskList object that stores tasks.
     * @param tasks ArrayList<Task></Task> to be loaded when Duke is run.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    /**
     * Creates string to print the tasks in the list.
     * @return str String to be returned.
     */
    public String list() {
        String str = "Here are the tasks in your list:\n";
        for (int i = 1; i < tasks.size() + 1; i++) {
            str += i + "." + tasks.get(i - 1).toString() + '\n';
        }
        return str;
    }

    /**
     * Adds the tasks to the tasklist.
     *
     * @param task Task to be added.
     * @return str String to be returned.
     */
    public String addTask(Task task) {
        assert this.tasks != null : "TaskList has not been initiated properly with ArrayList<Tasks>.";
        this.tasks.add(task);
        // inform user item has been added
        String str = "Got it. I've added this task:\n"
            + task.toString() + "\n"
            + "Now you have " + tasks.size() + " tasks in the list.\n";
        return str;
    }

    /**
     * Deletes the tasks from the tasklist.
     *
     * @param taskNo Task number to be deleted.
     * @return str String to be returned.
     */
    public String deleteTask(int taskNo) throws DukeException {
        // verify task number exists, then delete
        assert taskNo - 1 < tasks.size() : "Task number to delete does not exist.";
        if (taskNo - 1 < tasks.size()) {
            Task toDelete = tasks.get(taskNo - 1);
            tasks.remove(taskNo - 1);
            String str = "Noted. I've removed this task:\n"
                + toDelete.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
            return str;
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }

    /**
     * Marks the tasks as completed in the tasklist.
     *
     * @param taskNo Task number to be marked as done.
     * @return str String to be returned.
     */
    public String markTaskDone(int taskNo) throws DukeException {
        // verify task number exists, then mark as done
        assert taskNo - 1 < tasks.size() : "Task number to mark as done does not exist.";
        if (taskNo - 1 < tasks.size()) {
            if (tasks.get(taskNo - 1).isDone) {
                // task marked as done already
                throw new DukeException("Task is already done! :)\n");
            } else {
                tasks.get(taskNo - 1).markAsDone();
                String str = "Nice! I've marked this task as done:\n"
                    + tasks.get(taskNo - 1).toString();
                return str;
            }
        } else {
            // task number does not exist
            throw new DukeException("Sorry, this task does not exist!\n");
        }
    }

    /**
     * Finds tasks with given keyword and prints them.
     *
     * @param keyword String that user wants to find task with.
     * @return str String to be returned.
     * @throws DukeException if keyword is empty.
     */
    public String findTask(String keyword) throws DukeException {
        assert keyword != null : "Keyword is empty.";
        if (keyword == null) {
            throw new DukeException("Please indicate the keyword you wish to use to find tasks with.");
        } else {
            String str = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                boolean isFound = task.description.contains(keyword);

                if (isFound) {
                    str += (i + 1) + "." + task.toString() + "\n";
                }
            }
            return str;
        }
    }

    /**
     * Tags tasks with given tag.
     *
     * @param tag String that user wants to tag task with
     * @param taskNo Task number to be tagged.
     * @return str String to be returned.
     * @throws DukeException if tag is empty, or task does not exists.
     */
    public String tagTask(String tag, int taskNo) throws DukeException {
        assert tag != null : "Tag is empty.";
        assert taskNo - 1 < tasks.size() : "Task number to tag does not exist.";
        if (tag == null) {
            throw new DukeException("Please indicate the tag you wish to use to tag tasks with.");
        } else {
            tasks.get(taskNo - 1).setTag(tag);
            String str = "Got it, I've tagged this task for you:\n";
            str += tasks.get(taskNo - 1).toString();
            return str;
        }
    }
}
