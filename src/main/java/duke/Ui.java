package duke;

import java.util.ArrayList;

/**
 * Ui class handles all the interactions with user of the Duke
 * chatbot. Ui object will print directly to the output.
 */
public class Ui {
    private final String SERVANT_SPEAK;
    private final String USER_PROMPT;

    public Ui () {
        // Initialise strings to separate messages from Duke
        // and commands from CLI
        this.SERVANT_SPEAK = "Duke:\n";
        this.USER_PROMPT = "Your Command Sire:";
    }

    /**
     * Returns an indicator that the following messages
     * are from Duke
     *
     * @return String
     */
    public String getServantSpeak() {
        return this.SERVANT_SPEAK;
    }

    /**
     * Returns a message to prompt the user for his/her input
     *
     * @return String
     */
    public String getUserPrompt() {
        return this.USER_PROMPT;
    }

    /**
     * Prints a welcome message every time the chat bot is activated.
     *
     * @return Nothing.
     */
    public void welcomeMessage() {
        // Introduction at the beginning of the chat
        System.out.println(SERVANT_SPEAK
                + "    Greetings my Liege.\n"
                + "    Why have you summoned me?\n");
    }

    /**
     * Prints all the available commands that the user can input.
     * This includes the format of the command so as to be accepted as a command.
     *
     * @return Nothing.
     */
    public void printAvailableCommands() {
        System.out.println(SERVANT_SPEAK
                + "    These are your available commands my Lord:\n\n"
                + "    help - Show list of available commands\n"
                + "    todo <Description of task>"
                + " - To add a normal to do task\n"
                + "    deadline <Description of task>"
                + " /by <Date in YYYY-MM-DD> <Time in hh:mm>"
                + " - To add a task with a deadline\n"
                + "    event <Description of event>"
                + " /at <Date in YYYY-MM-DD> <Time in hh:mm>"
                + " - To add an event\n"
                + "    list - Show list of tasks\n"
                + "    done <task number> - Mark task as done\n"
                + "    delete <task number> - Delete task from list\n");
    }

    /**
     * Prints a message to indicate that a task has been added to the task list.
     * Prints back to the console the task that was added to the task list.
     *
     * @param task task that was added to the task list.
     * @param size size of task list.
     * @return Nothing.
     */
    public void printTaskAddedMessage(Task task, int size) {
        System.out.println(SERVANT_SPEAK
                + "    As you wish Sire. I have added the task:\n       "
                + task.toString() + "\n"
                + "    Now you have " + size
                + " tasks in the list.\n");
    }

    /**
     * Prints a message to indicate that the task has been marked as done.
     * Prints back to the console the task that was marked as done.
     *
     * @param task task that was marked as done.
     * @return Nothing.
     */
    public void printMarkAsDoneMessage(Task task) {
        System.out.println(SERVANT_SPEAK
                + "    As you wish Sire. I have marked this task as done:\n"
                + "       " + task.toString());
    }

    /**
     * Prints a message to indicate that the task has been deleted.
     * Prints back to the console the task that was removed from the task list.
     *
     * @param task task that was removed from the task list.
     * @return Nothing.
     */
    public void printTaskDeletedMessage(Task task) {
        System.out.println(SERVANT_SPEAK
                + "    As you wish Sire. I removed this task:\n"
                + "       " + task.toString());
    }

    /**
     * Prints all the task in the task list.
     *
     * @param userTasks This is the task list to be printed.
     * @return Nothing.
     */
    public void printAllTasks(ArrayList<Task> userTasks) {
        System.out.println(SERVANT_SPEAK
                + "    Here are your tasks your Majesty:");
        System.out.println(new TaskList(userTasks).toString());
    }

    /**
     * Prints all the tasks in the task list
     * that were marked as done.
     *
     * @param userTasksDone This is the list of tasks to be printed.
     * @return Nothing.
     */
    public void printAllTasksDone(ArrayList<Task> userTasksDone) {
        System.out.println(SERVANT_SPEAK
                + "    Here are all the tasks that you have"
                + " done your Majesty:");
        System.out.println(new TaskList(userTasksDone).toString());
    }

    /**
     * Prints all the tasks in the task list
     * that were not marked as done.
     *
     * @param userTasksNotDone This is the list of tasks to be printed.
     * @return Nothing.
     */
    public void printAllTasksNotDone(ArrayList<Task> userTasksNotDone) {
        System.out.println(SERVANT_SPEAK
                + "    Here are all the tasks that you have"
                + " not done your Majesty:");
        System.out.println(new TaskList(userTasksNotDone).toString());
    }

    /**
     * Prints all the to do tasks in the task list.
     *
     * @param userToDos This is the list of to do to do tasks to be printed.
     * @return Nothing.
     */
    public void printAllToDos(ArrayList<Task> userToDos) {
        System.out.println(SERVANT_SPEAK
                + "    Here are all your to do tasks your Majesty:");
        System.out.println(new TaskList(userToDos).toString());
    }

    /**
     * Prints all the events in the task list.
     *
     * @param userDeadlines This is the list of deadlines to be printed.
     * @return Nothing.
     */
    public void printAllDeadlines(ArrayList<Task> userDeadlines) {
        System.out.println(SERVANT_SPEAK
                + "    Here are all your tasks with a deadline your Majesty:");
        System.out.println(new TaskList(userDeadlines).toString());
    }

    /**
     * Prints all the events in the task list.
     *
     * @param userEvents This is the list of events to be printed.
     * @return Nothing.
     */
    public void printAllEvents(ArrayList<Task> userEvents) {
        System.out.println(SERVANT_SPEAK
                + "    Here are all your events your Majesty:");
        System.out.println(new TaskList(userEvents).toString());
    }

    /**
     * Prints a message to the console when the user ends the chat with Duke.
     *
     * @return Nothing.
     */
    public void printByeMessage() {
        System.out.println(SERVANT_SPEAK
                + "    It was a pleasure to serve you my Liege.\n"
                + "    Till next time.");
    }

    /**
     * Prints out the error message generated by the DukeException object.
     *
     * @param ex This is the DukeException object created by the error.
     * @return Nothing.
     */
    public void printError(DukeException ex) {
        System.out.println(ex);
    }
}
