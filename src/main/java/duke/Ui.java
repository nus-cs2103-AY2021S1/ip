package main.java.duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private TaskList taskList = null;
    private Storage storage = null;
    private Parser parser = new Parser();

    public Ui(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public static void invalidInput(String errMsg) {
        DukeException exception = new DukeException(errMsg);
        System.out.println(exception.getMessage());
    }

    /**
     * Prints a horizontal line of fixed length.
     */
    public static void horiLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
            if (i == length - 1) System.out.println("");
        }
    }

    /**
     * Takes in user input via a Scanner and prints the respective results
     */
    public void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        horiLine(60);
        System.out.println("  Hello! I'm IntelliGent!\n  What can I do for you?");
        horiLine(60);
        while (sc.hasNextLine()) {
            String nextInput = sc.nextLine();
            String[] commandComponents = nextInput.split(" ", 2);
            String taskType = commandComponents[0];
            horiLine(60);
            if (nextInput.equals("hello")) {
                System.out.println("  Hey! welcome to your nightmare!");
            } else if (nextInput.equals("bye")) {
                System.out.println("  Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (nextInput.equals("list")) {
                System.out.println(taskList.toString());
            } else if (commandComponents[0].equals("done")) {
                try {
                    int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
                    Task toMark = taskList.getTask(taskIndex);
                    toMark.markDone();
                    storage.changeFileContents(taskList);
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("    " + toMark.toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The task number does not exist!");
                }
            } else if (commandComponents[0].equals("delete")) {
                try {
                    int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
                    Task toDisplay = taskList.getTask(taskIndex);
                    taskList.removeTask(taskIndex);
                    storage.changeFileContents(taskList);
                    System.out.println("  Noted. I've removed this task:");
                    System.out.println("    " + toDisplay.toString());
                    System.out.println("  Now you have " + taskList.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The task number does not exist!");
                }
            } else {
                // pass user input to parser
                if (commandComponents.length == 1) {
                    invalidInput("  \u2639 OOPS!!! The description of a task cannot be empty.");
                } else {
                    try {
                        Task toAdd = parser.handleInput(nextInput);
                        if (toAdd == null) {
                            invalidInput("  \u2639 OOPS!!! I'm sorry, but I don't know what " +
                                    "that means :-(");
                        } else {
                            storage.writeToFile(toAdd.toString() + System.lineSeparator());
                            System.out.println("  Got it. I've added this task:\n"
                                    + "    " + toAdd.toString() + "\n  Now you have "
                                    + (taskList.size()+1) + " tasks in the list.");
                            taskList.addTask(toAdd);
                        }
                    } catch(ArrayIndexOutOfBoundsException e) {
                        invalidInput("  \u2639 OOPS!!! A deadline/event task must be input with a " +
                                "forward slash and the deadline");
                    } catch (DateTimeParseException dtpe) {
                        System.out.println("Sorry, please key in a valid date and time format");
                    }

                }
            }
            horiLine(60);
        }

    }

    public void printAllTasks(ArrayList<Task> taskList) {

    }


}
