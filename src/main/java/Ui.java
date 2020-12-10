import java.util.List;
import java.util.Scanner;

/**
 * Handles the interaction with user, such as reading input and
 * printing output.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the welcome message.
     */
    public static String showWelcomeMessage() {
        String intro = "Hello, I am Doraemon! I can help you remember and keep track of your tasks.\n"
                + "I assume that you are as forgetful as Nobita. Remember to bribe me with loads of Dorayaki too!";
        return intro;
    }

    /**
     * Returns the goodbye message.
     */
    public static String showByeMessage() {
        String bye = "Goodbye, I need to find my sister now! >_<";
        return bye;
    }

    /**
     * Returns string of the list of tasks when user calls for 'list'.
     * @param taskList The list of task to be printed.
     */
    public String showListTasks(List<Task> taskList) {
        StringBuilder reply = new StringBuilder();
        reply.append("I remembered your tasks well because I have such good memory! :D Here you go:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task tsk = taskList.get(i);
            reply.append("Task " + (i + 1) + ": " + tsk + (i == taskList.size() - 1 ? "" : "\n"));
        }
        return reply.toString();
    }

    /**
     * Returns string of the list of tasks with same date as what user demands
     * when user calls for 'print /date'.
     * @param taskList The list of tasks with same date.
     */
    public String showRequiredTasks(List<Task> taskList) {
        if (taskList.size() > 0) {
            StringBuilder reply = new StringBuilder();
            reply.append("I have matched your queries as below. "
                    + "Don't forget to complete them if not your mom will scold you!\n");
            for (int i = 0; i < taskList.size(); i++) {
                reply.append((i + 1) + ". " + taskList.get(i) + (i == taskList.size() - 1 ? "" : "\n"));
            }
            return reply.toString();
        } else {
            String failToFindMsg = "Oh no >_< I can't seem to find any matching tasks.";
            return failToFindMsg;
        }
    }

    /**
     * Prints the task that has been added to the list.
     * @param tsk The task that has been added.
     */
    public String showAddTask(Task tsk) {
        String addTaskMsg = "I have added this task for you:\n" + tsk;
        return addTaskMsg;
    }

    /**
     * Prints the task that has been marked as done when user calls 'done /task'.
     * @param tsk The task that is completed.
     */
    public String showDoneTask(Task tsk) {
        String doneTaskMsg = "Excellent! Don't procrastinate like Nobita :P. You have completed:\n" + tsk;
        return doneTaskMsg;
    }

    /**
     * Prints the task that has been deleted when user calls for 'delete /task'
     * @param tsk The task that is deleted.
     */
    public String showDeleteTask(Task tsk) {
        String deleteTaskMsg = "I have removed this task for you:\n" + tsk;
        return deleteTaskMsg;
    }

    /**
     * Prints the total number of tasks in list.
     * @param num The total number of tasks.
     */
    public String showTotalTasks(int num) {
        String totalTaskMsg = "You have " + num + " task(s) in your list!";
        return totalTaskMsg;
    }

    /**
     * Prints the error when an IOException occurs.
     */
    public String showLoadingError() {
        String loadErrorMsg = "Error with reading / writing file!";
        return loadErrorMsg;
    }

    /**
     * Prints the error when a DukeException occurs.
     * @param e The DukeException that arises.
     */
    public String showDukeError(DukeException e) {
        return e.toString();
    }
}
