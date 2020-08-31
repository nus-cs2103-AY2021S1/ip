package main.java.duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private TaskList taskList = null;
    private Storage storage = null;
    private Parser parser = new Parser();

    /**
     * Creates an instance of the Ui class
     * @param taskList taskList object used to store tasks
     * @param storage storage object used to read and write for the tasklist.txt file
     */
    public Ui(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Prints the DukeException error message.
     * @param errMsg
     */
    public String invalidInput(String errMsg) {
        DukeException exception = new DukeException(errMsg);
        return exception.getMessage();
    }

    /**
     * Prints a horizontal line of fixed length.
     */
    public void horiLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
            if (i == length - 1) System.out.println("");
        }
    }

    /**
     * Returns a string to be displayed as the response on CLI or GUI
     * @param nextInput user input string
     * @return a string to be displayed as the response on CLI or GUI
     */
    public String getResultFromParser(String nextInput) {
        String[] commandComponents = nextInput.split(" ", 2);
        String taskType = commandComponents[0];
        String toReturn;
        if (nextInput.equals("hello")) {
            toReturn = "  Hey! welcome to your nightmare!";
        } else if (nextInput.equals("bye")) {
            toReturn = "  Bye. Hope to see you again soon!";
        } else if (nextInput.equals("list")) {
            toReturn = taskList.toString();
        } else if (commandComponents[0].equals("done")) {
            try {
                int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
                Task toMark = taskList.getTask(taskIndex);
                toMark.markDone();
                storage.changeFileContents(taskList);
                toReturn = "  Nice! I've marked this task as done:\n"
                        + "    " + toMark.toString();
            } catch (IndexOutOfBoundsException e) {
                toReturn = "The task number does not exist!";
            } catch (NumberFormatException nfe) {
                toReturn = "Please provide a task number to mark as done!";
            }
        } else if (commandComponents[0].equals("delete")) {
            try {
                int taskIndex = Integer.parseInt(commandComponents[1]) - 1;
                Task toDisplay = taskList.getTask(taskIndex);
                taskList.removeTask(taskIndex);
                storage.changeFileContents(taskList);
                toReturn = "  Noted. I've removed this task:\n"
                        + "    " + toDisplay.toString() + "\n"
                        + "  Now you have " + taskList.size() + " tasks in the list.";
            } catch (IndexOutOfBoundsException e) {
                toReturn = "The task number does not exist!";
            } catch (NumberFormatException nfe) {
                toReturn = "Please provide a task number to delete!";
            }
        } else if (commandComponents[0].equals("find")) {
            String keyword = commandComponents[1];
            toReturn = taskList.searchWithKeyword(keyword);
        } else {
            // pass user input to parser
            if (commandComponents.length == 1) {
                toReturn = invalidInput("  \u2639 OOPS!!! The description of a task cannot be empty.");
            } else {
                try {
                    Task toAdd = parser.handleInput(nextInput);
                    if (toAdd == null) {
                        toReturn = invalidInput("  \u2639 OOPS!!! I'm sorry, but I don't know what " +
                                "that means :-(");
                    } else {
                        storage.writeToFile(toAdd.toString());
                        toReturn = "  Got it. I've added this task:\n"
                                + "    " + toAdd.toString() + "\n"
                                + "  Now you have " + (taskList.size()+1) + " tasks in the list.";
                        taskList.addTask(toAdd);
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    toReturn = invalidInput("  \u2639 OOPS!!! A deadline/event task must be input with a " +
                            "forward slash and the deadline");
                } catch (DateTimeParseException dtpe) {
                    toReturn = "Sorry, please key in a valid date and time format";
                }
            }
        }
        return toReturn;
    }

    /**
     * Takes in user input via a Scanner object. For commands bye, done and delete this
     * method updates the TaskList object and writes the changes to the System via the Storage object.
     * The inputting of new tasks is passed to the Parser object. Thereafter the application's responses are printed.
     */
    public void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        horiLine(60);
        System.out.println("  Hello! I'm IntelliGent!\n  What can I do for you?");
        horiLine(60);
        while (sc.hasNextLine()) {
            String nextInput = sc.nextLine();
            horiLine(60);
            System.out.println(getResultFromParser(nextInput));
            if (nextInput.equals("bye")) {
                sc.close();
                break;
            }
            horiLine(60);
        }

    }

}
