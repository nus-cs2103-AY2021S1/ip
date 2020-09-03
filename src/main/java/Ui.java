import java.util.Scanner;

public class Ui {
    private final static String MSG_GREET = "Hello! I'm Duke \n" +
            "What can I do for you?\n";
    private final static String MSG_EXIT = "Bye. Hope to see you again soon!";
    private final static String MSG_ADDED_TASK = "Got it. I've added this task:\n";
    private final static String MSG_LIST_TASK_NO_TASK = "You have no tasks in your list.";
    private final static String MSG_LIST_TASK_HAVE_TASK = "Here are the tasks in your list:";
    private final static String MSG_MARK_DONE = "Nice! I've marked this task as done:\n";
    private final static String MSG_DELETED_TASK = "Noted. I've removed this task:\n";
    private final static String MSG_FOUND_MATCHING_TASK = "Here are the matching tasks in your list:";
    private final static String MSG_NO_MATCHING_TASK = "No matching tasks are found in your list.";
    
    
    public String showWelcome() {
        return MSG_GREET;
    }

    public String showExit() {
        return MSG_EXIT;
    }
    
    public String showError(Exception e) {
        return e.getMessage();
    }

    public String showError(String e) {
        return e;
    }
    
    public String showListWithTasksHeader() {
        return MSG_LIST_TASK_HAVE_TASK;
    }
    
    public String showListNoTasks() {
        return MSG_LIST_TASK_NO_TASK;
    }
    
    public String showTaskWithIndex(int i, Task task) {
        return i + "." + task;
    }
    
    public String showDeleted(Task task, int numOfRemainingTasks) {
        return MSG_DELETED_TASK + task + "\n" 
                + (numOfRemainingTasks == 0 
                        ? "Now you have no tasks in the list."
                        : (numOfRemainingTasks == 1 
                                ? "Now you have 1 task in the list." 
                                : "Now you have " + numOfRemainingTasks + " tasks in the list."));
    }
    
    public String showDone(Task task) {
        return MSG_MARK_DONE + task;
    }
    
    public String showAdded(Task task, int numOfTasks) {
        return MSG_ADDED_TASK + task + "\n" 
                + (numOfTasks == 0
                        ? "Now you have no tasks in the list."
                        : (numOfTasks == 1
                                ? "Now you have 1 task in the list."
                                : "Now you have " + numOfTasks + " tasks in the list."));
    }
    
    public String showNoMatch() {
        return MSG_NO_MATCHING_TASK;
    }
    
    public String showMatchingTaskHeader() {
        return MSG_FOUND_MATCHING_TASK;
    }
    
}
