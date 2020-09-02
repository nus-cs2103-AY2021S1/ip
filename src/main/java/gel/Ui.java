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

    public String markTaskAsDoneMsg(Task task) {
        String markDoneMsg = "\n    Nice! I've marked this task as done:\n    " + task + "\n";
        System.out.println(markDoneMsg);
        return markDoneMsg;
    }

    public String showListOfTask(List<Task> taskList) {
        StringBuilder listOfTask = new StringBuilder("\n    Here are the task(s) in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            listOfTask.append("\n    ").append(i).append(".").append(task);
        }
        listOfTask.append("\n");
        System.out.println(listOfTask);
        return listOfTask.toString();
    }

    public String showSearchResults(List<Task> taskList, String keyword) {
        int resultCount = 1;
        StringBuilder searchResults = new StringBuilder("\n    Here are the matching task(s) in your list:");
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                searchResults.append("\n    ").append(resultCount).append(".").append(task);
                resultCount += 1;
            }
        }
        searchResults.append("\n");
        System.out.println(searchResults);
        return searchResults.toString();
    }
}
