package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Encapsulates a list of tasks.
 * It generates messages to inform users of its operations.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Displays all the tasks in the list of tasks.
     */
    public void listAllTasks() {
        int size = taskList.size();
        if (size == 0) {
            Ui.addMessage("There are no tasks in your list.");
        } else {
            Ui.addMessage("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                int taskNumber = i + 1;
                String task = String.format("%d. %s", taskNumber, taskList.get(i));
                Ui.addMessage(task);
            }
        }
    }

    /**
     * Adds a task to the list of tasks.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        int size = taskList.size();
        String taskWord = (size > 1 ? "tasks" : "task");
        Ui.addMessage("Got it. I've added this task:");
        Ui.addMessage("  " + task);
        Ui.addMessage("Now you have " + size + " " + taskWord + " in the list.");
    }

    /**
     * Deletes a task with the given index in the list of tasks.
     * @param index Index of task to be deleted.
     */
    public void deleteTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        Ui.addMessage("Got it. I've removed this task:");
        Ui.addMessage("  " + task);
    }

    /**
     * Marks a task with the given index in the list of tasks as done.
     * @param index Index of task to be marked as done.
     */
    public void doneTask(int index) {
        Task task = taskList.get(index);
        task.markAsDone();

        Ui.addMessage("Nice! I've marked this task as done:");
        Ui.addMessage("  " + task);
    }

    /**
     * Finds and displays tasks whose description contains the given keywords.
     * @param keyword Keyword to be used as for searching.
     */
    public void findTasks(String keyword) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.contains(keyword)) {
                tasks.add(task);
            }
        }

        int size = tasks.size();
        if (size == 0) {
            Ui.addMessage("Sorry, I couldn't find any matching tasks.");
        } else {
            Ui.addMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < size; i++) {
                int taskNumber = i + 1;
                String task = String.format("%d. %s", taskNumber, tasks.get(i));
                Ui.addMessage(task);
            }
        }
    }

    /**
     * Displays all tasks that were completed in the past 7 days.
     */
    public void displayStatistics() {
        List<Task> doneTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.isDoneThisWeek(LocalDate.now())) {
                doneTasks.add(task);
            }
        }
        int size = doneTasks.size();
        if (size == 0) {
            Ui.addMessage("You did not complete any task in the past 7 days.");
        } else {
            String taskWord = size <= 1 ? "task" : "tasks";
            Ui.addMessage("You completed " + size + " " + taskWord + " in the past 7 days:");
            for (int i = 0; i < size; i++) {
                int taskNumber = i + 1;
                String task = String.format("%d. %s", taskNumber, doneTasks.get(i));
                Ui.addMessage(task);
            }
        }
    }

    /**
     * Retrieves the list of tasks.
     * @return List of task.
     */
    public List<Task> getTask() {
        return taskList;
    }
}
