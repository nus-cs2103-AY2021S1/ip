import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> entryList = new ArrayList<Task>();

    static String line = "    ____________________________________________________________\n";

    public static void printList() {
        int counter = 0;

        System.out.printf(line);

        for (Task task: entryList) {
            counter++;
            System.out.printf("     %d.%s%n", counter, task);
        }

        System.out.println(line);
    }

    public static void finishTask(Task task) {
        task.complete();
        String completion = "     Nice! I've marked this task as done:\n" +
                            String.format("       %s%n", task);
        System.out.printf(line + completion + line);
    }

    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         System.out.println("Hello from\n" + logo);
         **/
        String welcome = line + "     Hello! I'm Cartona (no relation to Cortana).\n" +
                         "     What can I do for you?\n" + line;

        System.out.println(welcome);

        String nextInput = "";
        while (true) {
            nextInput = sc.nextLine();

            if (nextInput.equals("bye")) {
                break;
            } else if (nextInput.equals("list")) {
                printList();
                continue;
            } else if (nextInput.substring(0, 4).equals("done")) {
                String[] doneArr = nextInput.split(" ");
                int taskNum = Integer.parseInt(doneArr[1]);
                finishTask(entryList.get(taskNum - 1));
                continue;
            }

            String echo = line + "     added: " + nextInput + "\n" + line;
            System.out.println(echo);
            entryList.add(new Task(nextInput));
        }

        String goodbye = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(goodbye);
    }
}