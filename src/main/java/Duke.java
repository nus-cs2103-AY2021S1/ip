import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________";
    private static final ArrayList<String> inputList = new ArrayList<>();

    public static void main(String[] args) {
        String startMsg =
            DIVIDER + "\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            DIVIDER;
        System.out.println(startMsg);

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while(!input.equals("bye")) {
            handleInput(input);
            input = sc.next();
        }

        String exitMsg =
            DIVIDER + "\n" +
            "Bye. Hope to see you again soon!\n" +
            DIVIDER;
        System.out.println(exitMsg);
    }

    private static String getEcho(String str) {
        String text = "added: " + str + "\n";
        return DIVIDER + "\n" + text + DIVIDER;
    }

    private static void handleInput(String in) {
        if (in.equals("list")) {
            int len = inputList.size();
            System.out.println(DIVIDER);
            for (int i = 0; i < len; i++) {
                System.out.println(i + ". " + inputList.get(i));
            }
            System.out.println(DIVIDER);
        } else {
            inputList.add(in);
            System.out.println(getEcho(in));
        }
    }
}
