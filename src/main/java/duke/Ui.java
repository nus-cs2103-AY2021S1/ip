package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui deals with interactions with users.
 */
public class Ui {
    private final String spacing = "\r\n";

    /**
     * Introduction of Mocha.
     *
     * @return Mocha's Introduction in String format.
     */
    public String sayIntroduction() {

        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        String mochaIntro = (nameIntro
                + spacing
                + greeting);
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
        String totalNumberOfTasksString = "Total number of tasks in list: ";

        if (task.getTaskType() == "ToDo") {

            String toDoHeader = "One new ToDo Task added: ";

            newTask = (toDoHeader
                    + spacing
                    + task.toString()
                    + spacing
                    + totalNumberOfTasksString
                    + sizeOfTasks);

        } else if (task.getTaskType() == "Deadline") {

            String deadlineHeader = "One new Deadline Task added: ";

            newTask = (deadlineHeader
                    + spacing
                    + task.toString()
                    + spacing
                    + totalNumberOfTasksString
                    + sizeOfTasks);

        } else if (task.getTaskType() == "Event") {

            String eventHeader = "One new Event Task added: ";

            newTask = (eventHeader
                    + spacing
                    + task.toString()
                    + spacing
                    + totalNumberOfTasksString
                    + sizeOfTasks);
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
        String taskDoneHeader = "Nice! One thing done:";

        newTaskDone = (taskDoneHeader
                + spacing
                + task.toString());
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

        String listAllTasksHeader = "Here are all of your tasks:";
        dividerStart = (listAllTasksHeader
                + spacing);

        for (int i = 0; i < tasks.getSize(); i++) {
            String taskToString = "";
            taskToString = ((i + 1) + "." + tasks.getTask(i).toString() + "\n");
            allTasks += taskToString;
        }

        dividerEnd += ("You have a total of "
                + tasks.getSize()
                + " tasks.");

        if (tasks.getSize() == 0) {
            listAllTasks = dividerEnd;
        } else {
            listAllTasks = dividerStart + allTasks + dividerEnd;
        }
        return listAllTasks;
    }

    /**
     * Mocha's salutations.
     */
    public String sayGoodbye() {
        String mochaGoodbye = "Bye! See ya soon!";
        return mochaGoodbye;
    }

    /**
     * Displays the message when a task is deleted.
     *
     * @param task Takes in a task object.
     * @param sizeOfTasks Takes in the size of the list of tasks.
     * @return the message for a deleted task in String format.
     */
    public String deleteTask(Task task, int sizeOfTasks) {
        String deleteTask = "";
        String deleteTaskHeader = "Noted. Removing the following task:";
        String deleteTaskFooter = "Total number of tasks left in the list: ";

        deleteTask = (deleteTaskHeader
                + spacing
                + task.toString()
                + spacing
                + deleteTaskFooter
                + sizeOfTasks);
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
        String findTask = "";

        String findTaskHeader = "Here are the matching tasks in your list:";
        dividerStart = (findTaskHeader + spacing);

        for (int i = 0; i < matchingTasks.size(); i++) {
            String matchingTask = "";
            matchingTask = ((i + 1)
                    + "."
                    + matchingTasks.get(i).toString()
                    + spacing);
            relevantTasks += matchingTask;
        }

        findTask = dividerStart + relevantTasks;
        return findTask;
    }
    
    public String help() {
        String helpHeader = "Here are all the commands: ";
        String toDoHelp = "todo {description} : Creates a new ToDo Task";
        String eventHelp = "event {description} /at dd/MM/yyyy HH:mm-HH:mm (24 hour clock)" +
                " : Creates a new Event Task for dd MMM yyyy";
        String deadlineHelp = "deadline {description} /by dd/MM/yyyy HH:mm (24 hour clock) " +
                ": Creates a new Deadline Task for dd MMM yyyy";
        String listHelp = "list : Lists all tasks";
        String doneHelp = "done {taskNumber} : Marks a task as done";
        String deleteHelp = "delete {taskNumber} : Deletes a task";
        String findHelp = "find {description} : Lists all tasks that contains the description";
        String byeHelp = "bye : Exits the application";
        String helpHelp = "help: Shows all commands";

        String commandsHelp = (helpHeader
                + spacing
                + toDoHelp
                + spacing
                + eventHelp
                + spacing
                + deadlineHelp
                + spacing
                + listHelp
                + doneHelp
                + spacing
                + deleteHelp
                + spacing
                + findHelp
                + spacing
                + byeHelp
                + spacing
                + helpHelp);
        return commandsHelp;
    }
}
