package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Supports interactions with user.
 */
public class Ui {

    /**
     * Array containing the commands a user can use.
     */
    protected static final String[] CMD_ARR = {"Help", "List", "Add", "Done", "Delete", "Find", "Priority", "Bye"};

    /**
     * Scanner to get user input.
     */
    protected Scanner sc;

    /**
     * Creates a Ui object.
     * @param sc Scanner for user input.
     */
    public Ui (Scanner sc) {
        this.sc = sc;
    }

    /**
     * Returns the error message of an exception.
     * @param errorMsg Message representing the exception.
     * @return Message representing the exception.
     */
    public String displayError(String errorMsg) {
        System.out.println(errorMsg);
        return errorMsg;
    }

    /**
     * Returns a prompt for the user to enter additional input.
     * @return Message to enter additional input.
     */
    public String printAdditionActionMessage() {
        System.out.println("What else would you like to do?");
        return "\nWhat else would you like to do?\n";
    }

    /**
     * Returns the goodbye message.
     * @return Goodbye message.
     */
    public String printGoodbyeMessage() {
        System.out.println("Bye! See you around");
        return "Bye! See you around\n";
    }

    /**
     * Returns the list of commands the user can enter.
     * @return List of commands.
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
     * Returns a prompt associated with a command.
     * @param s Prompt from command.
     * @return Prompt
     */
    public String printPrompt(String s) {
        System.out.println(s);
        return s;
    }

    /**
     * Returns an acknowledgement that a task was added
     * @param taskList List of tasks.
     * @return Acknowledgement of the add.
     */
    public String printAddAcknowledgement(TaskList taskList) {
        String s = "Alright, I've added this task:\n"
                + taskList.getMostRecentTask().toString() + "\n"
                + "You now have " + taskList.getTaskListSize() + " tasks on your list\n";
        System.out.println(s);
        return s;
    }

    /**
     * Returns the tasks currently on the list of tasks.
     * @param taskList List of tasks.
     * @return List of tasks.
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
     * Returns the acknowledgement of a task being updated to done.
     * @param taskList List of tasks.
     * @param taskNum Task specified to be changed to done.
     * @return Acknowledgement of the update.
     */
    public String printDoneAcknowledgement(TaskList taskList, int taskNum) {
        String s = "Good job! This task is now marked as done:\n"
                + taskList.getTask(taskNum - 1).toString()
                + "\n";
        System.out.println(s);
        return s;
    }

    /**
     * Returns the acknowledgement of the task that the user deleted from the list of tasks.
     * @param taskList List of tasks.
     * @param task Task that was deleted.
     * @return Acknowledgement of the deletion.
     */
    public String printDeleteAcknowledgement(TaskList taskList, Task task) {
        String s = "Alright, the following task has been removed:\n"
                + task.toString()
                + "\n"
                + "You now have " + taskList.getTaskListSize() + " tasks on your list.\n";
        System.out.println(s);
        return s;
    }

    /**
     * Returns the acknowledgement of the updated priority level of a task.
     * @param taskList List of tasks.
     * @param taskNum Task that was updated.
     * @return Acknowledgement of the updated priority level.
     */
    public String printPrioritySetAcknowledgement(TaskList taskList, int taskNum) {
        String s = "Alright, the following task priority has been updated:\n"
                + taskList.getTask(taskNum - 1).toString() + "\n";
        System.out.println(s);
        return s;
    }

    /**
     * Returns the string representation of a task on the task list.
     * @param taskList List of tasks.
     * @param taskNum Number on the list of the task.
     * @return String representation of the task.
     */
    public String printTask(TaskList taskList, int taskNum) {
        String s = (taskNum + 1)
                + ". "
                + taskList.getTask(taskNum).toString()
                + "\n";
        System.out.println(s);
        return s;
    }
}
