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
    public String goodbye(boolean isRunningOnGui) {
        if (isRunningOnGui) {
            return "Bye! Hope to see you again soon!";
        } else {
            System.out.println("Bye! Hope to see you again soon!");
            return "Bye";
        }
    }

    /**
     * Displays the user's current task list.
     *
     * @param tasks The current TaskList of the User.
     */
    public String list(TaskList tasks, boolean isRunningOnGui) {
        if (isRunningOnGui) {
            if (tasks.size() == 0) {
                String noTasks = "There are no tasks in your list yet.";
                return noTasks;
            } else {
                String response = "Here are the tasks in your list:";
                for (int i = 0; i < tasks.getTasks().size(); i++) {
                    response += "\n" + Integer.toString((i + 1)) + ". " + tasks.getTasks().get(i).recordString();
                }
                return response;
            }
        } else {
            if (tasks.size() == 0) {
                System.out.println("There are no tasks in your list yet.");
                return "";
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.getTasks().size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.getTasks().get(i).recordString());
                }
                return "";
            }
        }
    }

    /**
     * Notifies the user that the specified task has been marked done.
     *
     * @param t The specified task that was marked done.
     */
    public String done(Task t, boolean isRunningOnGui) {
        if (isRunningOnGui) {
            String response = "Nice! I've marked this task as done:";
            response += "\n" + t.recordString();
            return response;
        } else {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t.recordString());
            return "";
        }
    }

    /**
     * Notifies the user that the specified task has been deleted.
     * Notifies the user how many tasks are left in the current TaskList.
     *
     * @param t The specified task that was deleted.
     * @param size The number of tasks in the TaskList.
     */
    public String delete(Task t, int size, boolean isRunningOnGui) {
        if (isRunningOnGui) {
            String response = "Noted. I've removed this task:";
            response += "\n" + t.recordString();
            response += "\n" + "Now, you have " + Integer.toString(size) + " tasks in the list";
            return response;
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(t.recordString());
            System.out.println("Now, you have " + size + " tasks in the list");
            return "";
        }
    }

    /**
     * Notifies the user that the specified task has been added.
     * Notifies the user how many tasks are in the updated TaskList.
     *
     * @param t The specified task that was added.
     * @param size The number of tasks in the TaskList.
     */
    public String add(Task t, int size, boolean isRunningOnGui) {
        if (isRunningOnGui) {
            String response = "Got it. I've added this task:";
            response += "\n" + t.recordString();
            response += "\n" + "Now, you have " + Integer.toString(size) + " tasks in the list";
            return response;
        } else {
            System.out.println("Got it. I've added this task:");
            System.out.println(t.recordString());
            System.out.println("Now, you have " + size + " tasks in the list");
            return "";
        }
    }

    /**
     * Displays the tasks in the user's TaskList that match the input string.
     *
     * @param tasks The list of Tasks that contains the input string.
     */
    public String find(ArrayList<Task> tasks, boolean isRunningOnGui) {
        if (isRunningOnGui) {
            if (tasks.size() == 0) {
                return "Could not find any tasks.";
            } else {
                String response = "Here are the matching tasks in your list:";
                for (int i = 0; i < tasks.size(); i++) {
                    response += Integer.toString(i + 1) + ". " + tasks.get(i).recordString();
                }
                return response;
            }
        } else {
            if (tasks.size() == 0) {
                System.out.println("Could not find any tasks.");
            } else {
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).recordString());
                }
            }
            return "";
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
                String s = Parser.parse(input, tasks, false);
                if (s.equals("Bye")) {
                    break;
                }
            }
        } catch (DukeException e) {
            showLoadingError(e);
        }
    }
}
