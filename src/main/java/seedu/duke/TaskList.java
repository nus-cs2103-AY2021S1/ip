package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.duke.todo.Task;

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
    public void listTasks() throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int taskNo = i + 1;
            Task task = tasks.get(i);
            System.out.println(taskNo + "." + task);
        }
    }

    /**
     * Lists out all the tasks on a specific date.
     * @param date The specific date.
     * @throws DukeException If there is no task on that day.
     */
    public void listTasksOn(LocalDate date) throws DukeException {
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

        if (taskList.size() > 0) {
            System.out.println("Here are the tasks happening on: " + date.format(formatter));
            for (int i = 0; i < taskList.size(); i++) {
                int taskNo = i + 1;
                Task task = taskList.get(i);
                System.out.println(taskNo + "." + task);
            }
        } else {
            System.out.println("You don't have anything on: " + date.format(formatter) + " :)))");
        }
    }

    /**
     * Marks the task as done.
     * @param taskNo The task number in the list.
     */
    public void doneTask(int taskNo) {
        System.out.println("Nice! I've marked this task as done:");
        Task completedTask = tasks.get(taskNo - 1);
        completedTask.markAsDone();
        System.out.println(" " + " "
                + "[" + completedTask.getStatusIcon() + "] "
                + completedTask.getDescription());
    }

    /**
     * Adds a task to the task list.
     * @param newTask The new task to add.
     */
    public void addTask(Task newTask) {
        System.out.println("Got it. I've added this task:");
        tasks.add(newTask);
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task on the task list.
     * @param taskNo The task number of the to be deleted task on the list.
     * @throws DukeException If there is nothing to delete and the task number exceeds total number of tasks.
     */
    public void deleteTask(int taskNo) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("Nothing to delete.");
        } else if (taskNo > tasks.size()) {
            throw new DukeException("Please provide a correct task number.");
        } else {
            System.out.println("Noted. I've removed this task:");
            Task taskToBeDeleted = tasks.get(taskNo - 1);
            tasks.remove(taskNo - 1);
            System.out.println(" " + " "
                    + "[" + taskToBeDeleted.getStatusIcon() + "] "
                    + taskToBeDeleted.getDescription());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    /**
     * Searches tasks that match the keyword.
     * @param keyword The keyword provided by the user.
     * @throws DukeException
     */
    public void searchKeyword(String keyword) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("You don't have any tasks.");
        }
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                taskList.add(task);
            }
        }

        if (taskList.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int taskNo = i + 1;
                Task task = taskList.get(i);
                System.out.println(taskNo + "." + task);
            }
        } else {
            System.out.println("You don't have anything related to " + "\"" + keyword + "\"");
        }
    }
}
