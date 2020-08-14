import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String LINE = "    ____________________________________________________________";
    private static String WELCOME_MESSAGE = "Hello, I'm Duke!\n"
        + "     What can I do for you?";
    private static String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";

    private static ArrayList<String> list = new ArrayList<>();

    private static String makeWrappedString(String txt) {
        return LINE + "\n     " + txt + "\n" + LINE;
    }

    public static void main(String[] args) {
        System.out.println(makeWrappedString(WELCOME_MESSAGE));

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            switch (input) {
            case "list":
                System.out.println(LINE);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i + 1) + ": " + list.get(i));
                }
                System.out.println(LINE);
                break;
            default:
                list.add(input);
                System.out.println(makeWrappedString( "added: " + input));
                break;
            }
            input = sc.nextLine();
        }

        System.out.println(makeWrappedString(GOODBYE_MESSAGE));
    }
}
