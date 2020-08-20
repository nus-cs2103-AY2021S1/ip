import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task tasks = new Task();
        Parser parser = new Parser();

        // TODO: 20/8/20 Improve runtime: keep an internal counter of task (for done.*)
        initialMessage();
        while(true) {
            try {
                String echo = sc.nextLine();

                // Command Handling
                // Exit
                if (echo.equals("bye")) {
                    exitMessage();
                    break;
                }

                // Querying items
                else if (echo.matches("list\\s*")) {
                    printList(tasks.printTodoList());
                }

                // Checks if it matches done and an integer
                else if (echo.matches("done.*")) {
                    // Strip the done, leaving the integer
                    // TODO: 20/8/20 Cleanup
                    int index = 0;
                    index = parser.parseDone(echo, tasks.length());
                    printDone(tasks.markAsDone(index));
                }

                // Add items
                else {
                    Pair<TaskType, ArrayList<String>> res = parser.parseAdd(echo);
                    ArrayList<String> description = res.getU();
                    TaskType type = res.getT();
                    switch (type) {
                        case TODO:
                            printAdd(tasks.add(new ToDo(description.get(0))));
                            break;
                        case DEADLINE:
                            printAdd(tasks.add(new Deadline(description.get(0), description.get(1))));
                            break;
                        case EVENT:
                            printAdd(tasks.add(new Event(description.get(0), description.get(1))));
                            break;
                        case NONE:
                            break;
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
