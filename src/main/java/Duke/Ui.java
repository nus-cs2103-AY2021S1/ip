package Duke;

import Friend.Friend;
import Friend.FriendList;
import Tasks.Task;
import Tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private String line = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructor for the class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Generate a greeting when user first launch the app.
     */
    public void greeting() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    /**
     * Generate a line to divide between replies.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Read user input.
     * @return a String of the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Generate the list of tasks.
     */
    public String generateList(TaskList taskList) {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getList().size(); i++) {
            Task t = taskList.getList().get(i);
            list += "\n" + (i + 1) + ". " + t.printTask();
        }
        return list;
    }

    /**
     * Generate the list of friends.
     */
    public String generateFriendList(FriendList friendList) {
        String list = "Here are all your friends:\n";
        for (int i = 0; i < friendList.getList().size(); i++) {
            Friend friend = friendList.getList().get(i);
            list += "\n" + (i + 1) + ". " + friend.toString();
        }
        return list;
    }

    /**
     * Generate a message to inform user task is successfully added.
     * @param task
     * @return a String of the message.
     */
    public String addTask(Task task) {
        return "Got it. I've added this task: \n" + task.printTask();
    }

    public String addFriend(Friend friend) {
        return "Added a new friend: \n" + friend.toString() + "\nNow you have 1 friend in the list";
    }

    /**
     * Generate a message to inform user task is successfully deleted.
     * @param task
     * @return a String of the message.
     */
    public String deleteTask(Task task) {
        return "Noted. I've removed this task:\n" + task.printTask();
    }

    /**
     * Generate a message to inform user friend is successfully deleted.
     * @param friend
     * @return a String of the message.
     */
    public String deleteFriend(Friend friend) {
        return "Noted. I've unfriend " + friend.toString();
    }

    /**
     * Generate a message to inform user task is marked as completed.
     * @param task
     * @return a String of the message.
     */
    public String completedTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.printTask();
    }

    /**
     * Generate a message to bid farewell to user.
     * @return a String of the message.
     */
    public String exit() {
        this.scanner.close();
        return "Bye. Hope to see you again soon!";
    }
}
