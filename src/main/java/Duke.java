import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String echo = sc.next();
            if (echo.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + echo);
                System.out.println("    ____________________________________________________________");
            }
        }

    }
}
