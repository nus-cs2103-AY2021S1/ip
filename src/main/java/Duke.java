import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static int getPosition(String s, char target) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == target) {
                return i;
            }
        }
        return -1;
    }

    public static void listAllTasks(ArrayList<Task> al) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).isDone()) {
                System.out.println((i+1)+  ". " + al.get(i));
            } else {
                System.out.println((i+1)+  ". " + al.get(i));
            }
        }
    }

    public static void completeTask(String input, ArrayList<Task> ls) {
        // mark task as done
        int position = Integer.parseInt(input.substring(5));
        ls.get(position-1).markDone();

        // print out
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(ls.get(position-1));
    }

    public static void printAddedTask(ArrayList<Task> ls) {
        System.out.println("Got it. I've added this task:");
        System.out.println(ls.get(ls.size() - 1));
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }

    public static void addEvent(String input, ArrayList<Task> ls) throws DukeException {
        if (input.length() <= 6) {
            throw new DukeException("Exception occurred: Name not found for Event.");
        }
        int pos = getPosition(input, '/');
        String description = input.substring(6, pos);
        String at = input.substring(pos + 4);
        ls.add(new Event(description, at));
        printAddedTask(ls);
    }

    public static void addDeadline(String input, ArrayList<Task> ls) throws DukeException {
        if (input.length() <= 9) {
            throw new DukeException("Exception occurred: Name not found for Deadline.");
        }
        int pos = getPosition(input, '/');
        String description = input.substring(9, pos);
        String by = input.substring(pos + 4);
        ls.add(new Deadline(description, by));
        printAddedTask(ls);
    }

    public static void deleteTask(String input, ArrayList<Task> ls) throws DukeException {
        if (input.length() <= 7) {
            throw new DukeException("Exception occurred: Kindly enter a number for deletion.");
        }
        int position = Integer.parseInt(input.substring(7));
        System.out.println("Noted. I've removed this task:");
        System.out.println(ls.get(position-1));
        ls.remove(position-1);
    }

    public static String parseInput(String input) throws DukeException{
        ArrayList<String> commands = new ArrayList<>(
                Arrays.asList("DONE", "LIST", "DEADLINE", "EVENT", "TODO", "BYE", "DELETE"));
        String arr[] = input.split(" ");
        String command = arr[0].toUpperCase();
        if (!commands.contains(command)) {
            String errMsg = "\n____________________________________________________________\n"
                    + "I'm sorry, I don't understand what you're asking.\n"
                    + "____________________________________________________________";
            throw new DukeException(errMsg);
        }
        return command;
    }

    enum Choice {
        DONE, LIST, TODO, EVENT, DEADLINE
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String welcomeMessage = "____________________________________________________________\n"
                + "Hello I'm Duke\n"
                + "Your personal smart assistant?\n"
                + "____________________________________________________________";
        System.out.println(welcomeMessage);

        Storage storage = new Storage();

        ArrayList<Task> ls = storage.readFile("data.txt");

        while (true) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    storage.writeToFile("data.txt", ls);
                    break;
                } else if (input.length() > 5 && input.substring(0,6).equals("delete")) {
                    deleteTask(input, ls);
                    continue;
                }
                Choice choice = Choice.valueOf(parseInput(input));
                switch (choice) {
                    case DONE:
                        completeTask(input, ls);
                        break;
                    case LIST:
                        listAllTasks(ls);
                        break;
                    case DEADLINE:
                        addDeadline(input, ls);
                        break;
                    case EVENT:
                        addEvent(input, ls);
                        break;
                    case TODO:
                        ls.add(new Todo(input.substring(5)));
                        printAddedTask(ls);
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("\n____________________________________________________________");
        }
    }
}