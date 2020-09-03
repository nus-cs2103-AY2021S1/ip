package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * summarize prints out all tasks inside a TaskList object.
     * @return String with all tasks
     */
    public String summarize() {
        StringBuilder allTasks = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = tasks.get(i);
            allTasks.append(String.format("%d.%s\n", i + 1, t.toString()));
        }
        return allTasks.toString();
    }

    /**
     * mark_done helps change the status of any Task inside TaskList, and mark
     * it done.
     * @param index index of task, counting from head of TaskList
     * @return String to confirm marking done
     */
    public String markDone(int index) {
        if (!this.tasks.get(index).isDone()) {
            this.tasks.get(index).done();
            return String.format("Nice! I've marked this task as done:\n[✓] %s\n", this.tasks.get(index).getName());
        } else {
            return String.format("Task %d already done!\n", index);
        }
    }

    /**
     * deleteTask helps remove any Task inside TaskList.
     * @param index index of task, counting from head of TaskList
     * @return String to confirm marking done
     */
    public String deleteTask(int index) {
        if (tasks.size() <= index) {
            return "No such task\n";
        }
        Task t = this.tasks.get(index);
        this.tasks.remove(index);
        return String.format("Noted. I've removed this task:\n  %s\n" +
                "Now you have %d tasks in the list.\n", t.toString(), this.tasks.size());
    }

    /**
     * addTask appends a task to the bottom of the list.
     * @param t Task object to be appended
     * @return String to confirm appending done
     */
    public String addTask(Task t) {
        tasks.add(t);
        return String.format("Got it. I've added this task:\n  %s\n" +
                "Now you have %d tasks in the list.\n", t.toString(), this.tasks.size());
    }

    /**
     * findTasksWith finds all tasks containing a specified keyword.
     * @param keyword keyword to be searched in task names
     * @return String with all tasks found
     */
    public String findTasksWith(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            matchName(task, keyword, matches);
        }

        // make String
        StringBuilder output_msg = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i ++) {
            Task t = matches.get(i);
            output_msg.append(String.format("%d.%s\n", i + 1, t.toString()));
        }
        return output_msg.toString();
    }

    /**
     * matchName checks if a task name contains keyword and add it to an
     * ArrayList of matched tasks
     * @param t Task object examined
     * @param keyword keyword to be searched for in names
     * @param matches ArrayList of Task objects that matches description
     */
    public static void matchName(Task t, String keyword, ArrayList<Task> matches) {
        if (t.containsKeyword(keyword)) {
            matches.add(t);
        }
    }

}
