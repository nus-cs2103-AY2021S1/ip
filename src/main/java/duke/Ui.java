package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui deals with interactions with users.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "______________________________________________________";

    /**
    * Introduction of Mocha.
     * @return
     */
    public String sayIntroduction() {

        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        String mochaIntro = (HORIZONTAL_LINE
                + "\r\n"
                + nameIntro
                + "\r\n"
                + greeting
                + "\r\n"
                + HORIZONTAL_LINE);
        return mochaIntro;
    }

    /**
     * Creates a new Parser object.
     *
     * @return a Parser object.
     */
    public Parser createParser() {
        return new Parser();
    }

    /**
     * Creates a new Scanner object that uses the same list of Tasks as the existing file.
     *
     * @return s Scanner object.
     */
    public Scanner createUserInputScanner() {
        return new Scanner(System.in);
    }

    /**
     * Displays the message when a task is added.
     *
     * @param task  Takes in a task object.
     * @param sizeOfTasks The size of the list of tasks.
     */
    public String addTask(Task task, int sizeOfTasks) {
        String newTask = "";
        
        if (task.getTaskType() == "ToDo") {

            newTask = (HORIZONTAL_LINE
                    + "\r\n"
                    + "One new ToDo Task added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + HORIZONTAL_LINE);

        } else if (task.getTaskType() == "Deadline") {

            newTask = (HORIZONTAL_LINE
                    + "\r\n"
                    + "One new Deadline added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + HORIZONTAL_LINE);

        } else if (task.getTaskType() == "Event") {
            newTask = (HORIZONTAL_LINE
                    + "\r\n"
                    + "One new Deadline Task added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + HORIZONTAL_LINE);
        }
        return newTask;
    }

    /**
     * Displays the message when a task is marked done.
     *
     * @param task Takes in a task object.
     */
    public String markTaskDone(Task task) {
        String newTaskDone = "";
        newTaskDone = (HORIZONTAL_LINE
                + "\r\n"
                + "Nice! One thing done: \r\n"
                + task.toString()
                + "\r\n"
                + HORIZONTAL_LINE);
        return newTaskDone;
    }

    /**
     * Displays all tasks.
     *
     * @param tasks Takes in a list of tasks.
     */
    public String listAllTasks(TaskList tasks) {
        String dividerStart = "";
        String allTasks = "";
        String dividerEnd = "";
        String listAllTasks = "";
        
        dividerStart = (HORIZONTAL_LINE
                + "\r\n"
                + "Here are all of your tasks:"
                + "\r\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            String taskToString = "";
            taskToString = ((i + 1) + "." + tasks.getTask(i).toString() +"\n");
            allTasks += taskToString;
        }

        dividerEnd += ("\r\n"
                + "You have a total of "
                + tasks.getSize()
                + " tasks."
                + "\r\n"
                + HORIZONTAL_LINE);
        
        listAllTasks = dividerStart + allTasks + dividerEnd;
        return listAllTasks;
    }

    /**
     * Mocha's salutations.
     */
    public String sayGoodbye() {
        String mochaGoodbye = "";
       mochaGoodbye = (HORIZONTAL_LINE
                + "\r\n"
                + "Bye! See ya soon!"
                + "\r\n"
                + HORIZONTAL_LINE);
        return mochaGoodbye;
    }

    /**
     * Displays the message when a task is deleted.
     * 
     * @param task Takes in a task object.
     * @param sizeOfTasks Takes in the size of the list of tasks.
     * @return
     */
    public String deleteTask(Task task, int sizeOfTasks) {
        String deleteTask = "";
        deleteTask = (HORIZONTAL_LINE
                + "\r\n"
                + "Noted. Removing the following task:"
                + "\r\n"
                + task.toString()
                + "\r\n"
                + "Total number of tasks left in the list: "
                + sizeOfTasks
                + "\r\n"
                + HORIZONTAL_LINE);
        return deleteTask;
    }

    /**
     * Displays all task that contains the word to find.
     *
     * @param matchingTasks A list of matching tasks.
     */
    public String findTask(ArrayList<Task> matchingTasks) {
        String dividerStart = "";
        String relevantTasks = "";
        String dividerEnd = "";
        String findTask = "";

        dividerStart = (HORIZONTAL_LINE
                + "\r\n"
                + "Here are the matching tasks in your list:"
                + "\r\n");

        for (int i = 0; i < matchingTasks.size(); i++) {
            String matchingTask = "";
            matchingTask = ((i + 1) 
                    + "."
                    + matchingTasks.get(i).toString()
                    + "\n");
            relevantTasks += matchingTask;
        }
        
        dividerEnd = HORIZONTAL_LINE;
        findTask = dividerStart + relevantTasks + dividerEnd;
        return findTask;
    }
}
