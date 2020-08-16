import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        introduction();
        Scanner sc  = new Scanner(System.in);
        interact(sc);
        sc.close();
    }

    public static void introduction() {
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static void interact(Scanner sc) {
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
    }
}
