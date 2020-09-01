package duke.utils;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String logo = "      _      _\n"
                + "| | / /| | / /\n"
                + "| |/ / | |/ /\n"
                + "|   <  |   <\n"
                + "|_|\\_\\ |_|\\_\\\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm KK\n" +
                " What can I do for you?");
    }
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        return input;
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showRemovalMessage(Task removedTask, TaskList tasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasks.getNumberOfTask() + " tasks in the list.");
    }

    public void showDoneMessage(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask);
    }

    public void showAddMessage(Task newTask, TaskList tasks) {
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.getNumberOfTask() + " tasks in the list.");
    }


    public void showListMessage(TaskList tasks) {
        tasks.printList();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLine(){
        System.out.println("-----------------------");
    }

    public void sayGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
