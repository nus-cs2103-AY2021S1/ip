package gel;

import java.util.List;

import gel.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Returns the loading error message.
     *
     * @return Error message in String.
     */
    public String loadingError() {
        String errorMsg = "    Lol... Did not manage to load storage data..."
                + " Creating new TaskList now...";
        return errorMsg;
    }

    /**
     * Returns the welcome message.
     *
     * @return Welcome message in String.
     */
    public String showWelcomeMessage() {
        String welcomeMsg = "    Heyy I'm Gel...\n    What do you want from me?";
        return welcomeMsg;
    }

    /**
     * Returns the farewell message.
     *
     * @return farewell message in String.
     */
    public String farewellMessage() {
        String farewellMsg = "    Bye. Hope to see you again soon!";
        return farewellMsg;
    }

    /**
     * Shows that task was added to the task list.
     *
     * @param task Task added.
     * @param numberOfItems Total number of items in the taskList.
     * @return message in String.
     */
    public String addTaskToListMsg(Task task, int numberOfItems) {
        String addTaskMsg = "    Got it. I've added this task:\n    "
                + task + "\n    Now you have " + numberOfItems + " task(s) in the list.";
        return addTaskMsg;
    }

    /**
     * Shows that task was removed from the task list.
     *
     * @param tasks Task to be deleted.
     * @param numberOfItemsLeft Number of items left.
     * @return Message in String.
     */
    public String taskRemoveMsg(List<Task> tasks, int numberOfItemsLeft) {
        StringBuilder listOfRemovedTask = new StringBuilder();
        for (Task task:tasks) {
            listOfRemovedTask.append("    ").append(task).append("\n");
        }
        String removeTaskMsg = "    Noted. I've removed this task:\n"
                + listOfRemovedTask.toString() + "    Now you have " + numberOfItemsLeft + " task(s) in the list.";
        return removeTaskMsg;
    }

    /**
     * Shows that task was marked as done.
     *
     * @param tasks Tasks to be marked as done.
     * @return Message in String.
     */
    public String markTaskAsDoneMsg(List<Task> tasks) {
        StringBuilder listOfDoneTask = new StringBuilder();
        for (Task task:tasks) {
            listOfDoneTask.append("    ").append(task).append("\n");
        }
        String markDoneMsg = "    Nice! I've marked this task as done:\n" + listOfDoneTask.toString();
        return markDoneMsg;
    }

    /**
     * Shows the complete list of tasks.
     *
     * @param taskList Entire task list.
     * @return Message in String.
     */
    public String showListOfTask(List<Task> taskList) {
        StringBuilder listOfTask = new StringBuilder("    Here are the task(s) in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            listOfTask.append("\n    ").append(i).append(". ").append(task);
        }
        return listOfTask.toString();
    }

    /**
     * Shows search results.
     *
     * @param taskList Entire list of tasks.
     * @param keyword Keyword input by the user.
     * @return Message in String.
     */
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
