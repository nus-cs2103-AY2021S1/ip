import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String command;
    public static String[] inputs,tokens;
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) throws DukeException{
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________");

        Scanner sc = new Scanner(System.in);
        inputs = sc.nextLine().split(" ",2);
        command = inputs[0];
        tasks = Storage.load();

        while(!command.equals("bye")) {
            switch(command) {
                case "list":
                    System.out.println("Task list");
                    int i = 1;
                    for (Task task : tasks) {
                        System.out.println(i++ + ". " + task);
                    }
                    break;
                case "done":
                    try {
                        handleDone();
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "deadline":
                    tokens = inputs[1].split(" /by ");
                    tasks.add(new Deadline(tokens[0],tokens[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "event":
                    tokens = inputs[1].split(" /at ");
                    tasks.add(new Event(tokens[0],tokens[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "todo":
                    try {
                        handleToDo();
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "delete":
                    try {
                        handleDelete();
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;
                default:
                    try {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
            }
            inputs = sc.nextLine().split(" ",2);
            command = inputs[0];
        }
        Storage.save(tasks);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }

    private static void handleToDo() throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        tasks.add(new ToDo(inputs[1]));
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDone() throws DukeException {
        int index = Integer.parseInt(inputs[1]) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("This task does not exist");
        }
        tasks.get(index).markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(index));
    }

    private static void handleDelete() throws DukeException {
        int index = Integer.parseInt(inputs[1]) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("This task does not exist");
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index));
        tasks.remove(index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
