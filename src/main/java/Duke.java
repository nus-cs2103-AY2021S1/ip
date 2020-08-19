import main.java.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // this method prints a horizontal line of fixed length
    public static void horiLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
            if (i == length - 1) System.out.println("");
        }
    }

    public static void invalidInput(String errMsg) {
        DukeException exception = new DukeException(errMsg);
        System.out.println(exception.getMessage());
    }


    // Note that all the outputs are formatted with two spaces before.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList();
        // welcome message
        horiLine(60);
        System.out.println("  Hello! I'm IntelliGent!\n  What can I do for you?");
        horiLine(60);
        while (sc.hasNextLine()) {
            String nextInput = sc.nextLine();
            String[] commandComponents = nextInput.split(" ", 2);
            String taskType = commandComponents[0];
            if (nextInput.equals("bye")) {
                horiLine(60);
                System.out.println("  Bye. Hope to see you again soon!");
                horiLine(60);
                sc.close();
                break;
            } else if (nextInput.equals("list")) {
                horiLine(60);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("  " + (i+1) + "." + taskList.get(i));
                }
                horiLine(60);
            } else if (commandComponents[0].equals("done")) {
                horiLine(60);
                int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
                Task toMark = taskList.get(taskIndex);
                toMark.markDone();
                System.out.println("  Nice! I've marked this task as done:");
                System.out.println("    " + toMark.toString());
                horiLine(60);
            } else if (commandComponents[0].equals("delete")) {
                horiLine(60);
                int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
                Task toDisplay = taskList.get(taskIndex);
                taskList.remove(taskIndex);
                System.out.println("  Noted. I've removed this task:");
                System.out.println("    " + toDisplay.toString());
                System.out.println("  Now you have " + taskList.size() + " tasks in the list.");
                horiLine(60);
            } else {
                // 1. split the input based on the first word(task type)
                // 2. if there is a valid string after the first word, work as intended
                // 3. else display corresponding error.
                // adding tasks
                horiLine(60);
                Task taskToAdd;
                if (taskType.equals("event")) {
                    // Event task
                    if (commandComponents.length == 1) {
                        // description is empty
                        invalidInput("  \u2639 OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        try {
                            taskToAdd = new Event(commandComponents[1]);
                            System.out.println("  Got it. I've added this task:\n"
                                    + "    " + taskToAdd.toString() + "\n  Now you have "
                                    + (taskList.size()+1) + " tasks in the list.");
                            taskList.add(taskToAdd);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            invalidInput("  \u2639 OOPS!!! An event task must be input with a forward slash and the deadline");
                        }
                    }
                } else if (taskType.equals("deadline")) {
                    // Deadline Task
                    if (commandComponents.length == 1) {
                        // description is empty
                        invalidInput("  \u2639 OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        try {
                            // the exception is thrown when we call the toString method.
                            taskToAdd = new Deadline(commandComponents[1]);
                            System.out.println("  Got it. I've added this task:\n"
                                    + "    " + taskToAdd.toString() + "\n  Now you have "
                                    + (taskList.size()+1) + " tasks in the list.");
                            taskList.add(taskToAdd);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            invalidInput("  \u2639 OOPS!!! A deadline task must be input with a forward slash and the deadline");
                        }
                    }
                } else if (taskType.equals("todo")){
                    // Todo task
                    if (commandComponents.length == 1) {
                        // description is empty
                        invalidInput("  \u2639 OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        taskToAdd = new Todo(commandComponents[1]);
                        System.out.println("  Got it. I've added this task:\n"
                                + "    " + taskToAdd.toString() + "\n  Now you have "
                                + (taskList.size()+1) + " tasks in the list.");
                        taskList.add(taskToAdd);
                    }
                } else {
                    // invalid input, detect it and create a DukeException,
                    // display the error message.
                    invalidInput("  \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                // include done to the test cases
                horiLine(60);
            }
        }
    }
}
