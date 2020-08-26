import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Ui(){}

    public String getUserInput(Scanner sc) {
        return sc.nextLine();
    }

    public void showGreeting() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Sorry, an error occurred while loading the data");
    }

    public void showExitMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTasksList(TaskList tasks) {
        int index = 1;
        ArrayList<Task> tasksList = tasks.getTasksList();
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasksList) {
            System.out.println(index + ". " + t);
            index++;
        }
    }

    public void showAddTask(Task task, int size) {
        String taskText = "Got it. I've added this task:" + "\n" + task + "\n";
        String totalText = "Now you have " + size + " tasks in the list";
        System.out.println(taskText + totalText);
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list");
    }


    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void lineBreak() {
        System.out.println("---");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
