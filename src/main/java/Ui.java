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

    public static void printAdd(Pair<String, Integer> msg) {
        String toPrint = "     Got it. I've added this task:\n"
                + "       " + msg.getT()
                + "     Now you have " + msg.getU() + " tasks in the list.\n";
        printMessage(toPrint);
    }

    public static void printDelete(Pair<String, Integer> msg) {
        String toPrint = "     Noted. I've removed this task: \n"
                + "       " + msg.getT()
                + "     Now you have " + msg.getU() + " tasks in the list.\n";
        printMessage(toPrint);
    }
}
