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

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String welcomeMessage = "____________________________________________________________\n"
                + "Hello I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(welcomeMessage);

        ArrayList<Task> ls = new ArrayList<>(); // Create an empty ArrayList of tasks

        while (true) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) { // ability to list out tasks
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < ls.size(); i++) {
                    if (ls.get(i).isDone()) {
                        System.out.println((i+1)+  ". " + ls.get(i));
                    } else {
                        System.out.println((i+1)+  ". " + ls.get(i));
                    }
                }
            } else if (input.substring(0,4).equals("done")) { // ability to mark tasks as done
                // mark task as done
                int position = Integer.parseInt(input.substring(5));
                ls.get(position-1).markDone();

                // print out
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(ls.get(position-1));
            } else {
                if (input.substring(0,4).equals("todo")) {
                    ls.add(new Todo(input.substring(5)));
                } else if (input.substring(0,5).equals("event")) {
                    int pos = getPosition(input, '/');
                    String description = input.substring(6, pos);
                    String at = input.substring(pos + 4);
                    ls.add(new Event(description, at));

                } else if (input.substring(0,8).equals("deadline")) {
                    int pos = getPosition(input, '/');
                    String description = input.substring(9, pos);
                    String by = input.substring(pos + 4);
                    ls.add(new Deadline(description, by));

                } else {
                    System.out.println("Please try again");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(ls.get(ls.size() - 1));
                System.out.println("Now you have " + ls.size() + " tasks in the list.");

            }
            System.out.println("\n____________________________________________________________");
        }
    }
}