import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");

        String type = sc.next();
        String input = sc.nextLine().trim();

        while (!type.equals("bye")) {
            if (type.equals("list")) {
                int listSize = list.size();
                if (listSize == 0) {
                    System.out.println("Your list is empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listSize; i++) {
                        int taskNum = i + 1;
                        System.out.print(taskNum + ".");
                        System.out.println(list.get(i));
                    }
                }
            } else if (type.equals("done")) {
                int index = Integer.parseInt(input) - 1;
                if (index > list.size() - 1) {
                    System.out.println("Task number out of range.");
                } else {
                    Task task = list.get(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.markAsDone());
                }
            } else {
                Task task;
                if (type.equals("todo")) {
                    task = new Todo(input);
                } else if (type.equals("deadline")) {
                    String[] arr = input.split("/by");
                    task = new Deadline(arr[0].trim(), arr[1].trim());
                } else { // event type
                    String[] arr = input.split("/at");
                    task = new Event(arr[0].trim(), arr[1].trim());
                }
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            type = sc.next();
            input = sc.nextLine().trim();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
