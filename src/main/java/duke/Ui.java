package duke;

public class Ui {
    public Ui() { }

    public static void printMessage(String msg) {
        System.out.println("    ____________________________________________________________");
        System.out.print(msg);
        System.out.println("    ____________________________________________________________");
    }

    public static void initialMessage() {
        String toPrint = "    Hello! I'm Duke the Bad Dragon.\n" + "    What can I do for you?\n";
        printMessage(toPrint);
    }

    public static void exitMessage() {
        printMessage("     Bye. Hope to see you again soon!\n");
    }

    public static void printDone(String msg) {
        String toPrint = "     Nice! I've marked this task as done:" + "\n" + msg;
        printMessage(toPrint);
    }

    public static void printList(String msg) {
        String toPrint = "     Here are the tasks in your list:\n" + msg;
        printMessage(toPrint);
    }

    public static void printAdd(String description, int numberOfTasks) {
        String toPrint = "     Got it. I've added this task:\n"
                + "       " + description
                + "     Now you have " + numberOfTasks + " tasks in the list.\n";
        printMessage(toPrint);
    }

    public static void printDelete(String description, int numberOfTasks) {
        String toPrint = "     Noted. I've removed this task: \n"
                + "       " + description
                + "     Now you have " + numberOfTasks + " tasks in the list.\n";
        printMessage(toPrint);
    }
}
