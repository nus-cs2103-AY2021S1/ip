import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "____________________________________________________________\n" +
                "  Hello! I'm Duke\n" +
                "  What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(message);

        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine();
        String message1;

        List<Task> lists = new ArrayList<Task>();

        while (!string1.equals("bye")) {
            try {
                if (string1.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < lists.size(); i++) {
                        Task task = lists.get(i);
                        System.out.println((i + 1) + "." + task.toString());
                    }
                    System.out.println("____________________________________________________________\n");
                } else if (string1.contains("done ")) {
                    int order = parseInt(string1.substring(string1.length() - 1));
                    Task task = lists.get(order - 1);
                    task.markAsDone();
                    System.out.println("____________________________________________________________\n" +
                            "  Nice! I've marked this task as done:");
                    System.out.println("    [" + task.getStatusIcon() + "] " + task.getDescription());
                    System.out.println("____________________________________________________________\n");
                } else if (string1.contains("delete")) {
                    int order = parseInt(string1.substring(string1.length() - 1));
                    Task task = lists.get(order - 1);
                    lists.remove(order - 1);
                    System.out.println("____________________________________________________________\n" +
                            "Noted. I've removed this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + lists.size() + " tasks in the list.\n" +
                            "____________________________________________________________");
                } else {
                    String type;
                    if (string1.contains(" ")) {
                        type = string1.substring(0, string1.indexOf(' '));
                    } else {
                        String str = "";
                        switch (string1) {
                            case "todo":
                                str = "☹ OOPS!!! The description of a todo cannot be empty.";
                                break;
                            case "deadline":
                                str = "☹ OOPS!!! The description of a deadline cannot be empty.";
                                break;
                            case "event":
                                str = "☹ OOPS!!! The description of an event cannot be empty.";
                                break;
                            default:
                                str = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                        }
                        throw new DukeException(str);
                    }

                    if (type.equals("deadline")) {
                        String description = string1.substring(string1.indexOf(' ') + 1, string1.indexOf('/') - 1);
                        String by = string1.substring(string1.indexOf("/by") + 4);
                        lists.add(new Deadline(description, by));
                    } else if (type.equals("event")) {
                        String time = string1.substring(string1.indexOf("/at") + 4);
                        String description = string1.substring(string1.indexOf(' ') + 1, string1.indexOf('/') - 1);
                        lists.add(new Event(description, time));
                    } else if (type.equals("todo")) {
                        String description = string1.substring(string1.indexOf(' ') + 1);
                        lists.add(new ToDo(description));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    message1 = "____________________________________________________________\n" +
                            "Got it. I've added this task:\n  "
                            + lists.get(lists.size() - 1).toString() + "\n" +
                            "Now you have " + lists.size() + " tasks in the list.\n" +
                            "____________________________________________________________\n";
                    System.out.println(message1);
                }
            } catch (DukeException e) {
                System.out.println("____________________________________________________________\n");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________\n");
            }

            string1 = scanner.nextLine();
        }

        message1 = "____________________________________________________________\n" +
                "  Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(message1);
    }
}
