import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {

    private final static ArrayList<Task> list = new ArrayList<>();

    public static void start() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void reply(String input) {
        if (input.trim().equals("list")) {
            if (list.size() == 0) {
                System.out.println("There are no tasks in your list");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < list.size() + 1; i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            }
        } else if (input.startsWith("done ") || input.equals("done")) {
            try {
                markAsDone(input);
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            } catch (NumberFormatException error) {
                System.out.println("Please provide a valid task number to mark as done");
            } catch (IndexOutOfBoundsException error) {
                System.out.println("This task is not in your list");
            }
        } else if (input.startsWith("delete ") || input.equals("delete")) {
            try {
                deleteTask(input);
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            } catch (NumberFormatException error) {
                System.out.println("Please provide a valid task number to delete");
            } catch (IndexOutOfBoundsException error) {
                System.out.println("This task is not in your list");
            }
        } else {
            try {
                addTask(input);
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    public static void addTask(String input) throws DukeException {
        Task task;
        if (input.startsWith("todo ") || input.equals("todo")) {
            if (input.length() < 6 || input.substring(5).trim().isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String name = input.substring(5).trim();
            task = new ToDo(name);
        } else if (input.startsWith("deadline ") || input.equals("deadline")) {
            if (input.length() < 10 || input.substring(9).trim().isEmpty()) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String details = input.substring(9).trim();
            String[] split = details.split("/by ");
            if (split.length != 2) {
                throw new DukeException("Please use the format: deadline (name) /by (when)");
            }
            String name = split[0];
            LocalDate by = LocalDate.parse(split[1]);
            task = new Deadline(name, by);
        } else if (input.startsWith("event ") || input.equals("event")) {
            if (input.length() < 7 || input.substring(6).trim().isEmpty()) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String details = input.substring(6).trim();
            String[] split = details.split("/at ");
            if (split.length != 2) {
                throw new DukeException("Please use the format: event (name) /at (what time)");
            }
            String name = split[0];
            LocalDate duration = LocalDate.parse(split[1]);
            task = new Event(name, duration);
        } else {
            System.out.println("I'm sorry, but I don't know what that means.");
            return;
        }
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (list.size() > 1) {
            System.out.println("Now you have " + list.size() + " tasks in your list");
        } else {
            System.out.println("Now you have " + list.size() + " task in your list");
        }
    }

    public static void markAsDone(String input) throws DukeException {
        if (input.length() < 6 || input.substring(5).trim().isEmpty()) {
            throw new DukeException("Which task do you want to mark as done?");
        }
        String item = input.substring(5).trim();
        int index = Integer.parseInt(item) - 1;
        list.get(index).done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
    }

    public static void deleteTask(String input) throws DukeException {
        if (input.length() < 8 || input.substring(7).trim().isEmpty()) {
            throw new DukeException("Which task do you want to delete?");
        }
        String item = input.substring(7).trim();
        int index = Integer.parseInt(item) - 1;
        Task deletedTask = list.get(index);
        list.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("You now have " + list.size() + " tasks in your list");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            reply(input);
            input = sc.nextLine();
        }
        exit();
    }
}