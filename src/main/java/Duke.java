import java.util.Scanner;

public class Duke {
    private static final String LINE_BREAK_BOT = "____________________________________________________________";
    private static final String LINE_BREAK_USER = "************************************************************";
    private static final String SPACE_BEFORE_LINE = "   ";
    private static final String SPACE_BEFORE_TEXT = "    ";

    public static void main(String[] args) {
        System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT);
        System.out.println(SPACE_BEFORE_TEXT + "Hey, I'm Emilia!" + "\n" +
                SPACE_BEFORE_TEXT + "What can I do for you ♥ ?");
        System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (!input.trim().toLowerCase().equals("bye")) {
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_USER + "\n" + SPACE_BEFORE_TEXT +
                        input + "\n" + SPACE_BEFORE_LINE + LINE_BREAK_BOT);
            } else {
                System.out.println(SPACE_BEFORE_LINE + LINE_BREAK_BOT + "\n" + SPACE_BEFORE_TEXT +
                        "Welcome back, master ♥ !" + "\n" + SPACE_BEFORE_LINE + LINE_BREAK_BOT);
                break;
            }
        }
    }
}
