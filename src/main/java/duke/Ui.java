package duke;

import duke.user.User;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    public static void showLine(String msg) {
        System.out.println(msg);
    }

    public static String bye() {
        return "See you again!";
    }

    public static String addTask(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list.";
    }

    public static String getTasks(TaskList taskList) {
        int num = 1;
        String res = "Here are the tasks in your list:\n";
        for (Task task: taskList.getList()) {
            res += (num + "." + task.toString() + "\n");
            num++;
        }
        return res;
    }

    public static String doneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    public static String deleteTask(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public static String setTaskTag(int taskIndex, String oldTag, String newTag) {
        if (oldTag == null) {
            return "Noted. I've tagged Task " + taskIndex + " as " + newTag + ".";
        } else {
            return "Noted. I've tagged Task " + taskIndex + " as " + newTag + ". Its original tag is " + oldTag + ".";
        }
    }

    public static String login() {
        if (Login.isLogined()) {
            User user = Login.getUser();
            return "Hi, " + user.getNickname() + "! Long time no see.";
        } else {
            return "Invalid username or password. Please try again.";
        }
    }

    public static String accessMissing() {
        return "No Access for Command. Please login in first.";
    }

    public static String addUser() {
        return "A new user has been added successfully.";
    }
}
