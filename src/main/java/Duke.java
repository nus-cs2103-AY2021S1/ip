import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________";
    private static final ArrayList<String> input_list = new ArrayList<>();

    public static void main(String[] args) {
        String start_msg =
            DIVIDER + "\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            DIVIDER;
        System.out.println(start_msg);

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while(!input.equals("bye")) {
            handle_input(input);
            input = sc.next();
        }

        String exit_msg =
            DIVIDER + "\n" +
            "Bye. Hope to see you again soon!\n" +
            DIVIDER;
        System.out.println(exit_msg);
    }

    private static String get_echo(String str) {
        String text = "added: " + str + "\n";
        return DIVIDER + "\n" + text + DIVIDER;
    }

    private static void handle_input(String in) {
        if (in.equals("list")) {
            int len = input_list.size();
            System.out.println(DIVIDER);
            for (int i = 0; i < len; i++) {
                System.out.println(i + ". " + input_list.get(i));
            }
            System.out.println(DIVIDER);
        } else {
            input_list.add(in);
            System.out.println(get_echo(in));
        }
    }
}
