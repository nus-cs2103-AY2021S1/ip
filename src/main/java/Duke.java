import java.rmi.server.ExportException;
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
                try {
                    if (input.contains("done")) {
                        String[] splitStr = input.split(" ");
                        if (splitStr.length == 1) {
                            throw new DukeException("☹ OOPS!!! I don't know which task to mark as done.");
                        }
                        int taskIndex = Integer.parseInt(splitStr[1]) - 1;
                        Task task = tasks[taskIndex];
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + task);
                    } else if (input.contains("todo")) {
                        if (input.length() <= 5) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(input.substring(5));
                        tasks[index] = todo;
                        index++;
                        System.out.println("Got it. I've added this task:\n" + todo + "\nNow you have "
                                + index + " tasks in the list.");
                    } else if (input.contains("deadline")) {
                        if (input.length() <= 9 || !input.contains("/by")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] splitStr = input.split("/by ");
                        String description = splitStr[0].substring(9).trim();
                        Deadline deadline = new Deadline(description, splitStr[1]);
                        tasks[index] = deadline;
                        index++;
                        System.out.println("Got it. I've added this task:\n" + deadline + "\nNow you have "
                                + index + " tasks in the list.");
                    } else if (input.contains("event")) {
                        if (input.length() <= 6 || !input.contains("/at")) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String[] splitStr = input.split("/at ");
                        String description = splitStr[0].substring(6).trim();
                        Event event = new Event(description, splitStr[1]);
                        tasks[index] = event;
                        index++;
                        System.out.println("Got it. I've added this task:\n" + event + "\nNow you have "
                                + index + " tasks in the list.");
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException ex) {
                    System.out.println(ex);
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
