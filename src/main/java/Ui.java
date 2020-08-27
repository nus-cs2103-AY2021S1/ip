import java.util.Scanner;

public class Ui {
    private final static String MSG_LINEBREAK = "____________________________________________________________";
    private final static String MSG_GREET = "Hello! I'm Duke \n" +
            "What can I do for you?\n";
    private final static String MSG_EXIT = "Bye. Hope to see you again soon!";
    private final static String MSG_ADDED_TASK = "Got it. I've added this task:\n";
    private final static String MSG_LIST_TASK_NO_TASK = "You have no tasks in your list.";
    private final static String MSG_LIST_TASK_HAVE_TASK = "Here are the tasks in your list:";
    private final static String MSG_MARK_DONE = "Nice! I've marked this task as done:\n";
    private final static String MSG_DELETED_TASK = "Noted. I've removed this task:\n";

    private Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(MSG_LINEBREAK + "\n" + MSG_GREET + MSG_LINEBREAK + "\n");
    }

    public void showExit() {
        System.out.println(MSG_EXIT);
    }
    
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void showLineTop() {
        System.out.println(MSG_LINEBREAK);
    }
    
    public void showLineBottom() { 
        System.out.println(MSG_LINEBREAK + "\n");
    }
    
    public String readCommand() {
        return sc.nextLine();
    }
    
    public void showListWithTasksHeader() {
        System.out.println(MSG_LIST_TASK_HAVE_TASK);
    }
    
    public void showListNoTasks() {
        System.out.println(MSG_LIST_TASK_NO_TASK);
    }
    
    public void showTaskWithIndex(int i, Task task) {
        System.out.println(i + "." + task);
    }
    
    public void showDeleted(Task task, int numOfRemainingTasks) {
        System.out.println(MSG_DELETED_TASK + task + "\n" 
                + (numOfRemainingTasks == 0 
                        ? "Now you have no tasks in the list."
                        : (numOfRemainingTasks == 1 
                                ? "Now you have 1 task in the list." 
                                : "Now you have " + numOfRemainingTasks + " tasks in the list.")));
    }
    
    public void showDone(Task task) {
        System.out.println(MSG_MARK_DONE + task);
    }
    
    public void showAdded(Task task, int numOfTasks) {
        System.out.println(MSG_ADDED_TASK + task + "\n" 
                + (numOfTasks == 0
                        ? "Now you have no tasks in the list."
                        : (numOfTasks == 1
                                ? "Now you have 1 task in the list."
                                : "Now you have " + numOfTasks + " tasks in the list.")));
    }
    
}
