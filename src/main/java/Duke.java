import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static String border = "-----------------------------------------------------------";
    public static String indentation = "    ";
    public static List<Task> tasks = new ArrayList<>();

    public static void interact() throws DukeException {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                exit();
                break;
            } else if (line.equals("list")) {
                list();
            } else {
                String[] arrOfStr = line.split(" ", 0);
                String identifier = arrOfStr[0];
//                mark as done
                if (identifier.equals("done")) {
                    int index = Integer.parseInt(arrOfStr[1]) - 1;
                    markDone(index);
                } else {
//                    add to list
                    if ((identifier.equals("todo") || identifier.equals("deadline")
                            || identifier.equals("event")) && arrOfStr.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a " + identifier + " cannot be empty.");
                    } else {
                        Task task;
                        if (identifier.equals("todo")) {
                            String[] newArrOfStr = line.split(" ", 2);
                            task = new Todo(newArrOfStr[1]);
                        } else if (identifier.equals("deadline")) {
                            String[] firstSplit = line.split("/by ", 2);
                            String time = firstSplit[1];
                            String[] secondSplit = firstSplit[0].split(" ", 2);
                            String description = secondSplit[1].substring(0, secondSplit[1].length() - 1);
                            task = new Deadline(description, time);
                        } else if (identifier.equals("event")) {
                            String[] firstSplit = line.split("/at ", 2);
                            String time = firstSplit[1];
                            String[] secondSplit = firstSplit[0].split(" ", 2);
                            String description = secondSplit[1].substring(0, secondSplit[1].length() - 1);
                            task = new Event(description, time);
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        add(task);
                    }
                }
            }
        }
    }

    public static void printBorder() {
        System.out.println(indentation + border);
    }

    public static void greet() {
        printBorder();
        System.out.println(indentation + "Hello! I'm Duke\n    What can I do for you?");
        printBorder();
    }

    public static void exit() {
        printBorder();
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        printBorder();
    }

    public static void list() {
        printBorder();
        System.out.println(indentation + "Here are the tasks in your list:");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(indentation + index + "." + task);
        }
        printBorder();
    }

    public static void add(Task task) {
        printBorder();
        tasks.add(task);
        System.out.println(indentation + "Got it. I've added this task:");
        System.out.println(indentation + indentation + task);
        System.out.println(indentation + "Now you have " + (tasks.size() > 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        printBorder();
    }

    public static void markDone(int index) {
        Task task = tasks.get(index);
        Task newTask = task.markAsDone();
        tasks.set(index, newTask);
        printBorder();
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + indentation + newTask.getStatusIcon() + " " + newTask.description);
        printBorder();
    }

    public static void main(String[] args) {
        try {
            interact();
        } catch (Exception e) {
            printBorder();
            System.out.println(indentation + e.getMessage());
            printBorder();
        }
    }
}
