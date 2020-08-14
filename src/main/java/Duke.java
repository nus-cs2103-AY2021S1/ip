import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();

        while (true) {
            String input = sc.next();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + input);
                System.out.println("    ____________________________________________________________");
                System.out.println();
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
