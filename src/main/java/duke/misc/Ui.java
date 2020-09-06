package duke.misc;

import java.util.List;

public class Ui {

    /**
     * Wraps the message inside a box.
     *
     * @param message the content of the message
     */
    public static String wrap(String message) {
        return "\n++++++++++++++++++++++++++++++++++++++++++\n"
                + message
                + "\n------------------------------------------\n";
    }

    /**
     * Greet the user and create a Scanner object.
     */
    public static String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return ("Hello from\n" + logo);
    }

    /**
     * Farewell the user.
     */
    public static String answerBye() {
        return ("See you again!\n");
    }

    /**
     * Prints the response of the DONE command.
     *
     * @param tasks the task that is marked as finished
     */
    public static String answerDone(List<String> tasks) {
        StringBuilder response = new StringBuilder("You have marked these task as finished: \n");
        for (String s : tasks) {
            response.append("    ").append(s).append("\n");
        }
        return response.toString();
    }

    /**
     * Prints the response of the LIST command
     *
     * @param tasks list of tasks to be printed
     */
    public static String answerList(List<String> tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        for (String s : tasks) {
            response.append("    ").append(s).append("\n");
        }
        return response.toString();
    }

    public static String answerFind(List<String> tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list: \n");
        for (String s : tasks) {
            response.append("    ").append(s).append("\n");
        }
        return response.toString();
    }

    /**
     * Prints the response of the DELETE command.
     *
     * @param tasks  the task deleted
     * @param count the number of tasks left in the list.
     */
    public static String answerDelete(List<String> tasks, int count) {
        StringBuilder response = new StringBuilder("Noted. I've removed these tasks: \n");
        for (String s : tasks) {
            response.append("    ").append(s).append("\n");
        }
        response.append("Now you have " + count + " tasks in the list.\n");
        return response.toString();
    }

    /**
     * Prints the response of the CLEAR command.
     */
    public static String answerClear() {
        return "All tasks cleared!\n";
    }

    /**
     * Prints the response of the commands that create a task.
     *
     * @param task  the task created
     * @param count the current number of tasks in the list
     */
    public static String answerTask(String task, int count) {
        return "Got it. I've added this task: \n"
                + "    " + task + "\n"
                + "Now you have " + count + " tasks in the list. \n";
    }

    public static String answerSave() {
        return "All changes saved!\n";
    }
}
