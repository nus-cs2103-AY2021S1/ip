import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Ui {
    private final BufferedReader INPUT_LINE;

    public Ui() {
        Reader inputStreamReader = new InputStreamReader(System.in);
        this.INPUT_LINE = new BufferedReader(inputStreamReader);
    }

    public void printTaskCount() {
        if (Task.totalTasks > 1) {
            System.out.println("You have a total of " + Task.totalTasks + " tasks in the list.");
        } else {
            System.out.println("You have a total of " + Task.totalTasks + " task in the list.");
        }
        printBorder();
    }

    public void printWelcomeMessage() {
        System.out.println("Hello there! My name is Duke." + "\nHow may I assist you today?");
        printBorder();
    }

    public void printByeMessage() {
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    public void doneMessage(Task task) {
        System.out.println("Great job! This task has been marked as done:");
        System.out.println(task);
        printBorder();
    }

    public void addedMessage(Task task) {
        System.out.println("Thank you for your input. The following task has been added to the list:");
        String outputString = task.toString();
        System.out.println(" " + outputString);
    }

    public void removeMessage(Task task) {
        System.out.println("The following task has been successfully removed:");
        System.out.println(task);
    }

    public String parseInput() {
        try {
            return INPUT_LINE.readLine();
        } catch (IOException error) {
            System.out.println("Error reading user input.");
            String returnNull = null;
            return returnNull;
        }
    }

    public void exitProgram() {
        try {
            INPUT_LINE.close();
        } catch (IOException error) {
            System.out.println("Error closing user input stream.");
        }
    }

    public void listTasks(TaskList arrayOfTasks) {
        String output = arrayOfTasks.toString();
        System.out.println(output);
        printBorder();
    }

    public void printBorder() {
        System.out.print("---------------------------\n");
    }
}