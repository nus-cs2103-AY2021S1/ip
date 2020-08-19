import java.util.Scanner;

public class Duke {

    private static final String lineBreak = "    ____________________________________________________________\n";
    private static final String preSpacing = "     ";
    private static final String openingMessage = "Hello! I'm Duke\n" + preSpacing + "What can I do for you?";
    private static final String closingMessage = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

    }

    private String processString(String string) {
        return lineBreak  + preSpacing + string + '\n' + lineBreak;
    }

    private String getOpeningText() {
        return processString(openingMessage);
    }

    private String getClosingText() {
        return processString(closingMessage);
    }



}
