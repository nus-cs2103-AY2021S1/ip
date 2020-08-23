import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    
    private static StringBuilder printListItems(StringBuilder builder, ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            builder.append(String.format(" %s. %s\n", i + 1, list.get(i)));
        }
        return builder;
    }

    protected static void printList(ArrayList<Task> list) {
        StringBuilder builder = new StringBuilder(" Here are the tasks in your list:\n");
        printWithDashes(printListItems(builder, list).toString());
    }

    protected static void printFound(LocalDate localDate, ArrayList<Task> list) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = localDate.format(formatter);
        String intro = String.format(" Here are the tasks that I've found on %s:\n", formattedDate);
        StringBuilder builder = new StringBuilder(intro);
        printWithDashes(printListItems(builder, list).toString());
    }

    protected static void printMarkTaskAsDone(Task task) {
        printWithDashes(" Nice! I've marked this task as done:\n " + task);
    }

    protected static void printDeleteTask(Task task, int length) {
        String message = " Noted. I've removed this task:\n " + task;
        message += String.format("\n Now you have %s task%s in the list.", length, length > 1 ? "s" : "" );
        printWithDashes(message);
    }

    protected static void printException(DukeException ex) {
        printWithDashes(" ERROR: " + ex.getMessage());
    }
}
