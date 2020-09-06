package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.todo.Task;

/**
 * Represents a list of tasks. A task list supports various modifications of the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Lists out all the tasks in the list.
     * @throws DukeException
     */
    public String listTasks() throws DukeException {
        assert tasks.size() >= 0 : "Number of tasks cannot be negative";
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }

        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int taskNo = i + 1;
            Task task = tasks.get(i);
            response = response + taskNo + "." + task + "\n";
        }
        return response;
    }

    /**
     * Lists out all the tasks on a specific date.
     * @param date The specific date.
     * @throws DukeException If there is no task on that day.
     */
    public String listTasksOn(LocalDate date) throws DukeException {
        assert tasks.size() >= 0 : "Number of tasks cannot be negative";
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: tasks) {
            LocalDate taskDate = task.getDate();
            if (taskDate != null) {
                if (taskDate.isEqual(date)) {
                    taskList.add(task);
                }
            }
        }

        String response;
        if (taskList.size() > 0) {
            response = "Here are the tasks happening on: " + date.format(formatter) + "\n";
            for (int i = 0; i < taskList.size(); i++) {
                int taskNo = i + 1;
                Task task = taskList.get(i);
                response = response + taskNo + "." + task + "\n";
            }
        } else {
            response = "You don't have anything on: " + date.format(formatter) + " :)))\n";
        }
        return response;
    }

    /**
     * Marks the task as done.
     * @param taskNo The task number in the list.
     */
    public String doneTask(int taskNo) {
        assert taskNo >= 0: "taskNo cannot be negative";
        Task completedTask = tasks.get(taskNo - 1);
        completedTask.markAsDone();
        String response = "Nice! I've marked this task as done:\n"
                + " " + " "
                + "[" + completedTask.getStatusIcon() + "] "
                + completedTask.getDescription() + "\n";
        return response;
    }

    /**
     * Adds a task to the task list.
     * @param newTask The new task to add.
     */
    public String addTask(Task newTask) {
        assert newTask != null: "newTask cannot be null";
        tasks.add(newTask);
        String response = "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
        return response;
    }

    /**
     * Deletes a task on the task list.
     * @param taskNo The task number of the to be deleted task on the list.
     * @throws DukeException If there is nothing to delete and the task number exceeds total number of tasks.
     */
    public String deleteTask(int taskNo) throws DukeException {
        assert taskNo >= 0: "taskNo cannot be negative";
        if (tasks.size() <= 0) {
            throw new DukeException("Nothing to delete.");
        } else if (taskNo > tasks.size()) {
            throw new DukeException("Please provide a correct task number.");
        } else {
            String response;
            Task taskToBeDeleted = tasks.get(taskNo - 1);
            tasks.remove(taskNo - 1);
            response = "Noted. I've removed this task:\n"
                    + " " + " "
                    + "[" + taskToBeDeleted.getStatusIcon() + "] "
                    + taskToBeDeleted.getDescription() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.\n";
            return response;
        }
    }

    /**
     * Searches tasks that match the keyword.
     * @param keyword The keyword provided by the user.
     * @throws DukeException
     */
    public String searchKeyword(String keyword) throws DukeException {
        assert keyword != "": "keyword cannot be empty";
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }

        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                taskList.add(task);
            }
        }

        String response;
        if (taskList.size() > 0) {
            response = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                int taskNo = i + 1;
                Task task = taskList.get(i);
                response = response + taskNo + "." + task + "\n";
            }
        } else {
            response = "You don't have anything related to " + "\"" + keyword + "\"" + "\n";
        }
        return response;
    }
}
