package duke;

import duke.task.Task;

import java.util.List;

/**
 * Ui handles all the console output of Duke program.
 */
public class Ui {

    protected TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Greets user who just activated Duke.
     * @return greeting message.
     */
    public String greeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Shows the available commands.
     * @return list of available commands.
     */
    public String help() {
        String msg = String.join("\n",
                "Adding task:",
                "todo <desc>",
                "deadline <desc> /by <desc>",
                "event <desc> /at <desc>",
                "",
                "list - show all added tasks",
                "find <desc> - show all tasks that contains <desc>",
                "done <taskId> - mark the task as done",
                "delete <taskId> - delete the task",
                "bye - close Duke");
        return (msg);
    }

    /**
     * Shows tasks in the current list.
     * @return list of tasks.
     */
    public String list() {
        List<Task> tasks = taskList.getTasks();
        String msg = "";
        if (tasks.size() == 0) {
            msg = "No tasks in the list wohoo!\n";
        } else {
            msg += "Here are the tasks in your list:\n";
            msg += tasksToString(tasks);
        }
        return msg;
    }

    /**
     * Shows tasks in the given filtered list.
     * @param tasks
     * @return list of filtered tasks.
     */
    public String filteredList(List<Task> tasks) {
        String msg = "";
        if (tasks.size() == 0) {
            msg = "No matching tasks in the list\n";
        } else {
            msg += "Here are the matching tasks in your list:\n";
            msg += tasksToString(tasks);
        }
        return msg;
    }

    private String tasksToString(List<Task> tasks) {
        String msg = "";
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            String taskDesc = (i + 1) + ". " + task + "\n";
            msg += taskDesc;
        }
        return msg;
    }

    /**
     * Shows message that the given task has been marked done.
     * @param task
     * @return task done message.
     */
    public String markAsDone(Task task) {
        return "Nice! I've marked it done - " + task.toString();
    }

    /**
     * Shows the summary of how many tasks are there in the list.
     */
    private String summary() {
        return "Now you have " + taskList.taskSize() + " tasks in the list";
    }

    /**
     * Shows message that the given task has been deleted.
     * @param task
     * @return task deleted message.
     */
    public String delete(Task task) {
        return "Noted! I've removed this task - " + task.toString() + "\n" + summary();
    }

    /**
     * Shows message that the given task has been added.
     * @param task
     * @return task added message.
     */
    public String add(Task task) {
        return "Added '" + task.toString() + "' to list of tasks" + "\n" + summary();
    }

}
