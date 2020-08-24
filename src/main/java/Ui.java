import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner user_input;

    public Ui() {
        this.user_input = new Scanner(System.in);
    }

    public void showGreeting() {
        String greeting = "Oh Golly! Who do we have here?\nThe name's Duke, how can I be of assistance?";
        System.out.println(greeting);
    }

    public void showDukeError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showTaskList(ArrayList<Task> taskList) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (i == taskList.size() - 1) {
                output = output + (i + 1) + "." + currentTask;
            } else {
                output = output + (i + 1) + "." + currentTask + "\n";
            }
        }
        String getListMsg = "Here are the tasks in your list:";
        String emptyListMsg = "Oh dear, it seems that your tasks list is empty!";
        if (taskList.size() < 1) {
            System.out.println(emptyListMsg);
        } else {
            System.out.println(getListMsg);
            System.out.println(output);
        }
    }

    public void showTotalTasks(int i) {
        System.out.println("Marvellous! Now you have " + i + " tasks in your list!");
    }

    public void showExit() {
        String parting = "Well, I'm utterly knackered! Cheerios!";
        System.out.println(parting);
    }

    public void showAddedToList(Task task) {
        String add_to_listMsg = "No worries, the following task has been added to your list:";
        System.out.println(add_to_listMsg);
        System.out.println("\t" + task);
    }

    public static void showUnknownError() {
        System.out.println("OH FIDDLESTICKS, WE SEEM TO HAVE HIT A BUMP ON THE ROAD HERE. " +
                "AN UNKNOWN ERROR HAS BEEN DETECTED.");

    }

    public String getUserInput() {
        return this.user_input.nextLine();
    }

    public void showMarkDone(Task task) {
        String markDoneMsg = "Splendid! I've marked the following task as done:";
        System.out.println(markDoneMsg);
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public void showDelete(Task task) {
        String deleteMsg = "No worries, the following task has been deleted from your list:";
        System.out.println(deleteMsg);
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }
}
