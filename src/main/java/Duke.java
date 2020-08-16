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
                String[] parsed = next.split(" +");
                if (parsed[0].equals("done")) {
                    manager.markTaskAsDone(Integer.parseInt(parsed[1]));
                } else {
                    manager.addTask(next); // default behaviour
                }
            }
            next = sc.nextLine();
        }
        close();
}

    public static void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}
