import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String lines = "____________________________________________________________";

        String greeting = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(greeting);
        if (sc.hasNextLine()) {
            input = sc.nextLine();
            ArrayList<Task> list = new ArrayList<>();

            while (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println(lines);
                    System.out.println("Here are the tasks in your list:");

                    for (Task task : list) {
                        String stringedIndex = Integer.toString(list.indexOf(task) + 1);
                        String outputLine = stringedIndex + ". " + task;
                        System.out.println(outputLine);
                    }
                    System.out.println(lines);
                    input = sc.nextLine();
                } else if (input.contains("done")) {
                    String stringIndex = input.substring(5, input.length());
                    int index = Integer.parseInt(stringIndex);
                    Task chosen = list.get(index -1);
                    chosen.finish();
                    System.out.println(lines);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(chosen);
                    System.out.println(lines);
                    input = sc.nextLine();
                } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                    Task task = null;
                    if (input.contains("todo")) {
                        task = new Todo(input.substring(5, input.length()));
                    } else if (input.contains("deadline")) {
                        String desc = input.substring(9, input.indexOf("/") - 1);
                        String by = input.substring(input.indexOf("/") + 4, input.length());
                        task = new Deadline(desc, by);
                    } else if (input.contains("event")) {
                        String desc = input.substring(6, input.indexOf("/") - 1);
                        String at = input.substring(input.indexOf("/") + 4, input.length());
                        task = new Event(desc, at);
                    }

                    System.out.println(lines);
                    System.out.println(" Got it. I've added this task: ");

                    list.add(task);
                    System.out.println("  " + task);
                    int listCount = list.size();
                    System.out.println(" Now you have " + Integer.toString(listCount) + " tasks in the list.");
                    System.out.println(lines);
                    input = sc.nextLine();



                } else {

                }
            }
            System.out.println(lines);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(lines);
        }

    }
}
