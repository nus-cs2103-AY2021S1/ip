package duke.ui;

import java.util.Random;

import duke.support.Login;
import duke.task.Task;
import duke.task.TaskList;
import duke.user.User;


/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    /**
     * Shows when existing the app.
     */
    public static String bye() {
        return "See you again!";
    }

    /**
     * Shows when adding the task successfully.
     */
    public static String addTask(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list.";
    }

    /**
     * Shows when listing tasks.
     */
    public static String getTasks(TaskList taskList) {
        int num = 1;
        String res = "Here are the tasks in your list:\n";
        for (Task task: taskList.getList()) {
            res += (num + "." + task.toString() + "\n");
            num++;
        }
        return res;
    }

    /**
     * Shows when marking task as done.
     */
    public static String doneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Shows when deleting a task.
     */
    public static String deleteTask(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Shows when setting a tag.
     */
    public static String setTaskTag(int taskIndex, String oldTag, String newTag) {
        if (oldTag == null) {
            return "Noted. I've tagged Task " + taskIndex + " as " + newTag + ".";
        } else {
            return "Noted. I've tagged Task " + taskIndex + " as " + newTag + ". Its original tag is " + oldTag + ".";
        }
    }

    /**
     * Shows when logging in the app.
     */
    public static String login() {
        if (Login.isLogined()) {
            User user = Login.getUser();
            return "Hi, " + user.getNickname() + "! Long time no see.";
        } else {
            return "Invalid username or password. Please try again.";
        }
    }

    /**
     * Shows when there is no access.
     */
    public static String accessMissing() {
        return "No Access for Command. Please login in first.";
    }

    /**
     * Shows when adding a user.
     */
    public static String addUser() {
        return "A new user has been added successfully.";
    }

    /**
     * Shows when calling love command.
     */
    public static String love() {
        String[] reply;
        reply = new String[]{
            "Love you!", "Good Good Study, Day Day Up",
            "It is lucky to have you!", "Pig!", "Beauty", "Are you with me ?", "Take a photo to record the life~",
            "When I first saw you, I thought you were a pig!", "LMAO, nothing here!", "Nothing deserves my love except you."
        };
        Random random = new Random();

        int index = random.nextInt(reply.length);
        return reply[index];
    }
}
