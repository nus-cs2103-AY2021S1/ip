import java.util.ArrayList;

public class Ui {
    private static void print(String s) {
        System.out.println("\t____________________________________________________________\n" +
                s +
                "\t____________________________________________________________\n");
    }

    static void welcomeMessage() {
        print("\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n");
    }

    public static void doneMessage(Task task) {
        print("\tNice! I've marked this task as done:\n" +
                "\t" + task + "\n");
    }

    public static void deleteMessage(Task task, int total) {
        print("\tNoted. I've removed this task:\n" +
                "\t" + task + "\n" +
                "\tNow you have " + total + " tasks in the list.\n");
    }

    public static void list(TaskList tasks) {
        ArrayList<Task> list = tasks.taskList;
        String temp = "";
        for(int i = 0; i < list.size(); i++) {
            temp += "\t" + (i+1) + ". " + list.get(i) + "\n";
        }
        print("\tHere are the tasks in your list:\n" + temp);
    }

    public static void addTaskMessage(Task task, int total) {
        print("\tGot it. I've added this task: \n" +
                "\t" + task + "\n" +
                "\tNow you have " + total + " tasks in the list.\n");
    }

    public static void ignoreMessage() {
        print("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    public static void emptyTodoMessage() {
        print("\t☹ OOPS!!! The description of a todo cannot be empty.\n");
    }

    public static void dateFormatReminder() {
        System.out.println("Try again! Date format should be yyyy-mm-dd.");
    }

    public static void exitMessage() {
        print("\tBye. Hope to see you again soon!\n");
    }
}

