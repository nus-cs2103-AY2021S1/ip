import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alison {
    public static List<Task> taskList = new ArrayList<>();

    public static String horizontal_line = "________________________________________________________________";

    public static void printWithBorder(String content) {
        System.out.println(horizontal_line);
        System.out.println(content);
        System.out.println(horizontal_line);
    }

    public static void greeting() {
        String logo =
                "       d8888 888      8888888  .d8888b.   .d88888b.  888b    888 \n" +
                "      d88888 888        888   d88P  Y88b d88P\" \"Y88b 8888b   888 \n" +
                "     d88P888 888        888   Y88b.      888     888 88888b  888 \n" +
                "    d88P 888 888        888    \"Y888b.   888     888 888Y88b 888 \n" +
                "   d88P  888 888        888       \"Y88b. 888     888 888 Y88b888 \n" +
                "  d88P   888 888        888         \"888 888     888 888  Y88888 \n" +
                " d8888888888 888        888   Y88b  d88P Y88b. .d88P 888   Y8888 \n" +
                "d88P     888 88888888 8888888  \"Y8888P\"   \"Y88888P\"  888    Y888";

        printWithBorder("Hello from\n" + logo);
        String greet = "Hello! I'm Alison.\n" + "What can I do for you?\n" + horizontal_line;
        System.out.println(greet);
    }

    public static void showList() {
        System.out.println(horizontal_line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(String.format("%d.%s", i+1, task));
        }
        System.out.println(horizontal_line);
    }

    public static void addTaskMsg(Task task) {
        printWithBorder("Got it. I've added this task: \n "
                + task + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static void removeTaskMsg(Task task) {
        printWithBorder("Noted. I've removed this task: \n "
                + task + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static void response(String input) {
        String[] words = input.split(" ");
        String command = words[0];
        if (words.length == 1) {
            switch (command) {
                case "bye" -> printWithBorder("Bye. Hope to see you again soon!");
                case "list" -> showList();
                case "done", "delete" -> printWithBorder(" ☹ OOPS!!! You must provided the index " +
                        "of the task after "+ command + ".\n" +
                        "(i.e. "+ command + " 3)");
                case "todo", "deadline", "event" -> printWithBorder(" ☹ OOPS!!! The description of a "
                        + command + " cannot be empty.");
                default -> printWithBorder("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }
        if (words.length > 1) {
            String description = input.split(" ", 2)[1];
            switch (command) {
                case "done" -> {
                    try {
                        int doneIndex = Integer.parseInt(description);
                        Task task = taskList.get(doneIndex - 1);
                        task.markAsDone();
                        printWithBorder("Nice! I've marked this task as done: \n" + task);
                    } catch (Exception e) {
                        String len = String.valueOf(taskList.size());
                        printWithBorder("☹ OOPS!!! You entered an invalid index. " +
                                "Your current task index ranges from 1 to " + len + ".");
                    }
                }
                case "delete" -> {
                    try {
                        int deleteIdx = Integer.parseInt(description);
                        Task deleteTask = taskList.remove(deleteIdx - 1);
                        removeTaskMsg(deleteTask);
                    } catch (Exception e) {
                        printWithBorder("☹ OOPS!!! You entered an invalid index for deletion:-(");
                    }
                }
                case "todo" -> {
                    ToDo todo = new ToDo(description);
                    taskList.add(todo);
                    addTaskMsg(todo);
                }
                case "deadline" -> {
                    String[] contentAndTime = description.split(" /by ");
                    if (contentAndTime.length == 1) {
                        printWithBorder(" ☹ OOPS!!! You must provide a date " +
                                "after '/by' for a deadline. \n" +
                                "(i.e. deadline return book /by Sunday)");
                    } else {
                        Deadline ddl = new Deadline(contentAndTime[0], contentAndTime[1]);
                        taskList.add(ddl);
                        addTaskMsg(ddl);
                    }
                }
                case "event" -> {
                    String[] contentAndTime = description.split(" /at ");
                    if (contentAndTime.length == 1) {
                        printWithBorder(" ☹ OOPS!!! You must provide a time interval " +
                                "after '/at' for an event. \n" +
                                "(i.e. event project meeting /at Mon 2-4pm)");
                    } else {
                        Event e = new Event(contentAndTime[0], contentAndTime[1]);
                        taskList.add(e);
                        addTaskMsg(e);
                    }
                }
                default -> printWithBorder("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        greeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            response(input);
            input = sc.nextLine();
        }
        response(input);
    }
}
