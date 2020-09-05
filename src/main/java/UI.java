import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Month;
import java.util.Scanner;

/**
 * Class to print string responses by the bot.
 * @author vanGoghhh
 */

public class UI {

    /**
     * Prints responses when a task is completed.
     * @param task the completed task.
     * @return String response for this command.
     */
    protected String doneTask(Task task) {
        if (task.getStatus()) {
            String str1 = "\nNice! I have completed this task!";
            String str2 = " " + task + "\n";
            return str1 + str2;
        } else {
            String str1 = "\nThis task has already been completed!\n";
            return str1;
        }
    }

    /**
     * Prints responses when a task is added.
     * @param tasks TaskList containing all tasks.
     * @param newTask Task to be completed.
     * @return String response for this command.
     */
    protected String addTask(TaskList tasks, Task newTask) {
        String str1 = "\nGot it. This task is now added.\n";
        String str2 = " " + newTask;
        int tasksLeft = tasks.checkTasksLeft();
        String str3 = "You have " + tasksLeft
                + " tasks left in your list!\n";
        return str1 + str2 + "\n" + str3;
    }

    /**
     * Prints responses when a task is deleted.
     * @param tasks TaskList containing all tasks.
     * @param deletedTask Task to be deleted.
     * @return String response for this command.
     */
    protected String deleteTask(TaskList tasks, Task deletedTask) {
        String str1 = "\nGot it. Deleting task.....\n";
        String str2 = " " + deletedTask;
        String str3 = "You have " + tasks.getTaskList().size()
                + " tasks left in your list!\n";
        return str1 + str2 + "\n" + str3;
    }

    /**
     * Prints all the tasks stored.
     * @param taskList TaskList containing all the tasks.
     * @return String response by the bot.
     */
    protected String displayTasks(TaskList taskList) {
        int index = 1;
        String allTask = "";
        String str1 = "Here are the tasks in your tasklist:\n";
        for (Task task : taskList.getTaskList()) {
            allTask += index + "." + task + "\n";
            index++;
        }
        return str1 + allTask;
    }

    /**
     * Prints tasks found using find command.
     * @param foundTasks tasks found with find command.
     * @return String response by the bot.
     */
    protected String findTask(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            String str1 = "\nNo matching tasks found!\n";
            return str1;
        } else {
            String str2 = "\nHere are the tasks in your list!\n";
            int index = 1;
            String allTask = "";
            for (Task task : foundTasks) {
                allTask += index + "." + task + "\n";
                index++;
            }
            return str2 + allTask;
        }
    }

    protected String findScheduleOnDate(TaskList tasks, LocalDate date) {
        String str1 = "";
        String str2 = "\nHere is your schedule for this day!\n";
        int index = 1;
        ArrayList<Task> allTasks = tasks.getTaskList();
        for (Task task: allTasks) {
            if (task.getTaskDeadline().equals(date)) {
                str1 += index + "." + task + "\n";
                index++;
            }
        }
        if (str1.compareTo("") == 1) {
            return "No work for today!";
        } else {
            return str2 + str1;
        }
    }

    protected String findScheduleForMonth(TaskList tasks, Month month) {
        String str1 = "";
        String str2 = "\nHere is your schedule for ths Month!\n";
        int index = 1;
        ArrayList<Task> eventAndDeadlineTasks = tasks.filterTask();
        for (Task eventOrDeadline : eventAndDeadlineTasks) {
            Month scheduleMonth = eventOrDeadline.getTaskDeadline().getMonth();
            if (scheduleMonth.equals(month)) {
                str1 += index + "." + eventOrDeadline + "\n";
                index++;
            }
        }
        if (str1.compareTo("") == 1) {
            return "No work for today!";
        } else {
            return str2 + str1;
        }
    }

    protected String sortTasks(ArrayList<Task> sortedTasks) {
        String str1 = "\nYour Tasks has been sorted! :)\n";
        String str2 = "";
        int index = 1;
        for (Task task: sortedTasks) {
            str2 += index + ". " + task + "\n";
            index++;
        }
        return str1 + str2;
    }
}
