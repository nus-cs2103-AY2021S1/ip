import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        String logo = " ______  ___       __         __        _____\n"
                + "   |    /         /  \\       /  \\     /\n"
                + "   |    \\___     /____\\     /____\\   |\n"
                + "   |        \\   /      \\   /      \\   \\\n"
                + " ------  ___/  /        \\ /        \\    -----\n";
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");
        String next = "";
        while (true) {
            next = sc.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
                for (Task i : list) {
                    System.out.println(list.indexOf(i) + 1 + "." + i);
                }
            } else if (next.length() > 4 && next.substring(0, 4).equals("done")) {
                int pos = Integer.parseInt(next.substring(5));
                list.get(pos - 1).markAsDone();
                System.out.println("Marked this task as done for you!\n" + list.get(pos - 1));
            } else if (next.length() > 4 && next.substring(0, 4).equals("todo")) {
                list.add(new Todo(next.substring(5)));
                System.out.println("Hi there, I've added this task:\n"
                        + list.get(list.size() - 1) + "\nYou have "
                        + list.size() + " tasks in the list");
            } else if (next.length() > 8 && next.substring(0, 8).equals("deadline")) {
                String[] descriptions = next.substring(9).split("/by ");
                list.add(new Deadline(descriptions[0], descriptions[1]));
                System.out.println("Hi there, I've added this task:\n"
                        + list.get(list.size() - 1) + "\nYou have "
                        + list.size() + " tasks in the list");
            } else if (next.length() > 5 && next.substring(0, 5).equals("event")) {
                String[] descriptions = next.substring(6).split("/at ");
                list.add(new Event(descriptions[0], descriptions[1]));
                System.out.println("Hi there, I've added this task:\n"
                        + list.get(list.size() - 1) + "\nYou have "
                        + list.size() + " tasks in the list");
            } else {
                System.out.println("Error! Please key in what type of task it is." );
            }
        }
    }
}


