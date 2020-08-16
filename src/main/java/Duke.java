import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        TaskManager manager = new TaskManager();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                manager.printList();
            } else {
                String[] actionExtracted = extractAction(next);
                String status = actionExtracted[0];
                String body = actionExtracted[1];
                switch (status) {
                    case "done":
                        manager.markTaskAsDone(Integer.parseInt(body));
                        break;
                    case "todo":
                        manager.addTask(body, status);
                        break;
                    default:
                        String[] timeExtracted = extractTime(body);
                        String content = timeExtracted[0];
                        String time = timeExtracted[1];
                        manager.addTask(content, status, time);
                        break;
                }
            }
            next = sc.nextLine();
        }
        close();
    }

    private static String[] extractAction(String command) {
        return command.split(" ", 2);
    }

    private static String[] extractTime(String command) {
        return command.split(" /by | /at ", 2);
    }

    private static void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}
