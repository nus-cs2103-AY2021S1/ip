import java.util.Scanner;
public class Ui {

    public Ui() {}

    public void showWelcome() {
        String logo = "Hello I'm Verzachtend \n" +
                "What can I do for you?\n" +
                "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE";
        System.out.println(logo);
    }

    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    public void showLoadingError(){
        System.out.println("No task found, please blablbalba");
    }

    public void showError(String message){
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("________________________________________________");
    }

    public void showAddedTask(int taskSize, Task added){
        System.out.println("Got it. I've added this task: \n"
                + " " + added + "\n"
                + "Now you have " + taskSize + " tasks in the list.");
    }

    public void showDeletedTask(int taskSize, Task deleted){
        System.out.println("Noted. I've removed this task: \n" +
                "  " + deleted + "\n" +
                "Now you have " + taskSize + " tasks in the list.");
    }

    public void showTasks(TaskList tasks){
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task item : tasks.getTaskList()) {
            System.out.println(i + ". " + item);
            i++;
        }
    }

    public void showDoneTask(Task done){
        System.out.println("Nice! I've marked this task as done: \n"
                + done);
    }

    public void showGoodBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

}
