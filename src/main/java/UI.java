import java.util.ArrayList;

/**
 * Class that generates all the messages for display on the GUI.
 */
public class UI {

    /**
     * Prints the error message in the terminal.
     *
     * @param errorMessage Is the error message to be displayed.
     */
    public void printFormattedMessage(String errorMessage) {
        System.out.println(errorMessage);

    }

    /**
     * generates the bye message to GUI.
     *
     * @return String bye message.
     */
    public String printByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * generates the added task message to GUI.
     *
     * @param task      the task to be printed.
     * @param sizeStore index of the task.
     * @return String added task message.
     */
    public String printTaskAdd(Task task, int sizeStore) {
        return "Got it. I've added this task: \n" + task.toString() + "\n"
                + "Now you have " + String.valueOf(sizeStore) + " tasks in the list.\n";
    }

    /**
     * generates the deleted task message to GUI.
     *
     * @param task      the task to be printed.
     * @param sizeStore index of the task.
     * @return String added task message.
     */
    public String printDeleteMessage(Task task, int sizeStore) {
        return "Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + String.valueOf(sizeStore) + " tasks in the list.\n";
    }

    /**
     * generates the list of tasks message to GUI.
     *
     * @param tasks the list of tasks to be printed.
     * @return String list of tasks message.
     */
    public String printListOfTasks(ArrayList<Task> tasks) {
        String message = "Here are the tasks in your list:\n";
        int sizeStore = tasks.size();
        for (int i = 1; i < sizeStore + 1; i++) {
            message += String.valueOf(i) + "." + tasks.get(i - 1).toString() + "\n";
        }
        return message;
    }

    /**
     * generates the mark task done message to GUI.
     *
     * @param task the list of tasks to be printed.
     * @return String  task description message.
     */
    public static String printMarkAsDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";

    }

    /**
     * generates the tasks with the keyword message to GUI.
     *
     * @param substring the keyword that th user wnats to find.
     * @param tasks     list of tasks.
     * @return String list of tasks with keyword message.
     * @throws DukeNoMatchesExcpetion If there no matches for the keyword.
     */
    public String printKeywordTasks(String substring, ArrayList<Task> tasks) throws DukeNoMatchesExcpetion {
        String message = "Here are the matching tasks in your list:\n";
        try {
            ArrayList<Task> keywordTasks = new ArrayList<>();

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).containsKeyword(substring)) {
                    keywordTasks.add(tasks.get(i));
                }
            }
            if (keywordTasks.size() == 0) {
                throw new DukeNoMatchesExcpetion("");
            }
            int sizeStore = keywordTasks.size();
            for (int i = 1; i < sizeStore + 1; i++) {
                message += String.valueOf(i) + "." + keywordTasks.get(i - 1).toString() + "\n";
            }
        } catch (DukeNoMatchesExcpetion e) {
            message = e.getMessage();
        }
        return message;
    }

    /**
     * generates the task with tag message.
     *
     * @param input input of the user
     * @param tasks list of tasks
     * @return String task with tag message.
     */
    public String printTag(String input, ArrayList<Task> tasks) {
        String message = "Nice! I've tagged this task with: ";
        try {
            String[] commandSubstring = input.split(" ");
            int index = Integer.parseInt(commandSubstring[1]);
            if (index < 1 || index > tasks.size()) {
                throw new DukeInvalidTagNumException(input);
            } else {
                tasks.get(index - 1).makeTag(commandSubstring[2]);
                message += "#" + commandSubstring[2];
            }
            message += "\n" + tasks.get(index - 1).toString();
        } catch (DukeInvalidTagNumException e) {
            message = e.getMessage();
        }
        return message;

    }

    /**
     * generates the tasks with the tags message to GUI.
     *
     * @param substring the tags that the user wants to find.
     * @param tasks     list of tasks.
     * @return String list of tasks with tags message.
     */
    public String printFoundTagsTasks(String substring, ArrayList<Task> tasks) {
        String message = "Here are the matching tasks in your list with the tag:\n";
        try {
            ArrayList<Task> keywordTasks = new ArrayList<>();

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getTagDisplay().contains(substring)) {
                    keywordTasks.add(tasks.get(i));
                }
            }
            if (keywordTasks.size() == 0) {
                throw new DukeNoMatchesTagExcpetion("");
            }
            int sizeStore = keywordTasks.size();
            for (int i = 1; i < sizeStore + 1; i++) {
                message += String.valueOf(i) + "." + keywordTasks.get(i - 1).toString() + "\n";
            }
        } catch (DukeNoMatchesTagExcpetion e) {
            message = e.getMessage();
        }
        return message;
    }
}
