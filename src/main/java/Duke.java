import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();
        String line = "---------------------------------------------------------";

        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println(line);

        String type = sc.next();
        String input = sc.nextLine().trim();

        while (!type.equals("bye")) {
            try {
                if (type.equals("list")) {
                    int listSize = list.size();
                    if (listSize == 0) {
                        throw new DukeException("OOPS!!! I'm sorry, your list is empty :<");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        System.out.println(list);
                    }
                } else if (type.equals("done")) {
                    try {
                        int index = Integer.parseInt(input) - 1;
                        Task task = list.markAsDone(index);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS!!! I'm sorry, which task number?");
                    }
                } else if (type.equals("delete")) {
                    try {
                        int index = Integer.parseInt(input) - 1;
                        Task task = list.deleteTask(index);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(task);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS!!! I'm sorry, which task number?");
                    }
                } else if (type.equals("date")) {
                    System.out.println(list.getTasksOn(input));
                } else {
                    Task task = list.addTask(type, input);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(line);
            type = sc.next();
            input = sc.nextLine().trim();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
