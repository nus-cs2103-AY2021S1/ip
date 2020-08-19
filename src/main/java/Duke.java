import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String LINE_BREAK_BOT = "____________________________________________________________";
    private static final String LINE_BREAK_USER = "************************************************************";
    private static final String SPACE_BEFORE_LINE = "   ";
    private static final String SPACE_BEFORE_TEXT = "    ";

    public static void main(String[] args) {
        List<String> userInput = new ArrayList<>();

        System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT);
        System.out.println(SPACE_BEFORE_TEXT + "Hey, I'm Emilia!" + "\n" +
                SPACE_BEFORE_TEXT + "What can I do for you ♥ ?");
        System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.trim().toLowerCase().equals("bye")) {
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT + "\n" + SPACE_BEFORE_TEXT +
                        "Welcome back, master ♥ !" + "\n" + SPACE_BEFORE_LINE + LINE_BREAK_BOT);
                break;

            } else if (input.trim().toLowerCase().equals("list")) {
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_USER);
                int count = 1;
                for (String text : userInput) {
                    System.out.println(SPACE_BEFORE_TEXT + count + ". " + text);
                    count++;
                }
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT);

            } else {
                userInput.add(input);
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_USER + "\n" + SPACE_BEFORE_TEXT +
                        "added: " + input + "\n" + SPACE_BEFORE_LINE + LINE_BREAK_BOT);
            }
        }
    }
}
