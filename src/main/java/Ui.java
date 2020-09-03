import java.util.Scanner;

public class Ui {

    private static final String LOGO =
            " \n____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER =
            "    ____________________________________________________________\n";

//    private final Scanner sc;
//
//    Ui() {
//        sc = new Scanner(System.in);
//    }
//
//    String readCommand() {
//        return sc.nextLine();
//    }

    String showDivider() {
        return DIVIDER;
    }

    String showError(DukeException e) {
        return "     " + e.getMessage();
    }

    String showWelcome() {
        return "Hello from\n" + LOGO
                + showDivider()
                + "     Hello! I'm Duke!\n" + "     What can I do for you?\n"
                + showDivider();
    }

    String showGoodbye() {
        return "     Bye. Hope to see you again soon!\n";
    }

    String showTaskList(String taskListString) {
        return "     Here are the tasks in your list:\n" +  taskListString;
    }

    String showDoneTask(Task task) {
        return "     Nice! I've marked this task as done:\n       " + task;
    }

    String showDeleteTask(Task task, int listLength) {
        return "     Noted. I've removed this task:\n       " + task 
                + "\n     Now you have " + listLength + " tasks in the list.";
    }

    String showAddedTask(Task task, int listLength) {
        return "     Got it. I've added this task:\n       " + task +
                "\n     Now you have " + listLength + " tasks in the list.";
    }
    
    String showMatchingTask(String taskList) {
        return "     Here are the matching tasks in your list:\n" + taskList;
    }
}
