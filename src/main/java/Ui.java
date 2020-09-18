import java.util.ArrayList;

/**
 * Encapsulates the UI object, in charge of interacting with the user.
 */
public class Ui {

    private static final String LOGO =
            " \n____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER =
            "______________________________\n";

    String showDivider() {
        return DIVIDER;
    }

    /**
     * Wraps the text provided with the divider.
     * 
     * @param response duke's response.
     * @return duke's response with dividers.
     */
    String formatResponse(String response) {
        return showDivider() + response + "\n" + showDivider();
    }

    String showError(DukeException e) {
        return "     " + e.getMessage();
    }

    String showWelcome() {
        return "Hello from\n" + LOGO
                + showDivider()
                + "Hello! I'm Duke!\n" + "What can I do for you?\n"
                + showDivider();
    }

    String showGoodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    String showTaskList(String taskListString) {
        return "Here are the tasks in your list:\n" +  taskListString;
    }

    private String buildTaskString(ArrayList<Task> tasks) {
        StringBuilder taskString = new StringBuilder();
        for (Task task : tasks) {
            taskString.append(task).append("\n   ");
        }
        return taskString.toString();
    }
    
    String showDoneTasks(ArrayList<Task> tasks) {
        String doneTasks = buildTaskString(tasks);
        return "Nice! I've marked these tasks as done:\n       " + doneTasks;
    }

    String showDeleteTasks(ArrayList<Task> tasks, int listLength) {
        String deleteTasks = buildTaskString(tasks);
        return "Noted. I've removed these tasks:\n       " + deleteTasks 
                + "\nNow you have " + listLength + " tasks in the list.";
    }

    String showAddedTask(Task task, int listLength) {
        return "Got it. I've added this task:\n       " + task
                + "\nNow you have " + listLength + " tasks in the list.";
    }
    
    String showMatchingTask(String taskList) {
        return "Here are the matching tasks in your list:\n" + taskList;
    }
}
