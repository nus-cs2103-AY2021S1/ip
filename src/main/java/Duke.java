import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("    ___________________________________________________________");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    ___________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("    ___________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ___________________________________________________________");
                return;
            }
            if (input.equals("list")) {
                System.out.println("    ___________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println("    " + (i + 1) + "." + task);
                }
                System.out.println("    ___________________________________________________________");
            } else {
                try {
                    if (input.contains("done")) {
                        String[] splitStr = input.split(" ");
                        if (splitStr.length == 1) {
                            throw new DukeException("☹ OOPS!!! I don't know which task to mark as done.");
                        }
                        int taskIndex = Integer.parseInt(splitStr[1]) - 1;
                        Task task = tasks.get(taskIndex);
                        task.markAsDone();
                        System.out.println("    ___________________________________________________________");
                        System.out.println("    Nice! I've marked this task as done:\n      " + task);
                        System.out.println("    ___________________________________________________________");
                    } else if (input.contains("todo")) {
                        if (input.length() <= 5) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(input.substring(5));
                        tasks.add(todo);
                        total++;
                        System.out.println("    ___________________________________________________________");
                        System.out.println("    Got it. I've added this task:\n      " + todo + "\n    Now you have "
                                + total + " tasks in the list.");
                        System.out.println("    ___________________________________________________________");
                    } else if (input.contains("deadline")) {
                        if (input.length() <= 9 || !input.contains("/by")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] splitStr = input.split("/by ");
                        String description = splitStr[0].substring(9).trim();
                        Deadline deadline = new Deadline(description, splitStr[1]);
                        tasks.add(deadline);
                        total++;
                        System.out.println("    ___________________________________________________________");
                        System.out.println("    Got it. I've added this task:\n      " + deadline + "\n    Now you have "
                                + total + " tasks in the list.");
                        System.out.println("    ___________________________________________________________");
                    } else if (input.contains("event")) {
                        if (input.length() <= 6 || !input.contains("/at")) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String[] splitStr = input.split("/at ");
                        String description = splitStr[0].substring(6).trim();
                        Event event = new Event(description, splitStr[1]);
                        tasks.add(event);
                        total++;
                        System.out.println("    ___________________________________________________________");
                        System.out.println("    Got it. I've added this task:\n      " + event + "\n    Now you have "
                                + total + " tasks in the list.");
                        System.out.println("    ___________________________________________________________");
                    } else if (input.contains("delete")) {
                        String[] splitStr = input.split(" ");
                        if (splitStr.length == 1) {
                            throw new DukeException("☹ OOPS!!! I don't know which task to delete.");
                        }
                        int taskIndex = Integer.parseInt(splitStr[1]) - 1;
                        Task task = tasks.get(taskIndex);
                        tasks.remove(taskIndex);
                        total--;
                        System.out.println("    ___________________________________________________________");
                        System.out.println("    Noted. I've removed this task:\n      " + task + "\n    Now you have "
                            + total + " tasks in the list.");
                        System.out.println("    ___________________________________________________________");

                    }
                    else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException ex) {
                    System.out.println("    ___________________________________________________________");
                    System.out.println("    " + ex);
                    System.out.println("    ___________________________________________________________");
                }
            }
        }
    }
}
