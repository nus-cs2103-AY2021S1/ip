package duke;

import java.util.List;

/**
 * The JavafxUi class to handle user interface methods for the javajx interface
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Ui {

    /**
     * JavafxUi constructor
     */
    Ui() {

    }


    /**
     * addTask method which returns the task information
     */
    public String addTask(Task task) {
        return "added: " + task.toString() + "\n";
    }

    /**
     * deleteTask method which returns the task information
     */
    public String deleteTask(Task task) {
        if (task == null) {
            return deleteAll();
        } else {
            return "Noted. I've removed this task: " + "\n" + task.toString() + "\n";
        }

    }

    /**
     * doneTask method which returns the task information
     */
    public String doneTask(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString() + "\n";
    }

    /**
     * listCount method which returns the number of tasks
     */
    public String listCount(int count) {
        return "Now you have " + count + " tasks in the list. \n";
    }

    /**
     * bye method which returns a message when the user exits
     */
    public String bye() {
        return "Bye. Hope to see you again soon! \n";
    }

    /**
     * deleteAll method which returns a message when the user deletes all tasks
     */
    public String deleteAll() {
        return "All tasks have been deleted!" + "\n";
    }

    /**
     * printList method which returns all the tasks in the list
     * @param tasks list of tasks
     */
    public String printList(List<Task> tasks) {
        String output = "";
        int index = 1;
        for (Task x: tasks) {
            if (x != null) {
                output += index + ". " + x.toString() + "\n";
            }
            index += 1;

        }
        return output;
    }

    /**
     * foundWord method which returns all the tasks in the list that contain the word
     * @param tasks list of tasks in the list that contain the word
     */
    public String foundWord(List<Task> tasks) {
        return "Here are the matching tasks in your list:\n"
                + printList(tasks);
    }

    public String rescheduledTask(Task task) {
        return "The following task has been rescheduled to:\n"
                + task.toString();
    }
}
