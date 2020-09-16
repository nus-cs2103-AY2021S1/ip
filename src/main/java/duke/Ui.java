package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Supports interactions with user.
 */
public class Ui {
    protected Scanner sc;
    protected static final String[] CMD_ARR = {"help", "add", "list", "done", "delete", "date", "bye"};
    protected static final String DIVIDER = "____________________________________________________________";
    protected static final String LOGO = " __________________________________________________________\n"
            + "|                                                          |\n"
            + "|  ____     _     _____   ____  _   _ ____   ____ _______  |\n"
            + "| |  _ \\   / \\   |  __ \\ / __ \\| \\ | |  _ \\ / __ \\__   __| |\n"
            + "| | |_) | /   \\  | |__) | |  | |  \\| | |_) | |  | | | |    |\n"
            + "| |  _ < / /_\\ \\ |  _  /| |  | | . ` |  _ <| |  | | | |    |\n"
            + "| | |_) / _____ \\| | \\ \\| |__| | |\\  | |_) | |__| | | |    |\n"
            + "| |____/_/     \\_\\_|  \\_\\\\____/|_| \\_|____/ \\____/  |_|    |\n"
            + "|                                                          |\n"
            + "|__________________________________________________________|\n";

    public Ui (Scanner sc) {
        this.sc = sc;
    }
    /**
     * Displays the startup UI and prompts user to enter input.
     * @param taskList List of tasks.
     */
    public void start(TaskList taskList) {
        this.printDivider();
        System.out.println(LOGO);
        this.printDivider();
        System.out.println("Hello, I am BaronBot!");
        System.out.println("What can I do for you?");
        this.printDivider();
    }

    public String readCommand() {
        return sc.nextLine().toLowerCase();
    }

    public String displayError(String errorMsg) {
        System.out.println(errorMsg);
        return errorMsg;
    }

    /**
     * Displays the divider that separates different actions.
     */
    public String printDivider() {
        System.out.println(DIVIDER);
        return DIVIDER;
    }

    /**
     * Displays a prompt for the user to enter additional input.
     */
    public String printAdditionActionMessage() {
        this.printDivider();
        System.out.println("What else would you like to do?");
        this.printDivider();
        return "What else would you like to do?";
    }

    /**
     * Displays the goodbye message.
     */
    public String printGoodbyeMessage() {
        this.printDivider();
        System.out.println("Bye! See you around :)");
        this.printDivider();
        return "Bye! See you around :)";
    }

    /**
     * Displays the list of commands the user can enter.
     */
    public String printHelp() {
        String s = "Here are the commands you can use:\n";
        for (int i = 0; i < CMD_ARR.length; i++) {
            s += (i + 1) + ". " + CMD_ARR[i] + "\n";
        }
        System.out.println(s);
        return s;
    }

    /**
     * Displays the types of tasks the user can enter.
     */
    public String printTaskTypes() {
        String s = "What kind of task is it?\n"
                + " - Todo\n"
                + " - Deadline\n"
                + " - Event\n";
        System.out.println(s);
        return s;
    }

    /**
     * Displays the prompt for the user to enter the task.
     */
    public String printEnterTaskPrompt() {
        String s = "Please enter the task";
        System.out.println();
        return s;
    }

    /**
     * Displays the example for the deadline format.
     */
    public String printDeadlineExample() {
        String s = "Please enter the task followed by the date and time of the deadline\n"
                + "e.g., submit report ,11/10/2019 1700\n";
        System.out.println(s);
        return s;
    }

    /**
     * Displays the example for the duke.task.Event format.
     */
    public String printEventExample() {
        String s = "Please enter the event followed by the date and time of the event\n"
                + "e.g., team project meeting ,2/10/2019 1400-1600";
        System.out.println(s);
        return s;
    }

    /**
     * Displays the acknowledgement of the user's added task.
     * @param taskList List of tasks.
     */
    public String printAddAcknowledgement(TaskList taskList) {
        String s = "Alright, I've added this task:\n"
                + taskList.getMostRecentTask().toString() + "\n"
                + "You now have " + taskList.getTaskListSize() + " tasks on your list\n";
        System.out.println(s);
        return s;
    }

    /**
     * Displays the tasks currently on the list of tasks.
     * @param taskList List of tasks.
     */
    public String printList(TaskList taskList) {
        String s = "These are the tasks on your list:\n";
        for (int j = 0; j < taskList.getTaskListSize(); j++) {
            s += (j + 1)
                    + ". "
                    + taskList.getTask(j).toString()
                    + "\n";
        }
        System.out.println(s);
        return s;
    }

    /**
     * Displays the prompt for the user to specify which task is done.
     */
    public String printDonePrompt() {
        String s = "Which task do you want to mark as done?";
        System.out.println(s);
        return s;
    }

    /**
     * Displays the acknowledgement of a task status being changed to done.
     * @param taskList List of tasks.
     * @param taskNum duke.task.Task specified to be changed to done.
     */
    public String printDoneAcknowledgement(TaskList taskList, int taskNum) {
        String s = "Good job! This task is now marked as done:\n"
                + taskList.getTask(taskNum - 1).toString()
                + "\n";
        System.out.println(s);
        return s;
    }

    /**
     * Displays the prompt for the user to delete a task.
     */
    public String printDeletePrompt() {
        String s = "Which task do you want to delete?\n";
        System.out.println(s);
        return s;
    }

    /**
     * Displays the acknowledgement of the task that the user deleted from the list of tasks.
     * @param taskList List of tasks.
     * @param task duke.task.Task that was deleted.
     */
    public String printDeleteAcknowledgement(TaskList taskList, Task task) {
        String s = "Alright, the following task has been removed:\n"
                + task.toString()
                + "You now have " + taskList.getTaskListSize() + " tasks on your list\n";
        System.out.println(s);
        return s;
    }

    public String printFindPrompt() {
        String s = "What are you trying to find? Search using a keyword.\n";
        System.out.println(s);
        return s;
    }

    public String printFoundTasksHeader() {
        String s = "These are the tasks that match the keyword:\n";
        System.out.println(s);
        return s;
    }

    public String printTask(TaskList taskList, int taskNum) {
        String s = (taskNum + 1)
                + ". "
                + taskList.getTask(taskNum).toString()
                + "\n";
        System.out.println(s);
        return s;
    }
}
