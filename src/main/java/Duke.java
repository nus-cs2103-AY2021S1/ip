import java.util.*;

public class Duke {
    private static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        response();
    }

    public static void response() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        if (next.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (next.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + "." + list.get(i));
            }
            response();
        } else if (next.startsWith("done")){
            try {
                int done = Integer.valueOf(next.substring(5, next.length()));
                list.get(done - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + list.get(done - 1));
            } catch(NumberFormatException e) {
                System.out.println("Please input a number");
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Please input a valid number");
            } finally {
                response();
            }
        } else {
            if (next.startsWith("todo ")) {
                ToDo todo = new ToDo(next.substring(5, next.length()));
                list.add(todo);
                System.out.println("Got it. I've added this task:\n\t" + todo);
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else if (next.startsWith("deadline ")) {
                int cut = next.indexOf(" /by ");
                if (cut != -1) {
                    String desc = next.substring(9, cut);
                    String by = next.substring(cut + 5, next.length());
                    Deadline deadline = new Deadline(desc, by);
                    list.add(deadline);
                    System.out.println("Got it. I've added this task:\n\t" + deadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                } else {
                    System.out.println("Please input a deadline");
                }
            } else if (next.startsWith("event ")) {
                int cut = next.indexOf(" /at ");
                if (cut != -1) {
                    String desc = next.substring(6, cut);
                    String time = next.substring(cut + 5, next.length());
                    Event event = new Event(desc, time);
                    list.add(event);
                    System.out.println("Got it. I've added this task:\n\t" + event);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                } else {
                    System.out.println("Please input a time for the event");
                }
            } else {
                System.out.println("Sorry I don't understand!");
            }
            response();
        }
    }
}
