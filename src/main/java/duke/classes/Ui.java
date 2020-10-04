package duke.classes;

import java.util.List;

import duke.tasks.Task;

/**
 * Class that handles the interactions between chatbot and users.
 */
public class Ui {

    protected String line = "---------------------------------------------------";

    /**
     * Default constructor with welcome message.
     */

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcome = line + "\nHello! I'm Duke!\n"
                + "What can I do for you?\n";
        System.out.println(welcome);
    }

    /**
     * Returns welcome message.
     * @return String welcome message
     */

    public String welcome() {
        return "\nHello! I'm Duke!\n" + "What can I do for you?\n";
    }

    /**
     * Iterates the todolist and prints out its objects.
     * @param list Todo List
     * @return String message of all tasks in list
     */

    public String displayList(List<Task> list) {
        if (list.size() == 0) {
            return "Sorry. The list is empty!";
        } else {
            StringBuilder tasklist = new StringBuilder();
            list.forEach((task) -> {
                tasklist.append(task.getStatusWithIndex()).append("\n");
                System.out.println(task.getStatusWithIndex());
            });
            return tasklist.toString();
        }
    }

    /**
     * Prints out the text after addition of the main types of tasks.
     * @param task Task added
     * @param list Todo List used for storage
     * @return String message
     */

    public String addTask(Task task, List<Task> list) {
        return task == null ? "Failed" : "\nGot it. I've added this task:\n   " + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.";
//        System.out.println(task == null
//                ? "Failed!"
//                : line + "\nGot it. I've added this task:\n   " + task.toString()
//                    + "\nNow you have " + list.size() + " tasks in the list.");
    }

    /**
     * Returns out the text after addition of a normal task.
     * @param task Task added
     * @return String message after task addition
     */

    public String addOtherTask(String task) {
        //System.out.println(line + "\nadded: " + task);
        return "\nadded: " + task;
    }

    /**
     * Returns out the string status of a task once it is checked as complete.
     * @param task Task completed
     * @return String Task completion message
     */

    public String completeTask(Task task) {
        System.out.println(line + "\nNice! I have marked this task as done: \n  "
                + task.toString());
        return "\nNice! I have marked this task as done: \n  "
                + task.toString();
    }

    /**
     * Returns the string message after deletion of a task from the current todolist.
     * @param task Task to be removed
     * @param list Todo List to remove tasks from
     * @return String message after deleting task.
     */

    public String deleteTask(Task task, List<Task> list) {
        System.out.println(line + "\nNoted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.");
        return "\nNoted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Returns out the complete message after ending program successfully.
     * @return goodbye message
     */

    public String endDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
        return "\nBye. Hope to see you again soon!";
    }

    /**
     * Returns out all error messages from exceptions.
     * @param error Error message derived from exceptions
     * @return String error message
     */
    public String printError(String error) {
        System.out.println(error);
        return error;
    }

    /**
     * Tags the task.
     * @param task Task
     * @return String of task
     */
    public String printTagging(Task task) {
        System.out.println(line + "\nNoted. I've tagged this task as :\n  " + task.toString());
        return "\nNoted. I've tagged this task as:\n  " + task.toString();
    }

}
