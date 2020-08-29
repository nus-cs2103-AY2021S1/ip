import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to print string responses by the bot.
 * @author vanGoghhh
 */

public class UI {

    /**
     * Reads command entered by user.
     * @return string representation of command entered.
     */
    protected String readCommand() {
        Scanner sc = new Scanner(System.in);

        String message = sc.nextLine();

        return message;
    }

    /**
     * Greets the user upon launch.
     */
    protected void greetUser() {
        String welcome = "Hello I am Duke!\nHow can I help you?\n";
        String instructions = "Leave a single blankspace after each command to " +
                "trigger Duke's response!\n";
        String listOfCommand = "List of Commands = " +
                "event, todo, deadline, delete, done, find, list\n";
        System.out.println(welcome);
        System.out.println(instructions);
        System.out.println(listOfCommand);
    }

    /**
     * Prints responses when a task is completed.
     * @param task the completed task.
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
     */
    protected String addTask(TaskList tasks, Task newTask) {
        String str1 = "\nGot it. This task is now added.";
        String str2 = " " + newTask;
        int tasksLeft = tasks.checkTasksLeft();
        String str3 = "You have " + tasksLeft
                + " tasks left in your list!\n";
        return str1 + str2 + str3;
    }

    /**
     * Prints responses when a task is deleted.
     * @param tasks TaskList containing all tasks.
     * @param deletedTask Task to be deleted.
     */
    protected String deleteTask(TaskList tasks, Task deletedTask) {
        String str1 = "\nGot it. Deleting task.....";
        String str2 = " " + deletedTask;
        String str3 = "You have " + tasks.getTaskList().size()
                + " tasks left in your list!\n";
        return str1 + str2 + str3;
    }

    /**
     * Prints all the tasks stored.
     * @param taskList TaskList containing all the tasks.
     */
    protected String displayTasks(TaskList taskList) {
        int index = 1;
        String allTask = "";
        String str1 = "Here are the tasks in your tasklist:";
        for (Task task : taskList.getTaskList()) {
            allTask += index + "." + task + "\n";
            index++;
        }
        return str1 + allTask;
    }

    /**
     * Prints tasks found using find command.
     * @param foundTasks tasks found with find command.
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
}
