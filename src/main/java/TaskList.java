import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    /* Constructor for loading past task list from storage. */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /* Constructor for initialising new task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * List tasks to system output.
     */
    public String listTasks() {
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("You have no remaining tasks! Cheers!\n");
        } else {
            message.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
        }
        return message.toString();
    }

    /**
     * Add a task to list of tasks.
     */
    public String addTask(Task task) {
        String message = "";
        this.tasks.add(task);
        message += "Got it. I've added this task:\n";
        message += "  " + tasks.get(tasks.size() - 1) + "\n";
        message += "Now you have " + tasks.size() + " tasks in the list.\n";
        return message;
    }

    /**
     * Set the task with given index as done.
     *
     * @param id index of task to be marked as done
     */
    public String setDone(int id) {
        String message = "";
        this.tasks.get(id).setDone();
        message += "Nice! I've marked this task as done:\n";
        message += "  " + tasks.get(id) + "\n";
        return message;
    }

    public String tagTask(int id, String tag) {
        String message = "";
        this.tasks.get(id).addTag(tag);
        message += "I've tagged this task with #" + tag + " :\n";
        message += "  " + tasks.get(id) + "\n";
        return message;
    }

    public String untagTask(int id, String tag) {
        String message = "";
        this.tasks.get(id).removeTag(tag);
        message += "I've remove the tag from this task: #" + tag + " :\n";
        message += "  " + tasks.get(id) + "\n";
        return message;
    }

    /**
     * Delete the task with given index from the task list.
     *
     * @param id index of task to be deleted
     */
    public String deleteTask(int id) {
        String message = "";
        Task deletedTask = this.tasks.remove(id);
        message += "Noted. I have removed this task:\n";
        message += "  " + deletedTask + "\n";
        return message;
    }

    /**
     * Search the list of tasks for tasks that contain a keyword.
     *
     * @param keyword keyword that task names must contain
     */
    public String searchKeyword(String keyword) {
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("You have no remaining tasks to search from!\n");
        } else {
            message.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).containsKeyword(keyword)) {
                    message.append(i).append(1).append(".").append(tasks.get(i)).append("\n");
                }
            }
        }
        return message.toString();
    }
}
