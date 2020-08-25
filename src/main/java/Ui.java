public class Ui {

    public static String welcomeMessage() {
        return "____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "____________________________________________________________\n";
    }

    public static String endMessage() {
        return "____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
    }

    public static String printTaskList(TaskList list) {
        return "____________________________________________________________"
                + list.toString()
                + "____________________________________________________________";
    }

    public static String printTask(Task Task) {
        return "____________________________________________________________"
                + Task.toString()
                + "____________________________________________________________";
    }

    public static String unknownInputErrorMessage(Exception e) {
        return "DUKE DOES NOT UNDERSTAND YOU!!!@#%#$%^!@^%\n"
                + e 
                + "\nTRY AGAIN!!!";
    }

}
