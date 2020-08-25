package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private static final String indentation = "     ";

    /**
     * Construct a TaskList object.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to taskList.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Prints the task.
     * @param task Task to be printed.
     */
    public void printAddedTask(Task task) {
        System.out.println(indentation + "Got it. I've added this task: ");
        System.out.println(indentation + "  " + task.toString());
        System.out.println(indentation + "Now you have "+ taskList.size() + " tasks in the list.");
    }

    /**
     * Prints taskList.
     */
    public void printList() {
        System.out.println(indentation + "Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            String s = indentation + (i + 1) + ". " + taskList.get(i).toString();
            System.out.println(s);
        }
    }

    /**
     * Marks the nth task as done.
     * @param n Index of the task to be marked as done.
     */
    public void markTaskAsDone(int n) {
        System.out.println(indentation + "Nice! I've marked this task as done: ");
        Task task = taskList.get(n - 1);
        task.markAsDone();
        System.out.println(indentation + "  " + task.toString());
    }

    /**
     * Deletes the nth task.
     * @param n Index of the task to be deleted.
     */
    public void deleteTask(int n) {
        System.out.println(indentation + "Noted. I've removed this task:");
        Task task = taskList.get(n - 1);
        System.out.println(indentation + "  " + task.toString());
        taskList.remove(n - 1);
        System.out.println(indentation + "Now you have "+ taskList.size() + " tasks in the list.");
    }

    /**
     * Returns the taskList.
     * @return TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of task in the list.
     * @return Integer indicating number of task in list.
     */
    public int getNumOfTask() {
        return taskList.size();
    }

    public void findTaskByKeyword(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTaskName().contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        System.out.println(indentation + "Here are the matching tasks in your list:");
        for(int i = 0; i < tasksWithKeyword.size(); i++) {
            String s = indentation + (i + 1) + ". " + tasksWithKeyword.get(i).toString();
            System.out.println(s);
        }
    }
}
