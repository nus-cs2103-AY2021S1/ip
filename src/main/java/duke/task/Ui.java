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

    protected String updateSuccess(Task t) {
        return "Update successful! (＾◡＾)\n" + t;
    }

    protected String showHelp() {
        return "List of Yoo's commands!\n \n"
                 + "todo: add Todo task\n"
                + "       todo DESCRIPTION\n"
                + "deadline: add Deadline task\n"
                + "       deadline DESCRIPTION /by YYYY-MM-DD\n"
                + "event: add Event task\n"
                + "       event DESCRIPTION /at YYYY-MM-DD\n"
                + "bye: ends convo with Yoo\n"
                + "delete: deletes a task\n"
                + "       delete INDEX\n"
                + "done: marks a task as completed\n"
                + "       done INDEX\n"
                + "find: find tasks with matching keyword\n"
                + "       find KEYWORD\n"
                + "help: displays list of Yoo's commands\n"
                + "list: shows list of tasks\n"
                + "update: updates task description or time\n"
                + "       update INDEX /d DESCRIPTION\n"
                + "       update INDEX /t YYYY-MM-DD\n"
                + "\nDo refer to the User Guide for more details!";
    }
}


























