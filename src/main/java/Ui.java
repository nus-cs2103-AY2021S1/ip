import java.io.IOException;

/**
 * Ui class that deals with interactions with the user.
 */
public class Ui {

    public final static String HORIZONTAL_LINE = "________________________________________________________" + "\n";

    /**
     * Constructor that creates a Ui object, with a Scanner that reads user input.
     */
    public Ui() {}

    /**
     * Prints to user a welcome message when the program is run.
     */
    public static String printWelcome() {
        String hello = "hey it's Cardi B!" + "\n" + "what do you need" + "\n";
        return HORIZONTAL_LINE + "\n" + hello + HORIZONTAL_LINE;
    }

    /**
     * Prints to user if the user has entered the terminate command.
     */
    public static String printBye() {
        String bye = "Okrrr bye!" + "\n";
        return HORIZONTAL_LINE + "\n" + bye + HORIZONTAL_LINE;
    }

    /**
     * Prints to user if a Task has been marked as complete.
     * @param task the Task that was marked as complete.
     * @return completed message String
     */
    public String printDone(Task task) {
        return HORIZONTAL_LINE + "Proud of you sister! I've marked this task as done:" + "\n"
                + task.toString() + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints to user if a Task has been successfully deleted from the TaskList
     * and the current number of Tasks in it.
     * @param taskList the TaskList associated with the current Duke object.
     * @param task the Task that was deleted from the TaskList.
     * @return delete message String
     */
    public String printDelete(TaskList taskList, Task task) {
        return HORIZONTAL_LINE + "Okrr sister. I've removed this task:" + "\n"
                + task.toString() + "\n" + "Now you have " + taskList.getSize() + " tasks in the list."
                + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints the list of tasks in the TaskList
     * @param taskList the TaskList associated with the current Duke object.
     * @return List of tasks
     */
    public String printList(TaskList taskList) {
        StringBuilder tasks = new StringBuilder();
        if (taskList.getSize() > 0) {
            tasks.append(HORIZONTAL_LINE + "Here are the tasks in your list sister:" + "\n");
            for (int i = 1; i <= taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                tasks.append(i + "." + task.toString()).append("\n");
            }
        } else {
            tasks.append("You have no tasks sister!");
        }
        tasks.append(HORIZONTAL_LINE);
        return tasks.toString();
    }

    /**
     * Prints the new Task that has been added.
     * @param taskList the TaskList associated with the current Duke object.
     * @param task the Task that is being added to the TaskList.
     * @return Add message String
     */
    public String printAdd(TaskList taskList, Task task) {
        return HORIZONTAL_LINE + "Got it sister! I've added this task:" + "\n" + task.toString() + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list."
                + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints to user if a DukeException has been caught, printing an error message.
     * @param e a DukeException containing the error message.
     * @return Duke Error message
     */
    public String printDukeError(DukeException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Prints to the user an error message when an I0Exception has been caught.
     * @param e an IOException containing an error message.
     * @return IO Error message
     */
    public String printIOError(IOException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Prints a statement depending on whether the file has been successfully created.
     * @param created a Boolean to indicate whether the file has been created.
     * @return Message indicating whether the file has been created
     */
    public String printHasCreated(Boolean created) {
        if (created) {
            return "New file created" + "\n";
        } else {
            return "Failed to create file";
        }

    }

    /**
     * Prints a statement with the input message when an error occurs.
     * @param message a String containing an error message
     * @return String of error message
     */
    public String printError(String message) {
        return "Oh dear! There seems to be an error" + "\n" + message;
    }

    /**
     * Prints to user the tasks that contain a match to the word.
     * @param word the word that is being searched for.
     * @param taskList the TaskList associated with the current Duke object.
     * @return List of Strings of tasks that match the word
     */
    public String find(String word, TaskList taskList) throws DukeDuplicateException {
        assert(!word.isEmpty());
        assert(taskList.getSize() > 0);
        TaskList list = new TaskList();
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.description.contains(word)) {
                list.addTask(task);
            }
        }
        StringBuilder tasks = new StringBuilder();
        if (list.getSize() > 0) {
            tasks.append(HORIZONTAL_LINE + "Here are the matching tasks in your list: \n");
            for (int i = 1; i <= list.getSize(); i++) {
                tasks.append(String.format("%s. %s%n", i, list.getTask(i).toString()))
                        .append("\n");
            }
        } else {
            tasks.append("Oops! There are no matching tasks.");
        }
        return tasks.toString();
    }

    public String printDuplicateError(DukeDuplicateException e) {
        return e.getMessage() + "\n";
    }

}
