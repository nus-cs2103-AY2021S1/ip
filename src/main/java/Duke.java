package main.java;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // attributes for task storage
        Task[] lib = new Task[100];
        int curr = 0;

        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | | | | | | | |/ / _ \\\n"
                + "  | |_| | |_| |   <  __/\n"
                + "  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println( "---------------\n" + "  Hello from\n" + logo + "\n" +
                "---------------\n" + "  What can I do for you today?\n" + "---------------\n");

        while (sc.hasNext()) {
            String echo = sc.next();

            if (echo.equals("bye")) {
                System.out.println("---------------\n" + "Hope to see you again!\n"
                        + "---------------\n");
                break;
            }

            if (echo.equals("list")) {
                System.out.println("---------------\n" + "Here are the tasks in your list:\n");

                for (int i = 1; i <= curr; i++) {
                    Task task = lib[i - 1];
                    System.out.println(i + ".[" + task.getStatusIcon() + "] " +
                            task.getDescription() +"\n");
                }
                System.out.println("---------------\n");
                continue;
            }

            if (echo.equals("done")) {
                int refNum = sc.nextInt();
                lib[refNum - 1].finishTask();
                System.out.println("---------------\n" + "The following task is now marked as complete!\n" +
                        "  [" + lib[refNum - 1].getStatusIcon() + "] " + lib[refNum - 1].getDescription());
                continue;
            }

            System.out.println("---------------\n" + "Storing item " + "(" + curr + "/100)"+
                    " : " + echo + "\n" + "---------------\n");
            lib[curr] = new Task(echo);
            curr++;
        }

        sc.close();
    }
}
