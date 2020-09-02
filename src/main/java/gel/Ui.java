package gel;

import java.util.List;

import gel.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public String showLoadingError() {
        String errorMsg = "    Lol... Did not manage to load storage data..."
                + " Creating new TaskList now...";
        System.out.println(errorMsg);
        return errorMsg;
    }

    public String showWelcomeMessage() {
        String welcomeMsg = "    Hello! I'm gel.Gel\n    What do you want from me?\n";
        System.out.println(welcomeMsg);
        return welcomeMsg;
    }

    public String farewellMessage() {
        String farewellMsg = "\n    Bye. Hope to see you again soon!\n";
        System.out.println(farewellMsg);
        return farewellMsg;
    }

    public String addTaskToListMsg(Task task, int numberOfItems) {
        String addTaskMsg = "\n    Got it. I've added this task:\n    "
                + task + "\n    Now you have " + numberOfItems + " task(s) in the list.\n";
        System.out.println(addTaskMsg);
        return addTaskMsg;
    }

    public String taskRemoveMsg(Task task, int numberOfItemsLeft) {
        String removeTaskMsg = "\n    Noted. I've removed this task:\n    "
                + task + "\n    Now you have " + numberOfItemsLeft + " task(s) in the list.\n";
        System.out.println(removeTaskMsg);
        return removeTaskMsg;
    }

    public void markTaskAsDoneMsg(Task task) {

        System.out.println("\n    Nice! I've marked this task as done:");
        System.out.println("    " + task);
        System.out.println();
        return
    }

    public void showListOfTask(List<Task> taskList) {
        System.out.println("\n    Here are the task(s) in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            System.out.println("    " + i + "." + task);
        }
        System.out.println();
    }

    public void showSearchResults(List<Task> taskList, String keyword) {
        int resultCount = 1;
        System.out.println("\n    Here are the matching task(s) in your list:");
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                System.out.println("    " + resultCount + "." + task);
                resultCount += 1;
            }
        }
        System.out.println();
    }
}
