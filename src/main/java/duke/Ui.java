package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Handles interactions with the user.
 */
public class Ui {

    /**
     * Scanner to read user inputs.
     */
    private Scanner scanner;

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints greeting message.
     */
    public String getGreeting() {
        String intro1 = "Hello! I'm Duke, your tasks manager.\n";
        String intro2 = "What can I do for you? \n";
        String info = "Here's a list of commands to help you get started: \n"
                + "1. todo DESCRIPTION \n2. deadline DESCRIPTION /by DATE [TIME] \n"
                + "3. event DESCRIPTION /at DATE [TIME] \n4. delete INDEX \n"
                + "5. done INDEX \n6. list \n7. archive INDEX \n8. find KEYWORD \n"
                + "9. bye \nFor more info, visit the user guide at: https://pengxiangg.github.io/ip/ \n ";

        String greeting = intro1 + intro2 + info;
        return greeting;
    }

    public void printGreeting() {
        System.out.println(formatString(getGreeting()));
    }

    /**
     * Adds dividers to the message.
     *
     * @param s Message to add dividers to.
     * @return Message with dividers.
     */
    private String addDividers(String s) {
        String divider = "____________________________________________________________________\n";
        String dividerFormatted = String.format("%" + (5 + divider.length()) + "s", divider);
        return dividerFormatted + s + dividerFormatted;
    }

    /**
     * Formats string to follow indent alignment.
     *
     * @param s String to be indented.
     * @return Indented string.
     */
    public String formatString(String s) {
        String output = "";
        Scanner scanner = new Scanner(s);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String formattedLine = String.format("%" + (6 + line.length()) + "s", line);
            output = output + formattedLine + "\n";
        }
        return addDividers(output);
    }

    /**
     * Formats string and then prints the formmatted string.
     *
     * @param s String to be printed.
     */
    public void printString(String s) {
        System.out.println(formatString(s));
    }

    /**
     * Returns message that task was added to TaskList.
     * @param task Task that was added.
     * @param size Size of TaskList.
     * @return String of message.
     */
    public String getAddTaskString(Task task, int size) {
        String s = "Got it. I've added this task: \n"
                + task.toString() + '\n'
                + "Now you have " + size + " tasks in the list. \n";
        return s;
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodbye() {
        String goodbye = getGoodbyeString();
        System.out.println(formatString(goodbye));
    }

    public String getGoodbyeString() {
        return "Bye. Hope to see you again soon! \n";
    }

    /**
     * Returns message that task has been deleted from TaskList.
     * @param task Task that was deleted from TaskList.
     * @param size Size of TaskList.
     * @return String of message.
     */
    public String getDeletedTaskString(Task task, int size) {
        String success = "Noted. I've removed this task: \n"
                + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list. \n";
        return success;
    }

    /**
     * Returns message that task has been marked as done.
     * @param task Task that is marked as done.
     * @return String of message.
     */
    public String getMarkedTaskString(Task task) {
        return "Nice! I've marked this task as done: \n"
                + task + "\n";
    }

    /**
     * Prints TaskList.
     * @param list TaskList to be printed.
     */
    public void printList(List<Task> list) {
        String printedList = getListString(list);
        System.out.println(formatString(printedList));
    }

    public String getListString(List<Task> list) {
        String printedList = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String lineItem = (i + 1) + ". " + list.get(i) + "\n";
                printedList = printedList + lineItem;
            }
        } else {
            printedList = "List is empty \n";
        }
        return printedList;
    }

    /**
     * Reads in user input.
     *
     * @return User input as String.
     */
    public String readInput() {
        return scanner.nextLine();
    }


    /**
     * Returns message of tasks that have been found.
     * @param foundTasks ArrayList of found tasks.
     * @return String of message.
     */
    public String getFoundTasksString(ArrayList<Task> foundTasks) {
        String printedList = "";
        if (foundTasks.size() > 0) {
            printedList = "Here are the matching tasks in your list: \n";
            for (int i = 0; i < foundTasks.size(); i++) {
                String lineItem = (i + 1) + ". " + foundTasks.get(i) + "\n";
                printedList = printedList + lineItem;
            }
        } else {
            printedList = "No such keyword exists in the tasks in your list.\n";
        }
        return printedList;
    }


    /**
     * Returns message of that the task is archived.
     * @param task Task to be archived.
     * @return String of message.
     */
    public String getArchivedTaskString(Task task) {
        return "Nice! I've removed this task from the main list, "
                + "and added this task to the archive: \n"
                + task + "\n";
    }
}
