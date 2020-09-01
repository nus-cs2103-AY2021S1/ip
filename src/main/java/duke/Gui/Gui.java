package duke.Gui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Gui {
    private static final String INDENT = "  ";
    Scanner scan;

    public Gui() {
        this.scan = new Scanner(System.in);
    }

    public String showWelcome() {
        return ("Hello! I'm duke.Duke\n" + "What can I do for you?");
    }

    public ArrayList<String> showBye() {
        ArrayList<String> responseList = new ArrayList<>();
        responseList.add("Bye!!! Hope to see you again real soon.");

        return responseList;
    }

    public ArrayList<String> showDone() {
        ArrayList<String> responseList = new ArrayList<>();
        responseList.add("The following task has been marked done: ");

        return responseList;
    }

    public String showError(String e) {
        return (e);
    }

    /**
     * prints out all the tasks.
     * @param list list of tasks.
     */
    public ArrayList<String> listAllTasks(ArrayList<Task> list) {
        ArrayList<String> responseList = new ArrayList<>();
        responseList.add("Here are the tasks in your list: ");

        int LENGTH_OF_LIST = list.size();
        if (LENGTH_OF_LIST > 0) {
            int counter = 1;
            for (Task task : list) {
                responseList.add("  " + counter + "." + task);
                counter++;
            }
        } else {
            responseList.remove(0);
            responseList.add("No tasks found, add a task now!");
        }
        return responseList;
    }

    /**
     * message after adding a task to list.
     * @param task task to be added.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     */
    public ArrayList<String> addMessage(Task task, int tasksLeft) {
        ArrayList<String> reponseList = new ArrayList<>();

        reponseList.add("Got it, the following task has been added:\n" + INDENT + INDENT + task +
                "\n" + INDENT + this.taskLeftMessage(tasksLeft));

        return reponseList;
    }

    /**
     * message after searching for term with Find
     * @param listOfTasksFound list of taks found containing that term
     */
    public ArrayList<String> findMessage(ArrayList<Task> listOfTasksFound) {
        ArrayList<String> responseList = new ArrayList<>();

        if (listOfTasksFound.isEmpty()) {
            responseList.add("No tasks found with that term");
        } else {
            responseList.add("Here are the matching tasks in your list: \n");

            for (int i = 1; i <= listOfTasksFound.size(); i++) {
                responseList.add(i + "." + listOfTasksFound.get(i - 1));
            }
        }
        return responseList;
    }

    /**
     * message after deleting a message.
     * @param task task to be deleted.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     */
    public ArrayList<String> deleteMessage(Task task, int tasksLeft) {
        ArrayList<String> responseList = new ArrayList<>();
        responseList.add ("Noted. I have removed this task:\n" + INDENT + INDENT + task + "\n" +
                this.taskLeftMessage(tasksLeft));
        return responseList;
    }

    /**
     * message to show number of tasks left.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     * @return string of message.
     */
    public ArrayList<String> taskLeftMessage(int tasksLeft) {
        ArrayList<String> responseList = new ArrayList<>();
        responseList.add("Now you have " + tasksLeft + " tasks in the list.");
        if (tasksLeft == 0) {
            responseList.add("No tasks found. Add a task now!");
        }
        return responseList;
    }

    /**
     * shows icon based on whether task is done or not.
     * @param task task to be checked.
     * @return string of icon with brackets.
     */
    public String getStatusIcon(Task task) {
        return ("[" + (task.getIsDone() ? "\u2713" : "\u2718") + "] ");
    }

    public String readCommand() {
        return scan.nextLine();
    }
}
