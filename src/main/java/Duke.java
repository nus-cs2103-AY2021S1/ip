import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "\t==================================================\n\t  ";
    private static int n = -1;

    private static String output(String message) {
        return horizontalLine + message + "\n" + horizontalLine;
    }

    private static boolean isDoneCommand(String cmd) {
        if (cmd.startsWith("done ")) {
            try {
                n = Integer.parseInt(cmd.substring(5));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private static Task generate(String cmd) {
        if (cmd.startsWith("todo ")) {
            return new ToDo(cmd.substring(5));
        } else if (cmd.startsWith("deadline ")) {
            String description = cmd.substring(9);
            int s = description.indexOf("/by");
            if (s == -1) {
                return null;
            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Deadline(description, time);
        } else if (cmd.startsWith("event ")) {
            String description = cmd.substring(6);
            int s = description.indexOf("/at");
            if (s == -1) {
                return null;
            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Event(description, time);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(output("Hello! I'm Duke\n\t  What can I do for you?"));
        Task[] list = new Task[100];
        int count = 0;
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(horizontalLine + "Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println("\t  " + (i + 1) + "." + list[i]);
                }
                System.out.println(horizontalLine);
            } else if (isDoneCommand(input)) {
                if (n > 0 && n <= count) {
                    list[n - 1].markAsDone();
                    System.out.println(output("Nice! I've marked this task as done:\n\t    " + list[n - 1]));
                } else {
                    System.out.println(output("The task does not exist!"));
                }
            } else {
                Task task = generate(input);
                if (task == null) {
                    System.out.println(output("Your input task is invalid!"));
                } else {
                    list[count++] = task;
                    System.out.println(output("Got it. I've added this task:\n\t    " + task +
                            "\n\t  Now you have " + count + " tasks in the list."));
                }
            }
            input = sc.nextLine();
        }
        System.out.println(output("Bye. Hope to see you again soon!"));
    }
}
