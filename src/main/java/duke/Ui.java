package duke;

import java.util.ArrayList;

public class Ui {

    public static void buildChatSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void bye() {
        buildChatSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        buildChatSeparator();
    }

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

    public void printDeleted(Task deleted, ArrayList<Task> list) {
        buildChatSeparator();
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(" " + deleted);
        System.out.println(" Now you have " + (list.size() <= 1
                ? list.size() + " task"
                : list.size() + " tasks")
                + " in the list.");
        buildChatSeparator();
    }

    public void printDone(ArrayList<Task> list, int index) {
        buildChatSeparator();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + list.get(index));
        buildChatSeparator();
    }

    public void introduce() {
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
