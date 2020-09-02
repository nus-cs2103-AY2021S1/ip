package duke.task;

/**
 * Encapsulates an Ui object that interacts with the user.
 */
public class Ui {

    protected static TaskList tasks;

    Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    protected static String showWelcome() {
        return "Hello! I'm Yoo ( ^-^)/ \nWhat can I do for you?";
    }

    protected static String showWelcomeTaskList() {
        if (tasks.length() > 0) {
            return "\nBy the way, here are your saved tasks:\n" + tasks.toString();
        } else {
            return "";
        }
    }

    protected String showError(String message) {
        return message;
    }

    protected String showExit() {
        return "Bye! Come back soon ( ^-^)/";
    }

    /**
     * Prints the list of tasks.
     * @param tasks List of tasks.
     */
    protected String showTaskList(TaskList tasks) {
        return tasks.toString();
    }

    protected String congratulate() {
        return "Good job completing the task! \u256D( \uFF65\u3142\uFF65)\u0648";
    }

    protected String confirmDelete(Task t, TaskList tasks) {
        return "I've deleted the following task! \n" + t + "\nNow you have "
                + tasks.length() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)";
    }

    protected String confirmAdd(Task t, TaskList tasks) {
        return "I've added the following task! \n" + t + "\nNow you have "
                + tasks.length() + " tasks in the list (\u00B4\u30FB\u03C9\u30FB\uFF40)";
    }

    protected String showFoundKeyword() {
        return "Here are the matching tasks in your list!";
    }

    protected String showCannotFindKeyword() {
        return "Sorry, no matching tasks were found (\u3063*\u00B4\u25A1`)\u3063";
    }
}
