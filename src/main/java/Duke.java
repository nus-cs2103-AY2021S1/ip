import java.util.Scanner;

public class Duke {

    private static Task[] lst = new Task[100];
    private static int counter = 0;

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
                System.out.println("added: " + s);
                System.out.println("-------------------------------------------------------------------------------------");
                lst[counter] = new Task(s);
                counter += 1;
            }
            s = scan.nextLine();
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
