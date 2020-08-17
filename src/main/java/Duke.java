import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________\n";

    public static void main(String[] args) {
        String start_msg =
            DIVIDER +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            DIVIDER;
        System.out.println(start_msg);

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while(!input.equals("bye")) {
            System.out.println(get_echo(input));
            input = sc.next();
        }

        String exit_msg =
            DIVIDER +
            "Bye. Hope to see you again soon!\n" +
            DIVIDER;
        System.out.println(exit_msg);
    }

    private static String get_echo(String str) {
        return DIVIDER + str + "\n" + DIVIDER;
    }
}
