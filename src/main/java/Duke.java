import java.util.Scanner;

public class Duke {
    public static void printMessage(String msg) {
        System.out.println("    ____________________________________________________________");
        System.out.print(msg);
        System.out.println("    ____________________________________________________________");
    }

    public static void initialMessage() {
        String toPrint = "    Hello! I'm Hartina Menzel!\n" + "    What can I do for you?\n";
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task tasks = new Task();

        initialMessage();
        while(true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                exitMessage();
                break;
            } else if (echo.equals("list")) {
                printList(tasks.printTodoList());
            } else if (echo.matches("done\\s[0-9]+")) { // Checks if it matches done and an integer
                // Strip the done, leaving the integer
                int index = Integer.parseInt(echo.replaceAll("done ", ""));
                printDone(tasks.markAsDone(index));
            } else { // adds something
                printMessage(tasks.add(echo));
            }
        }
    }
}
