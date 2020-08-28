package duke;

import java.util.ArrayList;

/**
 * Represents the text ui interface
 */
public class Ui {

    /**
     * Build chat line separator
     */
    public static void buildChatSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Show farewell message
     */
    public static void sayFarewell() {
        buildChatSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        buildChatSeparator();
    }

    /**
     * Show added task and size of list
     *
     * @param list The task list
     * @param curr The newly added task
     */
    public void describeTask(ArrayList<Task> list, Task curr) {
        buildChatSeparator();
        System.out.println(" Got it. I've added this task: ");
        System.out.println(" " + curr.toString() + curr.getDateDescription());
        System.out.println(" Now you have " + (list.size() == 1
                ? list.size() + " task"
                : list.size() + " tasks")
                + " in the list.");
        buildChatSeparator();
    }

    /**
     * Print all task inside the list
     *
     * @param list The task list
     */
    public void printList(ArrayList<Task> list) {
        buildChatSeparator();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(" " + i + ". "
                    + list.get(i - 1).toString()
                    + list.get(i - 1).getDateDescription());
        }
        buildChatSeparator();
    }

    /**
     * Show response when a task is deleted
     *
     * @param deleted The deleted task
     * @param list The task list
     */
    public static void printDeleted(Task deleted, ArrayList<Task> list) {
        buildChatSeparator();
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(" " + deleted);
        System.out.println(" Now you have " + (list.size() <= 1
                ? list.size() + " task"
                : list.size() + " tasks")
                + " in the list.");
        buildChatSeparator();
    }

    /**
     * Show response when a task is done
     *
     * @param list The task list
     * @param index The index of the element inside the list
     */
    public static void printDone(ArrayList<Task> list, int index) {
        buildChatSeparator();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + list.get(index));
        buildChatSeparator();
    }

    /**
     * Show introduction of the Duke chatbot
     */
    public void introduceDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(introduction);
    }
}
