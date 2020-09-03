package duke;

public class Ui {
    private static String printMessage(String msg) {
        return msg;
    }

    public static String initialMessage() {
        String toPrint = "Hello! I'm Duke the Bad Dragon.\n" + "What can I do for you?\n";
        return printMessage(toPrint);
    }

    public static String exitMessage() {
        return printMessage("Bye. Hope to see you again soon!\n");
    }

    public static String printDone(String msg) {
        String toPrint = "Nice! I've marked this task as done:" + "\n" + msg;
        return printMessage(toPrint);
    }

    public static String printList(String msg) {
        String toPrint = "Here are the tasks in your list:\n" + msg;
        return printMessage(toPrint);
    }

    public static String printAdd(String description, int numberOfTasks) {
        String toPrint = "Got it. I've added this task:\n"
                + description
                + "Now you have " + numberOfTasks + " tasks in the list.\n";
        return printMessage(toPrint);
    }

    public static String printDelete(String description, int numberOfTasks) {
        String toPrint = "Noted. I've removed this task: \n"
                + description
                + "Now you have " + numberOfTasks + " tasks in the list.\n";
        return printMessage(toPrint);
    }
}
