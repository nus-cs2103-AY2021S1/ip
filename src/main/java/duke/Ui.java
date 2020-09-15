package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Duke User Interface which deals with interactions with the user
 */
public class Ui {
    public Ui() { }

    /**
     * Processes DukeException errors and displays them to the user.
     *
     * @param e The DukeException thrown
     */
    public void showLoadingError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Simulates an exit event when the user inputs the bye command.
     *
     * @return Returns "Bye" if the user inputs the bye command.
     */
    public String goodbye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Displays the user's current task list.
     *
     * @param tasks The current TaskList of the User.
     */
    public String list(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list yet.";
        } else {
            String response = "Here are the tasks in your list:";
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                String taskString = "\n" + (i + 1) + ". " + tasks.getTasks().get(i).formattedDateString();
                response += taskString;
            }
            return response;
        }
    }

    public String showTags(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list yet.";
        } else {
            String response = "Here are the tasks in your list:";
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                String taskString = "\n" + (i + 1) + ". " + tasks.getTasks().get(i).formattedDateStringWithTags();
                response += taskString + "\n";
            }
            return response;
        }
    }

    /**
     * Notifies the user that the specified task has been marked done.
     *
     * @param t The specified task that was marked done.
     */
    public String done(Task t) {
        String response = "Nice! I've marked this task as done:";
        response += "\n" + t.formattedDateString();
        return response;
    }

    /**
     * Notifies the user that the specified task has been tagged.
     *
     * @param t The specified task that was marked done.
     */
    public String tag(Task t, Tag tag) {
        String response = "Nice! I've tagged this task with " + tag + ":";
        response += "\n" + t.formattedDateString();
        return response;
    }

    /**
     * Notifies the user that the specified task has been deleted.
     * Notifies the user how many tasks are left in the current TaskList.
     *
     * @param t The specified task that was deleted.
     * @param size The number of tasks in the TaskList.
     */
    public String delete(Task t, int size) {
        String response = "Noted. I've removed this task:";
        response += "\n" + t.formattedDateString();
        response += "\n" + "Now, you have " + size + " tasks in the list";
        return response;
    }

    /**
     * Notifies the user that the specified task has been added.
     * Notifies the user how many tasks are in the updated TaskList.
     *
     * @param t The specified task that was added.
     * @param size The number of tasks in the TaskList.
     */
    public String add(Task t, int size) {
        String response = "Got it. I've added this task:";
        response += "\n" + t.formattedDateString();
        response += "\n" + "Now, you have " + size + " tasks in the list";
        return response;
    }

    /**
     * Displays the tasks in the user's TaskList that match the input string.
     *
     * @param tasks The list of Tasks that contains the input string.
     */
    public String find(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "Could not find any tasks.";
        } else {
            String response = "Here are the matching tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                String matchingTask = "\n" + (i + 1) + ". " + tasks.get(i).formattedDateString();
                response += matchingTask;
            }
            return response;
        }
    }

    /**
     * Displays the tasks in the user's TaskList that match the input string.
     *
     * @param tasks The list of Tasks that contains the input string.
     */
    public String findTag(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "Could not find any tasks.";
        } else {
            String response = "Here are the matching tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                String matchingTask = "\n" + (i + 1) + ". " + tasks.get(i).formattedDateStringWithTags();
                response += matchingTask;
            }
            return response;
        }
    }

    /**
     * Initializes the Duke UI to receive user inputs.
     *
     * @param tasks The specified task that was marked done.
     */
    public void initializeDukeUI(TaskList tasks) {
        greeting();
        try {
            Scanner userInput = new Scanner(System.in);
            while (userInput.hasNext()) {
                String input = userInput.nextLine();
                String s = Parser.parse(input, tasks);
                if (s.equals("Bye")) {
                    break;
                }
            }
        } catch (DukeException e) {
            showLoadingError(e);
        }
    }

    public String removeTag(Task t, boolean isTagRemoved, String tagRemoved) {
        if (!isTagRemoved) {
            return "The specified task does not have the specified tag: " + tagRemoved;
        } else {
            String response = "Nice! I've removed the tag: " + tagRemoved;
            response += "\nfrom " + t.formattedDateString();
            return response;
        }
    }
}
