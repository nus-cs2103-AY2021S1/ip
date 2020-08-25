package ParserStorageUi;

import Task.*;

import java.util.Scanner;
public class Ui {

    /** Initializes Ui class **/
    public Ui() {}

    /** Show Welcome message to the user **/
    public void showWelcome() {
        String logo = "Hello I'm Verzachtend \n" +
                "What can I do for you?\n" +
                "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE";
        System.out.println(logo);
    }

    /** Read all the input from the user **/
    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    /** Show loading error to the user **/
    public void showLoadingError(){
        System.out.println("No task found, please blablbalba");
    }

    /** Show error message to the user **/
    public void showError(String message){
        System.out.println(message);
    }

    /** Show line to the user **/
    public void showLine() {
        System.out.println("________________________________________________");
    }

    /** Show added task to the user **/
    public void showAddedTask(int taskSize, Task added){
        System.out.println("Got it. I've added this task: \n"
                + " " + added + "\n"
                + "Now you have " + taskSize + " tasks in the list.");
    }

    /** Show deleted task to the user **/
    public void showDeletedTask(int taskSize, Task deleted){
        System.out.println("Noted. I've removed this task: \n" +
                "  " + deleted + "\n" +
                "Now you have " + taskSize + " tasks in the list.");
    }

    /** Show the current tasks to the user **/
    public void showTasks(TaskList tasks){
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task item : tasks.getTaskList()) {
            System.out.println(i + ". " + item);
            i++;
        }
    }

    /** Show done tasks to the user **/
    public void showDoneTask(Task done){
        System.out.println("Nice! I've marked this task as done: \n"
                + done);
    }

    /** Show goodbye message to the customer **/
    public void showGoodBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

}
