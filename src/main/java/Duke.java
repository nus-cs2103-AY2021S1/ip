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
        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println(lines);
                System.out.println("Here are the tasks in your list:");
                for (Task task : list) {
                    String stringedIndex = Integer.toString(list.indexOf(task) + 1);
                    String outputLine = stringedIndex + ". " + task;
                    System.out.println(outputLine);
                }
                System.out.println(lines);
            } else if (input.contains("done")) {
                String stringIndex = input.substring(5, input.length());
                int index = Integer.parseInt(stringIndex);
                Task chosen = list.get(index - 1);
                chosen.finish();
                System.out.println(lines);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(chosen);
                System.out.println(lines);
            } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                Task task = null;

                if (input.contains("todo")) {
                    if (input.length() < 5 || input.substring(5).replaceAll("\\s", "").equals("")) {
                        System.out.println(lines);
                        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(lines);
                    } else {
                        task = new Todo(input.substring(5, input.length()));
                    }

                } else if (input.contains("deadline")) {
                    if (input.length() < 8 || input.substring(8).replaceAll("\\s", "").equals("")) {
                        System.out.println(lines);
                        System.out.println(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(lines);
                    } else if (!input.contains("/")) {
                        System.out.println(lines);
                        System.out.println(" ☹ OOPS!!! The description of a deadline must be followed up by a {space}/by{space}{due-date}.");
                        System.out.println(lines);
                    } else if (input.contains("/by")
                            && Character.toString(input.charAt(8)).equals(" ")
                            && Character.toString(input.charAt(input.indexOf("/") + 3)).equals(" ")
                            && Character.toString(input.charAt(input.indexOf("/") - 1)).equals(" ")) {
                        String desc = input.substring(9, input.indexOf("/") - 1);
                        String by = input.substring(input.indexOf("/") + 4, input.length());
                        task = new Deadline(desc, by);
                    } else {
                        System.out.println(lines);
                        System.out.println(" ☹ OOPS!!! The format must be deadline{space}{description}{space}/by{space}{due-date}.");
                        System.out.println(lines);
                    }

                } else if (input.contains("event")) {

                    if (input.length() < 5 || input.substring(5).replaceAll("\\s", "").equals("")) {
                        System.out.println(lines);
                        System.out.println(" ☹ OOPS!!! The description of an event cannot be empty.");
                        System.out.println(lines);
                    } else if (!input.contains("/at")) {
                        System.out.println(lines);
                        System.out.println(" ☹ OOPS!!! The description of an event must be followed up by a {space}/at{space}{time-slot}.");
                        System.out.println(lines);
                    } else if (input.contains("/")
                            && Character.toString(input.charAt(5)).equals(" ")
                            && Character.toString(input.charAt(input.indexOf("/") + 3)).equals(" ")
                            && Character.toString(input.charAt(input.indexOf("/") - 1)).equals(" ")) {
                        String desc = input.substring(6, input.indexOf("/") - 1);
                        String at = input.substring(input.indexOf("/") + 4, input.length());
                        task = new Event(desc, at);
                    } else {
                        System.out.println(lines);
                        System.out.println(" ☹ OOPS!!! The format must be event{space}{description}{space}/by{space}{time-slot}.");
                        System.out.println(lines);
                    }
                }

                if (task != null) {
                    System.out.println(lines);
                    System.out.println(" Got it. I've added this task: ");
                    list.add(task);
                    System.out.println("  " + task);
                    int listCount = list.size();
                    System.out.println(" Now you have " + Integer.toString(listCount) + " tasks in the list.");
                    System.out.println(lines);
                }

            } else if (input.equals("bye")) { // end
                System.out.println(lines);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(lines);
                System.exit(0);
            } else {
                System.out.println(lines);
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(lines);
            }

        }
    }
}
