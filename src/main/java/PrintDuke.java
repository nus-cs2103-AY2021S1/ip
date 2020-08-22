import java.util.ArrayList;

public class PrintDuke {
    private static void printDashes() {
        int length = 60;
        System.out.println("_".repeat(length));
    }

    protected static void printWithDashes(String str) {
        printDashes();
        System.out.println(str);
        printDashes();
    }

    protected static void printLogo() {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = logo + " Hello! I'm Duke\n" + " What can I do for you?";
        printWithDashes(greeting);
    }

    protected static void printExitMessage() {
        String bye = "Bye. Hope to see you again soon!";
        printWithDashes(" " + bye);
    }

    protected static void printAddTask(Task task, int length) {
        String message = " Got it. I've added this task:\n";
        message += " " + task;
        message += String.format("\n Now you have %s task%s in the list.", length, length > 1 ? "s" : "" );
        printWithDashes(message);
    }

    protected static void printList(ArrayList<Task> list) {
        StringBuilder listStr = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            listStr.append(String.format(" %s. %s\n", i + 1, list.get(i)));
        }
        printWithDashes(listStr.toString());
    }

    protected static void printMarkTaskAsDone(Task task) {
        printWithDashes(" Nice! I've marked this task as done:\n " + task);
    }

    protected static void printDeleteTask(Task task, int length) {
        String message = " Noted. I've removed this task:\n " + task;
        message += String.format("\n Now you have %s task%s in the list.", length, length > 1 ? "s" : "" );
        printWithDashes(message);
    }

    protected static void printDukeException(DukeException ex) {
        printWithDashes(" ERROR: " + ex.getMessage());
    }
}
