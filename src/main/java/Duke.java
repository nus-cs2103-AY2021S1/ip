package main.java;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // attributes for task storage
        Task[] lib = new Task[100];
        int curr = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println( "---------------\n" + "Hello! I'm\n" + logo + "\n" +
                "---------------\n" + "What can I do for you?\n" + "---------------");

        while (sc.hasNextLine()) {
            String echo = sc.nextLine();
            String[] modEcho = echo.split(" ", 2);

            if (modEcho.length == 1) {
                if (echo.equals("bye")) {
                    System.out.println("---------------\n" + "Bye. Hope to see you again soon!\n"
                            + "---------------");
                    break;
                }

                if (echo.equals("list")) {
                    System.out.println("---------------\n" + "Here are the tasks in your list:");

                    for (int i = 1; i <= curr; i++) {
                        System.out.println(i + "." + lib[i - 1].toString());
                    }
                    System.out.println("---------------");
                    continue;
                }

            } else {
                String task = modEcho[0];

                if (task.equals("done")) {
                    int index = Integer.parseInt(modEcho[1]) - 1;
                    System.out.println("---------------\n" + "Nice! I've marked this task as done:");

                    lib[index].finishTask();
                    System.out.println(lib[index].toString());
                    System.out.println("---------------\n");
                    continue;
                }

                System.out.println("---------------\n" +
                        "Got it. I've added this task:");

                if (task.equals("todo")) {
                    ToDo todo = new ToDo(modEcho[1]);
                    lib[curr] = todo;
                    System.out.println(todo.toString());

                } else if (task.equals("deadline")) {
                    String[] processDeadline = modEcho[1].split("/");
                    String[] time = processDeadline[1].split(" ", 2);
                    Deadline deadline = new Deadline(processDeadline[0].trim(),
                            time[1].trim());

                    lib[curr] = deadline;
                    System.out.println(deadline.toString());

                } else if (task.equals("event")) {
                    String[] processEvent = modEcho[1].split("/");
                    String[] time = processEvent[1].split(" ", 2);
                    Event event = new Event(processEvent[0].trim(),
                            time[1].trim());

                    lib[curr] = event;
                    System.out.println(event.toString());
                }

                curr++;
                System.out.println("Now you have " + curr + " tasks in the list.\n" +
                        "---------------");
            }

        }

        sc.close();
    }
}
