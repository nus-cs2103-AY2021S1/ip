package main.java.duke;

import main.java.duke.task.Task;

/**
 * Encapsulates the user interface of duke
 */
public class Ui {

    /** String to print upon adding a new task to the task list */
    private final String addTaskMessage = "Got it. I've added this task:";

    /** String to print upon completion of a task */
    private final String completeTaskMessage = "Nice! I've marked this task as done:";

    /** String to print upon deletion of a task */
    private final String deleteTaskMessage = "Noted. I've removed this task:";

    /** String to print on exit */
    private final String exitMessage = "Bye. Hope to see you again soon!";

    /** String to print at the start of a sublist of matched tasks */
    private String foundTasksMessage = "Here are the matching tasks in your list:";

    /** String to print if no matched tasks were found */
    private String foundZeroTasksMessage = "I'm sorry, but none of the tasks match the keyword";

    /** Strings to print on start-up */
    private final String[] greetMessage = new String[] {"Hello! I'm Duke", "What can I do for you?"};

    /** Horizontal line */
    private final String horizontalLine = "    ____________________________________________________________";

    /** String to print when an invalid input is detected */
    private final String invalidSyntaxMessage = "OOPS!!! I'm sorry, but I don't know what that means :(";

    /** String to print when an invalid task index is detected */
    private final String invalidTaskIndexMessage = "Please enter a valid task index";

    /** String to print as a header preceding the task list */
    private final String listTasksMessage = "Here are the tasks in your list:";

    /** String to print to reflect the number of tasks in the current task list */
    private final String numOfTasksMessage = "Now you have %d tasks in the list";

    /** Text indentation */
    private final String textIndentation = "     ";

    /** String to print when the task list is empty */
    private final String zeroTasksMessage = "Your task list is currently empty. YAY!!! :D";

    /**
     * Gets the string to print to reflect the number of tasks in the current task list
     *
     * @param tasks Task list
     * @return zeroTaskMessage if the task list is empty, numOfTasksMessage formatted with the number of
     * tasks if the list is not empty
     */
    private String getNumOfTasksString(TaskList tasks) {
        int numOfTasks = tasks.getNumOfTasks();
        if (numOfTasks == 0) {
            return zeroTasksMessage;
        } else {
            return String.format(numOfTasksMessage, numOfTasks);
        }
    }

    /**
     * Prints the strings with top and bottom horizontal lines and indentation
     *
     * @param strings Array of strings to be printed
     */
    void print(String[] strings) {
        System.out.println(this.horizontalLine);
        for(String string : strings) {
            System.out.print(this.textIndentation);
            System.out.println(string);
        }
        System.out.println(this.horizontalLine);
    }

    /**
     * Prints strings upon completing a task
     *
     * @param task Completed task
     */
    public void printCompleteTask(Task task) {
        String[] strings = new String[] {this.completeTaskMessage, task.toString()};
        this.print(strings);
    }

    /**
     * Prints strings upon creating a new task
     *
     * @param tasks Current task list
     * @param task Created task
     */
    public void printCreateTask(TaskList tasks, Task task) {
        String[] strings = new String[] {this.addTaskMessage, task.toString(), getNumOfTasksString(tasks)};
        this.print(strings);
    }

    /**
     * Prints strings upon deleting a task
     *
     * @param tasks Current task list
     * @param task Deleted task
     */
    public void printDeleteTask(TaskList tasks, Task task) {
        String[] strings = new String[] {this.deleteTaskMessage, task.toString(),getNumOfTasksString(tasks)};
        this.print(strings);
    }

    /**
     * Prints strings upon exit
     */
    public void printExit() {
        String[] strings = new String[] {this.exitMessage};
        this.print(strings);
    }

    /**
     * Prints strings upon start-up
     */
    void printHello() {
        this.print(this.greetMessage);
    }

    /**
     * Prints strings upon handling an invalid input
     */
    public void printInvalidInput() {
        String[] strings = new String[] {this.invalidSyntaxMessage};
        this.print(strings);
    }

    /**
     * Prints strings upon receiving an invalid task index
     */
    public void printInvalidTaskIndex() {
        String[] strings = new String[] {this.invalidTaskIndexMessage};
        this.print(strings);
    }

    /**
     * Prints string representation of all tasks
     *
     * @param tasks Current task list
     */
    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            this.print(new String[] {this.zeroTasksMessage});
        } else {
            String[] strings = new String[tasks.getNumOfTasks() + 1];
            strings[0] = this.listTasksMessage;
            for (int i = 1; i < strings.length; i++) {
                strings[i] = tasks.getTaskAt(i - 1).toString();
            }
            this.print(strings);
        }
    }


    /**
     * Prints strings upon finding matching tasks
     * @param tasks Sublist of tasks
     */
    public void printTasksWithKeyword(Task[] tasks) {
        String[] strings = new String[tasks.length + 1];
        if (tasks.length == 0) {
            strings[0] = this.foundZeroTasksMessage;
        } else {
            strings[0] = this.foundTasksMessage;
            for (int i = 1; i < strings.length; i++) {
                strings[i] = tasks[i - 1].toString();
            }
        }
        this.print(strings);
    }
}
