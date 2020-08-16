import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);

        Scanner sc  = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("    ____________________________________________________________\n"
                    + "     "
                    + command
                    + "\n"
                    + "    ____________________________________________________________\n"
            );

            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n"
        );

        sc.close();
    }
}
