package gel;

import java.util.List;

import gel.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public String loadingError() {
        String errorMsg = "    Lol... Did not manage to load storage data..."
                + " Creating new TaskList now...";
        return errorMsg;
    }

    public String showWelcomeMessage() {
        String welcomeMsg = "    Heyy I'm Gel...\n    What do you want from me?";
        return welcomeMsg;
    }

    public String farewellMessage() {
        String farewellMsg = "    Bye. Hope to see you again soon!";
        return farewellMsg;
    }

    public String addTaskToListMsg(Task task, int numberOfItems) {
        String addTaskMsg = "    Got it. I've added this task:\n    "
                + task + "\n    Now you have " + numberOfItems + " task(s) in the list.";
        return addTaskMsg;
    }

    public String taskRemoveMsg(Task task, int numberOfItemsLeft) {
        String removeTaskMsg = "    Noted. I've removed this task:\n    "
                + task + "\n    Now you have " + numberOfItemsLeft + " task(s) in the list.";
        return removeTaskMsg;
    }

    public String markTaskAsDoneMsg(Task task) {
        String markDoneMsg = "    Nice! I've marked this task as done:\n    " + task;
        return markDoneMsg;
    }

    public String showListOfTask(List<Task> taskList) {
        StringBuilder listOfTask = new StringBuilder("    Here are the task(s) in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            listOfTask.append("\n    ").append(i).append(".").append(task);
        }
        return listOfTask.toString();
    }

    public String showSearchResults(List<Task> taskList, String keyword) {
        int resultCount = 1;
        StringBuilder searchResults = new StringBuilder("    Here are the matching task(s) in your list:");
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                searchResults.append("\n    ").append(resultCount).append(".").append(task);
                resultCount += 1;
            }
        }
        return searchResults.toString();
    }
}
