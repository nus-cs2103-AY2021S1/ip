package ui;

import java.util.ArrayList;

import duke.Task;

/**
 * Represents the text ui interface
 */
public class Ui {

    /**
     * Build chat line separator
     */
    public static String buildChatSeparator() {
        return "____________________________________________________________";
    }

    /**
     * Show farewell message
     */
    public static String sayFarewell() {
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Show added task and size of list
     *
     * @param list The task list
     * @param curr The newly added task
     */
    public String describeTask(ArrayList<Task> list, Task curr) {
        return " Got it. I've added this task: \n"
                + " " + curr.toString() + curr.getDateDescription()
                + "\n Now you have " + (list.size() == 1
                ? list.size() + " task"
                : list.size() + " tasks")
                + " in the list.";
    }

    /**
     * Print all task inside the list
     *
     * @param list The task list
     */
    public String printList(ArrayList<Task> list) {
        StringBuilder output = new StringBuilder();
        output.append(" Here are the tasks in your list: \n");
        for (int i = 1; i <= list.size(); i++) {
            output.append(" " + i + ". "
                    + list.get(i - 1).toString()
                    + list.get(i - 1).getDateDescription()
                    + '\n');
        }
        return output.toString();
    }

    /**
     * Show response when a task is deleted
     *
     * @param deleted The deleted task
     * @param list The task list
     */
    public static String printDeleted(Task deleted, ArrayList<Task> list) {
        return " Noted. I've removed this task:  \n"
                + deleted
                + "\n Now you have " + (list.size() <= 1
                    ? list.size() + " task"
                    : list.size() + " tasks")
                + " in the list.";
    }

    /**
     * Show response when a task is done
     *
     * @param list The task list
     * @param index The index of the element inside the list
     */
    public static String printDone(ArrayList<Task> list, int index) {
        return " Nice! I've marked this task as done: \n"
                + " " + list.get(index);
    }

    /**
     * Show introduction of the Duke chatbot
     */
    public String introduceDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        StringBuilder output = new StringBuilder("Hello from\n" + logo);
        String introduction = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        output.append(introduction);
        return output.toString();
    }
}
