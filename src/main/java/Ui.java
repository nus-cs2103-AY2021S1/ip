import java.util.List;

public class Ui {

    public void showLoadingError() {
        System.out.println("    Lol... did not manage to load storage data...");
    }

    public void showWelcomeMessage() {
        System.out.println("    Hello! I'm Gel\n    What do you want from me?\n");
    }

    public void farewellMessage() {
        System.out.println("\n    Bye. Hope to see you again soon!\n");
    }

    public void addTaskToListMsg(Task task, int numberOfItems) {
        System.out.println("\n    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + numberOfItems + " task(s) in the list.\n");
    }

    public void taskRemoveMsg(Task task, int numberOfItemsLeft) {
        System.out.println("\n    Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + numberOfItemsLeft + " task(s) in the list.\n");
    }

    public void markTaskAsDoneMsg(Task task) {
        System.out.println("\n    Nice! I've marked this task as done:");
        System.out.println("    " + task);
        System.out.println();
    }

    public void showListOfTask(List<Task> taskList) {
        System.out.println("\n    Here are the task(s) in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            System.out.println("    " + i + "." + task);
        }
        System.out.println();
    }
}
