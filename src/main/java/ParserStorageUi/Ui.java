package parserstorageui;

import java.util.Scanner;
import task.Task;
import task.TaskList;

public class Ui {

    /**
     * Initializes Ui class
     **/
    public Ui() {
    }

    /**
     * Show welcome message to the user
     **/
    public void showWelcome() {
        String logo = "Hello I'm Verzachtend \n"
            + "What can I do for you?\n"
            + "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE";
        System.out.println(logo);
    }


    /**
     * Receive input from user
     **/
    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Show loading error to the user
     **/
    public void showLoadingError() {
        System.out.println("No task found, please blablbalba");
    }

    /**
     * Show error message to the user
     **/
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Show line to the user
     **/
    public void showLine() {
        System.out.println("________________________________________________");
    }

    /**
     * Show added task to the user
     **/
    public String showAddedTask(int taskSize, Task added) {
        return "Got it. I've added this task: \n"
            + " " + added + "\n"
            + "Now you have " + taskSize + " tasks in the list.";
    }

    /**
     * Show deleted task to the user
     **/
    public String showDeletedTask(int taskSize, Task deleted) {
        return "Noted. I've removed this task: \n"
            + "  "
            + deleted
            + "\n"
            + "Now you have " + taskSize + " tasks in the list.";
    }

    /**
     * Show the current tasks to the user
     **/
    public String showTasks(TaskList tasks) {
        int i = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task item : tasks.getTaskList()) {
            output = output + i + ". " + item + "\n";
            //System.out.println(i + ". " + item);
            i++;
        }
        return output;
    }

    /**
     * Show done tasks to the user
     **/
    public String showDoneTask(Task done) {
        return "Nice! I've marked this task as done: \n"
            + done;
    }

    /**
     * Show the tasks that match the keyword
     **/
    public String  showFoundTasks(TaskList tasks, String keyword) {
        int i = 1;
        String output = "Here are the matching tasks in your list:\n";
        for (Task item : tasks.getTaskList()) {
            if (item.isNameMatchKeyWord(keyword)) {
                output = output + i + ". " + item + "\n";
                /*System.out.println(i + ". " + item);*/
                i++;
            }
        }
        return output;
    }

    /**
     * Show goodbye message to the customer
     **/
    public String showGoodBye() {
        return "Bye. Hope to see you again soon!";
    }

}
