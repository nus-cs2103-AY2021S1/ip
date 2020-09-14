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
            String str1 = "\nThis task has already been completed!\n";
            return str1;
        } else {
            task.markNotDone();
            String str2 = "\nNice! I have completed this task!\n";
            String str3 = " " + task + "\n";
            return str2 + str3;
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
        long tasksLeft = tasks.checkTasksLeft();
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
            allTask += index + ". " + task + "\n";
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
            String str2 = "\nHere are the tasks in your list:\n";
            int index = 1;
            String allTask = "";
            for (Task task : foundTasks) {
                allTask += index + ". " + task + "\n";
                index++;
            }
            return str2 + allTask;
        }
    }

    /**
     * Find all events and deadlines due on a particular date.
     * @param tasks list of tasks in the task list.
     * @param date date which the tasks are due.
     * @return String response by the bot.
     */
    protected String findScheduleOnDate(TaskList tasks, LocalDate date) {
        String str1 = "";
        String str2 = "\nHere is your schedule for this day:\n";
        int index = 0;
        ArrayList<Task> allTasks = tasks.getTaskList();
        for (Task task: allTasks) {
            if (task.getTaskDeadline().equals(date)) {
                str1 += index + 1 + ". " + task + "\n";
                index++;
            }
        }
        if (index == 0) {
            return "No work for today!";
        } else {
            return str2 + str1;
        }
    }

    /**
     * Find all events and deadline due on a particular month.
     * @param tasks list of tasks in the task list.
     * @param month month which the tasks are due.
     * @return String response by the bot.
     */
    protected String findScheduleForMonth(TaskList tasks, Month month) {
        String str1 = "";
        String str2 = "\nHere is your schedule for ths month:\n";
        int index = 0;
        ArrayList<Task> eventAndDeadlineTasks = tasks.filterTask();
        for (Task eventOrDeadline : eventAndDeadlineTasks) {
            Month scheduleMonth = eventOrDeadline.getTaskDeadline().getMonth();
            if (scheduleMonth.equals(month)) {
                str1 += index + 1 + ". " + eventOrDeadline + "\n";
                index++;
            }
        }
        if (index == 0) {
            return "No work for today!";
        } else {
            return str2 + str1;
        }
    }

    /**
     * Prints the chronologically sorted list of tasks.
     * @param sortedTasks list of all tasks in an arraylist.
     * @return String response by the bot.
     */
    protected String sortTasks(ArrayList<Task> sortedTasks) {
        String str1 = "\nYour tasks has been sorted:\n";
        String str2 = "";
        int index = 1;
        for (Task task: sortedTasks) {
            str2 += index + ". " + task + "\n";
            index++;
        }
        return str1 + str2;
    }

    /**
     * Prints the task that is to be updated by the user.
     * @param taskToBeUpdated the task to be updated.
     * @return String response by the bot.
     */
    protected String enterNewUpdateForTask(Task taskToBeUpdated) {
        return "\nThis is the current task:\n" + taskToBeUpdated +
                "\nOk tell me what to update!\n";
    }

    /**
     * Prints the updated task.
     * @param updatedTask the updated task.
     * @return String response by the bot.
     */
    protected String updatedTask(Task updatedTask) {
        String str1 = "\nThis task has been updated!\n";
        return str1 + updatedTask;
    }
}
