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
     * Ui constructor
     */
    Ui() {

    }

    /**
     * addTask method which returns the task information
     */
    public String helpString() {
        String help = "Welcome to duke!\n"
                + "These are the list of commands and [ ] indicates user input while { } indicates variables\n"
                + "- [todo {name}] to add a new todo\n"
                + "- [deadline {name} /by {dd/mm/yyyy HHmm}] to add a new deadline\n"
                + "- [event {name} /at {dd/mm/yyy HHmm-HHmm}] to add a new event\n"
                + "- [delete {index}] to delete a task at the index specified\n"
                + "- [delete all] to delete all tasks\n"
                + "- [done {index}] to mark a task as done\n"
                + "- [list] to view all tasks in the list\n"
                + "- [find {word}] to find task that contains a certain word\n"
                + "- [snooze {index} {hours}] to push back a deadline or event by a number of hours\n"
                + "- [reschedule {index} {time}] to reschedule a deadline\n"
                + "- [reschedule {index} {time}-{time}] to reschedule a event\n"
                + "- [help] to print list of commands\n"
                + "- [bye] to exit the application";
        return help;
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

    /**
     * rescheduledTask method which returns string when a task time has been changed
     */
    public String rescheduledTask(Task task) {
        return "The following task has been rescheduled to:\n"
                + task.toString();
    }
}
