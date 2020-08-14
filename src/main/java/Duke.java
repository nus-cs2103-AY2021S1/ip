import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static int index = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    Task task = tasks[i];
                    System.out.println((i + 1) + "." + task);
                }
            } else {
//                String[] splitStr = input.split(" ");
                if (input.contains("done")) {
                    String[] splitStr = input.split(" ");
                    int taskIndex = Integer.parseInt(splitStr[1]) - 1;
                    Task task = tasks[taskIndex];
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + task);

                } else if (input.contains("todo")) {
                    Todo todo = new Todo(input.substring(5));
                    tasks[index] = todo;
                    index++;
                    System.out.println("Got it. I've added this task:\n" + todo + "\nNow you have "
                            + index + " tasks in the list.");
                } else if (input.contains("deadline")) {
                    String[] splitStr = input.split("/by ");
                    String description = splitStr[0].substring(9).trim();
                    Deadline deadline = new Deadline(description, splitStr[1]);
                    tasks[index] = deadline;
                    index++;
                    System.out.println("Got it. I've added this task:\n" + deadline + "\nNow you have "
                            + index + " tasks in the list.");
                } else if (input.contains("event")) {
                    String[] splitStr = input.split("/at ");
                    String description = splitStr[0].substring(6).trim();
                    Event event = new Event(description, splitStr[1]);
                    tasks[index] = event;
                    index++;
                    System.out.println("Got it. I've added this task:\n" + event + "\nNow you have "
                            + index + " tasks in the list.");
                } else {
                    Task task = new Task(input);
                    tasks[index] = task;
                    index++;
                    System.out.println("added: " + task);
                }
            }
        }
    }
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}
