package duke;

public class Ui {

    /**
     * Prints message in exception.
     *
     * @param e Duke Exception
     */
    public static String printException(Exception e) {
        String errorMessage = "Whoops! Something went wrong...\nError: " + e.getMessage();
        return errorMessage;
    }

    public static String informTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public static String informTaskDeleted(Task task) {
        return "Alright! I've removed this task:\n" + task;
    }

    public static String informTasksDeleted(Task[] tasks) {
        String userMessage = "Alright! I've removed the following tasks:\n";
        for (int i = 0; i < tasks.length; i ++) {
            int taskNumber = i + 1;
            userMessage += String.format("%d. %s\n", taskNumber, tasks[i]);
        }
        return userMessage;
    }

    public static String informNumberOfTasksRemaining(TaskList taskList) {
        return "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public static String informNewTask(Task task) {
        return "Roger. I've added this task:\n" + task;
    }

    public static String informInvalidCommand() {
        return "I'm sorry, but I don't know what that means :(";
    }

    public static String informNoTaskFound() {
        return "Sorry, no matching task was found!";
    }
}
