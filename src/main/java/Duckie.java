import java.net.IDN;
import java.util.ArrayList;
import java.util.Scanner;

public class Duckie {
    private final static String INDENT = "    ";
    private final static String horizL = INDENT +
            "____________________________________________________________";
    private final static String logo = INDENT
            + "           ____                   _      _\n"
            + INDENT + "    __    |  _ \\   _   _    ___  | | _  (_)  ___     __\n"
            + INDENT + "___( o)>  | | | | | | | | /  __| | |/ / | | / _ \\  <(o )___ \n"
            + INDENT + "\\ <_. )   | |_| | | | | | | (__  |   <  | | | __/   ( ._> /\n"
            + INDENT + " `---'    |____/  \\___,_|  \\ __| |_|\\_\\ |_| \\___|    `___' \n";
    private static ArrayList<Task> lst = new ArrayList<>();

    private static void intro() {
        System.out.println(horizL + "\n" + logo + "\n" +
                INDENT + "Quack. Duckie is here to remember your tasks!\n" +
                INDENT + "Please start!\n" + horizL);
    }

    private static void ending() {
        System.out.println("\n" + INDENT +
                "Quack! Hope to see you again!\n" + horizL);
    }

    private static void displayList() {
        int index = 1;
        System.out.println(horizL);
        System.out.println(INDENT + "Quack! These are the tasks in your list: ");
        for (Task task : lst) {
            System.out.println(INDENT + index + ". " + task);
            index++;
        }
        System.out.println(horizL);
    }

    private static void addTask(String input, String type) throws DuckieException{
        Task t1;
        try {
            if (type.equals("todo")) {
                String todo = input.split(" ", 2)[1];
                t1 = new Todo(todo);
            } else {
                String[] splitted = input.split("/");
                String time = splitted[1].split(" ", 2)[1];
                String description = splitted[0].split(" ", 2)[1];
                if (type.equals("deadline")) {
                    t1 = new Deadline(description, time);
                } else { //This will be Event task.
                    t1 = new Event(description, time);
                }
            }
        } catch (Exception e) {
            throw new DuckieException(horizL + "\n" + INDENT
                    + "Duckie is unable to add task without a description behind.\n"
                    + horizL);
        }
        lst.add(t1);
        System.out.println(horizL);
        System.out.println(INDENT + "Quack! Added: " + t1);
        System.out.println(INDENT + "Now you have " + lst.size() + " tasks in the list.");
        System.out.println(horizL);
    }

    private static void checkTask(int ind) {
        System.out.println(horizL);
        Task t1 = lst.get(ind - 1);
        t1.checked();
        System.out.println(INDENT + "Quack! I've marked this task as done: \n" +
                 INDENT + t1 + "\n" + horizL);
    }

    //Check if a String only
    private static boolean is_word(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1);
    }

    public static void main(String[] args) {
        Duckie.intro();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String initialInput = sc.nextLine();
                String input = initialInput.strip();
                if (input.equalsIgnoreCase("bye")) {
                    System.out.print(horizL);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    Duckie.displayList();
                } else if (input.toLowerCase().indexOf("done") == 0) {
                    if (lst.size() == 0) {
                        throw new DuckieException(horizL + "\n" + INDENT
                                + "Quack! You have no list to start with!\n"
                                + horizL);
                    } else if (is_word(input)) {
                        throw new DuckieException(horizL + "\n" + INDENT
                                + "Duckie is unable to check the task for you without an index!\n"
                                + horizL);
                    }
                    int ind = Integer.parseInt(input.split(" ")[1]);
                    Duckie.checkTask(ind);
                } else if (input.toLowerCase().indexOf("todo") == 0) {
                    Duckie.addTask(input, "todo");
                } else if (input.toLowerCase().indexOf("deadline") == 0) {
                    Duckie.addTask(input, "deadline");
                } else if (input.toLowerCase().indexOf("event") == 0) {
                    Duckie.addTask(input, "event");
                } else {
                    throw new DuckieException(horizL + "\n" + INDENT
                            + "Sorry, Duckie does not know what you are trying to do.\n"
                            + horizL);
                }
            } catch (DuckieException e) {
                System.out.println(e.getMessage());
            }
        }
        Duckie.ending();
    }
}
