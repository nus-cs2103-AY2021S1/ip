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
            System.out.println("added " + next);
            list.add(new Task(next));
            response();
        }
    }
}
