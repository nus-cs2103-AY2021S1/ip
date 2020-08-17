import java.util.Scanner;

public class Duke {

    private static Task[] lst = new Task[100];
    private static int counter = 0;

    public static Task getTask(String st) {
        if ((st.length() >= 4) && (st.substring(0, 4).equals("todo"))) {
            return new ToDo(st.substring(5));
        } else if ((st.length() >= 8) && (st.substring(0, 8).equals("deadline"))) {
            String[] arr = st.substring(9).split(" /by ");
            return new Deadline(arr[0], arr[1]);
        } else {
            String[] arr = st.substring(6).split(" /at ");
            return new Event(arr[0], arr[1]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------------------------------------------------------");

        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + "." + lst[i]);
                }
                System.out.println("-------------------------------------------------------------------------------------");
            } else if ((s.length() >= 4) && (s.substring(0, 4).equals("done"))) {
                int i = Integer.parseInt(s.substring(5)) - 1;
                lst[i].markAsDone();
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("    " + lst[i]);
                System.out.println("-------------------------------------------------------------------------------------");
            } else {
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("Got it. I've added this task:");
                lst[counter] = getTask(s);
                System.out.println("    " + lst[counter]);
                counter += 1;
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println("-------------------------------------------------------------------------------------");
            }
            s = scan.nextLine();
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
