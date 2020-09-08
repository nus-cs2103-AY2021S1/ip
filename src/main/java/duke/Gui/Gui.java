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
        final String BYE_MESSAGE = "Bye!!! Hope to see you again real soon.";
        responseList.add(BYE_MESSAGE);
        assert responseList instanceof ArrayList : "Return type of the result of this method should be an ArrayList";

        return responseList;
    }

    public ArrayList<String> showDone() {
        ArrayList<String> responseList = new ArrayList<>();
        assert responseList instanceof ArrayList : "Return type of the result of this method should be an ArrayList";
        final String DONE_MESSAGE = "The following task has been marked done: ";
        responseList.add(DONE_MESSAGE);

        return responseList;
    }

    public ArrayList<String> addTag(String[] arrOfTags) {
        ArrayList<String> responseList = new ArrayList<>();
        assert responseList instanceof ArrayList : "Return type of the result of this method should be an ArrayList";
        final String ADD_TAG_MESSAGE_PREFIX = "The following tags ";
        final String ADD_TAG_MESSAGE_SUFFIX = "\nhave been added to the following task: ";

        responseList.add(ADD_TAG_MESSAGE_PREFIX);
        for (int i = 1; i < arrOfTags.length; i++) {
            responseList.add("#" + arrOfTags[i] + " ");
        }
        responseList.add(ADD_TAG_MESSAGE_SUFFIX);

        return responseList;
    }

    public String showError(String e) {
        return (e);
    }

    /**
     * Prints out all the tasks.
     * @param list list of tasks.
     */
    public ArrayList<String> listAllTasks(ArrayList<Task> list) {
        ArrayList<String> responseList = new ArrayList<>();
        assert responseList instanceof ArrayList : "Return type of the result of this method should be an ArrayList";
        final String LIST_MESSAGE = "Here are the tasks in your list: ";
        responseList.add(LIST_MESSAGE);

        int LENGTH_OF_LIST = list.size();
        if (LENGTH_OF_LIST > 0) {
            list.forEach(task -> {
                int counter = list.indexOf(task) + 1;
                responseList.add("  " + counter + "." + task);
            });
        } else {
            responseList.remove(0);
            responseList.add("No tasks found, add a task now!");
        }
        return responseList;
    }

    /**
     * Message displayed after item added to list
     * @param task task to be added.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     */
    public ArrayList<String> addMessage(Task task, int tasksLeft) {
        ArrayList<String> responseList = new ArrayList<>();
        assert responseList instanceof ArrayList : "Return type of the result of this method should be an ArrayList";

        responseList.add("Got it, the following task has been added:\n" + INDENT + INDENT + task +
                "\n" + INDENT + this.messageForTasksLeft(tasksLeft));

        return responseList;
    }

    /**
     * Message after searching for term with Find
     * @param listOfTasksFound list of taks found containing that term
     */
    public ArrayList<String> findMessage(ArrayList<Task> listOfTasksFound) {
        ArrayList<String> responseList = new ArrayList<>();
        assert responseList instanceof ArrayList : "Return type of the result of this method should be an ArrayList";

        if (listOfTasksFound.isEmpty()) {
            final String NO_TASKS_FOUND_MESSAGE = "No tasks found with that term";
            responseList.add(NO_TASKS_FOUND_MESSAGE);
        } else {
            final String MATCHING_TASKS_MESSAGE = "Here are the matching tasks in your list: \n";
            responseList.add(MATCHING_TASKS_MESSAGE);

            for (int i = 1; i <= listOfTasksFound.size(); i++) {
                responseList.add(i + "." + listOfTasksFound.get(i - 1));
            }
        }
        return responseList;
    }

    /**
     * Message displayed after deleting a task
     * @param task task to be deleted.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     */
    public ArrayList<String> deleteMessage(Task task, int tasksLeft) {
        ArrayList<String> responseList = new ArrayList<>();
        assert responseList instanceof ArrayList : "Return type of the result of this method should be an ArrayList";

        final String DELETED_TASK_MESSAGE = "Noted. I have removed this task:\n" + INDENT + INDENT + task + "\n" +
                this.messageForTasksLeft(tasksLeft);
        responseList.add (DELETED_TASK_MESSAGE);
        return responseList;
    }

    /**
     * Message to show number of tasks left.
     * @param tasksLeft integer value of tasks remaining in the list of tasks.
     * @return string of message.
     */
    public ArrayList<String> messageForTasksLeft(int tasksLeft) {
        ArrayList<String> responseList = new ArrayList<>();
        assert responseList instanceof ArrayList : "Return type of the result of this method should be an ArrayList";
        final String TASKS_LEFT_MESSAGE = "Now you have " + tasksLeft + " tasks in the list.";
        responseList.add(TASKS_LEFT_MESSAGE);
        if (tasksLeft == 0) {
            final String NO_TASKS_FOUND_MESSAGE = "No tasks found. Add a task now!";
            responseList.add(NO_TASKS_FOUND_MESSAGE);
        }
        return responseList;
    }

    /**
     * Shows icon based on whether task is done or not.
     * @param task task to be checked.
     * @return string of icon with brackets.
     */
    public String getStatusIcon(Task task) {
        final String STATUS_ICON_MESSAGE = "[" + (task.getIsDone() ? "\u2713" : "\u2718") + "] ";
        return (STATUS_ICON_MESSAGE);
    }

    public String readCommand() {
        return scan.nextLine();
    }
}
