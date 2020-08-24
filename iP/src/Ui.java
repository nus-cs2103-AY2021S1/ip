import java.util.ArrayList;
import java.util.Scanner;

public class Ui {


    public void showLoadingError() {
        System.out.println("Failed to load data");
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(
                "Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showListOfTask(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList) {
            System.out.println(String.format("%s. %s", index, task));
            index += 1;
        }
    }

    public void showMarkedDoneTask(Task doneTask) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(doneTask);
    }

    public void showDeletedTask(Task deletedTask, ArrayList<Task> taskList) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(deletedTask);
        System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
    }

    public void showAddedTask(Task addedTask, ArrayList<Task> taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
    }

    public void showLine() {
        System.out.println("---------------------------------------");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String user_input = "";
        user_input = scanner.nextLine();  // Read user input
        return user_input;
    }



}
